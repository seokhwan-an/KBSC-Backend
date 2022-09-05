package com.hanwul.kbscbackend.domain.missioncategory;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.Mission;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class MissionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}
