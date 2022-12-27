package com.hanwul.kbscbackend.domain.mission.success;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Success extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long count;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void deletesuccess(){
        this.count--;
    }

    public void addsuccess(){
        this.count++;
    }
}