package com.hanwul.kbscbackend.domain.chat.message;

import lombok.Data;

@Data
public class ChatMessageDto {

    private String roomId;
    private String writer;
    private String message;
}
