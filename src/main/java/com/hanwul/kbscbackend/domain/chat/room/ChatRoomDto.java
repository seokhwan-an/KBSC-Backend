package com.hanwul.kbscbackend.domain.chat.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomDto {

    private Long id;
    private String description;
    private RoomStatus status;

}