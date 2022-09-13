package com.hanwul.kbscbackend.domain.chat.room;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public ChatRoom(String description, Account account, RoomStatus status) {
        this.description = description;
        this.account = account;
        this.status = status;
    }
}
