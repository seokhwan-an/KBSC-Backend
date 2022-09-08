package com.hanwul.kbscbackend.domain.mission.missionaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.Mission;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MissionAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mission mission;

    @ManyToOne
    private Account account;

    @Column
    private Boolean isSuccess = Boolean.FALSE;

    @Builder
    public MissionAccount(Mission mission, Account account, Boolean isSuccess) {
        this.mission = mission;
        this.account = account;
    }
}
