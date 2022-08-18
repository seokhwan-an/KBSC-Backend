package com.hanwul.kbscbackend.domain.chattingmessage;

import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Long> {
    Optional<ChattingMessage> findByChattingRoom(ChattingRoom chattingRoom);
}
