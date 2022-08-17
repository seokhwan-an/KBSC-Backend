package com.hanwul.kbscbackend.domain.chatting_room;

import com.hanwul.kbscbackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    Optional<User> findByConstructor(User user);

    Optional<User> findByParticipant(User user);
}
