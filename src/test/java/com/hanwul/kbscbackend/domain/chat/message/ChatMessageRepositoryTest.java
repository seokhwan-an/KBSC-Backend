package com.hanwul.kbscbackend.domain.chat.message;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.chat.history.RoomHistory;
import com.hanwul.kbscbackend.domain.chat.history.RoomHistoryRepository;
import com.hanwul.kbscbackend.domain.chat.room.ChatRoom;
import com.hanwul.kbscbackend.domain.chat.room.ChatRoomRepository;
import com.hanwul.kbscbackend.domain.chat.room.RoomStatus;
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
class ChatMessageRepositoryTest {

//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ChatRoomRepository chatRoomRepository;
//
//    @Autowired
//    private RoomHistoryRepository roomHistoryRepository;
//
//    @Autowired
//    private ChatMessageRepository chatMessageRepository;
//
//    @Test
//    @DisplayName("채팅 생성 테스트")
//    void createChatMessageTest() throws Exception {
//        // given
//        Account from = Account.builder()
//                .username("from")
//                .password(passwordEncoder.encode("1234"))
//                .nickname("nick01")
//                .build();
//
//        Account to = Account.builder()
//                .username("to")
//                .password(passwordEncoder.encode("1234"))
//                .nickname("nick02")
//                .build();
//
//        accountRepository.save(from);
//        accountRepository.save(to);
//
//        ChatRoom chatRoom = ChatRoom.builder()
//                .account(from)
//                .status(RoomStatus.WILL)
//                .description("채팅룸1")
//                .build();
//        chatRoomRepository.save(chatRoom);
//
//        RoomHistory history = RoomHistory.builder()
//                .chatRoom(chatRoom)
//                .from(from)
//                .to(to)
//                .build();
//        roomHistoryRepository.save(history);
//
//        ChatMessage message1 = ChatMessage.builder()
//                .content("채팅 내용1")
//                .roomHistory(history)
//                .sender(from)
//                .build();
//
//        ChatMessage message2 = ChatMessage.builder()
//                .content("채팅 내용2")
//                .roomHistory(history)
//                .sender(to)
//                .build();
//
//        ChatMessage message3 = ChatMessage.builder()
//                .content("채팅 내용3")
//                .roomHistory(history)
//                .sender(from)
//                .build();
//
//        // when
//        chatMessageRepository.save(message1);
//        chatMessageRepository.save(message2);
//        chatMessageRepository.save(message3);
//
//        // then
//    }
}