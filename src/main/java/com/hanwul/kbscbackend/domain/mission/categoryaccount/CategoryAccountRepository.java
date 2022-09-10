package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryAccountRepository extends JpaRepository<CategoryAccount, Long> {

    Optional<CategoryAccount> findByAccountAndCategory(Account account, Category category);

    List<CategoryAccount> findByAccount(Account account);

    void deleteByAccountAndCategory(Account account, Category category);
}
