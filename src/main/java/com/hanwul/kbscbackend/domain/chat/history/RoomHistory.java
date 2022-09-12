package com.hanwul.kbscbackend.domain.chat.history;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.chat.room.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "chat_room_account")
@Entity
public class RoomHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account from;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account to;

    @Builder
    public RoomHistory(ChatRoom chatRoom, Account from, Account to) {
        this.chatRoom = chatRoom;
        this.from = from;
        this.to = to;
    }
}
