package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(mappedBy = "chatRoom")
    private Account account;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
