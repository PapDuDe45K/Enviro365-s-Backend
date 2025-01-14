/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository;

/**
 *
 * @author Admin
 */
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;
/**
 * Repository interface for RecyclingTip entity.
 * Manages recycling tips data access.
 */
@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    List<RecyclingTip> findByDifficultyLevel(String difficultyLevel);
    List<RecyclingTip> findByTitleContainingIgnoreCase(String keyword);
    Optional<RecyclingTip> findByTitle(String title);
    List<RecyclingTip> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}