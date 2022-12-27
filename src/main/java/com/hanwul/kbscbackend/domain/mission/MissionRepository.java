package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.mission.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByCategory(Category category);


    List<Mission> findAll();

    Optional<Mission> findById(Long id);
}
