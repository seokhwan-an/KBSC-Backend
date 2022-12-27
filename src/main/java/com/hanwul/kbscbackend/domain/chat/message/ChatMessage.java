package com.hanwul.kbscbackend.domain.chat.message;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.chat.history.RoomHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "chat_message")
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_history_id")
    private RoomHistory roomHistory;

    private String content;

    @ManyToOne
    private Account sender;

    @Builder
    public ChatMessage(RoomHistory roomHistory, String content, Account sender) {
        this.roomHistory = roomHistory;
        this.content = content;
        this.sender = sender;
    }
}
