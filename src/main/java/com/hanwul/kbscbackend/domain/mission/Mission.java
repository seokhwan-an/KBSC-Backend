package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.mission.category.Category;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name="mission")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    public Category category;

}
