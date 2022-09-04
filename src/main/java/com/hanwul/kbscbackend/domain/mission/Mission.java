package com.hanwul.kbscbackend.domain.mission;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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

    private String category;

    private Boolean isSuccess;

    public void changeStatus(){
        this.isSuccess = !(this.isSuccess);
    }
}
