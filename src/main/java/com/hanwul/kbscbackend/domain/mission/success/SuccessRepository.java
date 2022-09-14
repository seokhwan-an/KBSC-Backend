package com.hanwul.kbscbackend.domain.mission.success;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SuccessRepository extends JpaRepository<Success, Long> {

    @Query("SELECT e FROM Success e WHERE  e.createdDateTime between :start and :end ")
    Optional<Success> findSuccessToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Account account);

    @Query("SELECT e FROM Success e WHERE  e.createdDateTime between :start and :end ")
    List<Success> findSuccessTopSevenDay(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Account account);
}
