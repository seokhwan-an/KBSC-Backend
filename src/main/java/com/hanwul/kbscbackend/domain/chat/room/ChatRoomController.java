package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public BasicResponseDto<Long> create(@RequestBody ChatRoomDto chatRoomDto, Principal principal){
        return chatRoomService.create(chatRoomDto, principal);
    }

    @GetMapping("/{chatRoomId}")
    public BasicResponseDto<ChatRoomDto> read(@PathVariable Long chatRoomId){
        return chatRoomService.read(chatRoomId);
    }

    @GetMapping
    public BasicResponseDto<List<ChatRoomDto>> getAll(){
        return chatRoomService.getAll();
    }

    @GetMapping("/my")
    public BasicResponseDto<List<ChatRoomDto>> getMy(Principal principal){
        return chatRoomService.getMy(principal);
    }

    @DeleteMapping("/{chatRoomId}")
    public BasicResponseDto<Void> delete(@PathVariable Long chatRoomId){
        return chatRoomService.delete(chatRoomId);
    }

}
