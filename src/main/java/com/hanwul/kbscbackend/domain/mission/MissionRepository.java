package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.mission.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByCategory(Category category);
}
