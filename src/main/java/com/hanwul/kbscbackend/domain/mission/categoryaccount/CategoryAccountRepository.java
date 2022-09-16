package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CategoryAccountRepository extends JpaRepository<CategoryAccount, Long> {

    Optional<CategoryAccount> findByAccountAndCategory(Account account, Category category);

    List<CategoryAccount> findByAccount(Account account);

    @Query("SELECT e FROM CategoryAccount e WHERE  e.createdDateTime between :start and :end ")
    List<CategoryAccount> findCategoryAccountToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
