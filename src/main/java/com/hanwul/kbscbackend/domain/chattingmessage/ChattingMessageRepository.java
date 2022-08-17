package com.hanwul.kbscbackend.domain.chattingmessage;

import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;

import java.util.Optional;

public interface ChattingMessageRepository {
    Optional<ChattingRoom> findByChattingRoom(ChattingRoom chattingRoom);
}
