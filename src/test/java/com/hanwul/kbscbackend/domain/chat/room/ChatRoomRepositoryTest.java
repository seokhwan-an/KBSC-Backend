package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ChatRoomRepositoryTest {

//    @Autowired
//    private ChatRoomRepository chatRoomRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    @DisplayName("채팅방 생성")
//    void createChatRoomTest() throws Exception {
//        // given
//        Account account = Account.builder()
//                .username("user01")
//                .password(passwordEncoder.encode("1234"))
//                .build();
//        accountRepository.save(account);
//
//        ChatRoom chatRoom = ChatRoom.builder()
//                .description("채팅룸1")
//                .status(RoomStatus.WILL)
//                .build();
//
//        // when
//        chatRoomRepository.save(chatRoom);
//        account.setChatRoom(chatRoom);
//
//        // then
//        assertThat(account.getChatRoom().getId()).isEqualTo(chatRoom.getId());
//    }
}