package com.hanwul.kbscbackend.domain.mission.category;

import com.hanwul.kbscbackend.domain.mission.MissionCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionCategory category;

    @Builder
    public Category(MissionCategory category) {
        this.category = category;
    }
}
