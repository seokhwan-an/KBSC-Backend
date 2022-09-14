package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.rate.RateRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final AccountRepository accountRepository;
    private final RateRepository rateRepository;

    @Transactional
    public BasicResponseDto<Long> create(ChatRoomDto chatRoomDto, Principal principal){
        Account account = get_account(principal);
        ChatRoom chatRoom = dtoToEntity(chatRoomDto, account);
        chatRoomRepository.save(chatRoom);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "chatRoom", chatRoom.getId());
    }

    public BasicResponseDto<ChatRoomDto> read(Long chatRoomId){
        Optional<ChatRoom> result = chatRoomRepository.findById(chatRoomId);
        if(result.isEmpty()){
            throw new IllegalArgumentException("해당 ID의 채팅룸 없음");
        }
        ChatRoom chatRoom = result.get();
        ChatRoomDto chatRoomDto = entityToDto(chatRoom);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "chatRoom", chatRoomDto);
    }

    public BasicResponseDto<List<ChatRoomDto>> getAll(){
        List<ChatRoom> results = chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtos = results.stream().map(chatRoom -> {
            ChatRoomDto chatRoomDto = entityToDto(chatRoom);
            Account account = chatRoom.getAccount();
            log.info("account: {} id: {}", account.getUsername(), account.getId());
            int count = rateRepository.countByEstimatedAndPrefer(account, true);
            log.info("count: {}", count);
            chatRoomDto.setLikeCount(count);
            return chatRoomDto;
        }).collect(Collectors.toList());
        return new BasicResponseDto<>(HttpStatus.OK.value(), "chatRoom", chatRoomDtos);
    }

    public BasicResponseDto<List<ChatRoomDto>> getMy(Principal principal){
        Account account = get_account(principal);
        List<ChatRoom> byAccount = chatRoomRepository.findByAccount(account);
        List<ChatRoomDto> dtos = byAccount.stream().map(chatRoom -> entityToDto(chatRoom)).collect(Collectors.toList());
        return new BasicResponseDto<>(HttpStatus.OK.value(), "chatRoom", dtos);
    }

    public BasicResponseDto<Void> delete(Long chatRoomId){
        chatRoomRepository.deleteById(chatRoomId);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "chatRoom", null);
    }

    private Account get_account(Principal principal) {
        return accountRepository.findByUsername(principal.getName()).get();
    }

    public ChatRoom dtoToEntity(ChatRoomDto chatRoomDto, Account account){
        return ChatRoom.builder()
                .id(chatRoomDto.getId())
                .account(account)
                .description(chatRoomDto.getDescription())
                .status(chatRoomDto.getStatus())
                .build();
    }

    public ChatRoomDto entityToDto(ChatRoom chatRoom){
        return ChatRoomDto.builder()
                .id(chatRoom.getId())
                .description(chatRoom.getDescription())
                .status(chatRoom.getStatus())
                .build();
    }
}