package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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