package com.hanwul.kbscbackend.domain.chatting_room;

import com.hanwul.kbscbackend.domain.chattingmessage.ChattingMessage;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chatting_room")
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private Account constructor;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private Account participant;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<ChattingMessage> messageList = new ArrayList<>();
}
