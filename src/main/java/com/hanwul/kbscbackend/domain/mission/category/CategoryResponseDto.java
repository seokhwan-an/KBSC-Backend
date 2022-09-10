package com.hanwul.kbscbackend.domain.mission.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryResponseDto {

    private Long id;
    private String category;
    private boolean isChecked = false;

    public CategoryResponseDto(Long id, String category) {
        this.id = id;
        this.category = category;
    }
}
