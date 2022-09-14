package com.hanwul.kbscbackend.domain.chat.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomHistoryRepository extends JpaRepository<RoomHistory, Long> {
}
