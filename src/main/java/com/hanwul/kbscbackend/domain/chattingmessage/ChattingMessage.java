package com.hanwul.kbscbackend.domain.chattingmessage;

import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "message")
public class ChattingMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ChattingRoom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "header_id")
    private ChattingRoom chattingRoom;

    private String text;

    @CreatedDate
    private LocalDateTime createdDateTime;
}
