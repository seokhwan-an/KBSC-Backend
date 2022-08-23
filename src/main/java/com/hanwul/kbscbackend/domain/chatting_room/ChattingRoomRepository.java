package com.hanwul.kbscbackend.domain.chatting_room;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    Optional<Account> findByConstructor(Account user);

    Optional<Account> findByParticipant(Account user);
}
