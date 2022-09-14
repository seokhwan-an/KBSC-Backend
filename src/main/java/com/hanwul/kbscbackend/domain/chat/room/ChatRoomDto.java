package com.hanwul.kbscbackend.domain.chat.room;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ChatRoomDto {

    private Long id;
    private String description;
    private RoomStatus status;

}
