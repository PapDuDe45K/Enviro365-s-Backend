/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository;

/**
 *
 * @author Admin
 */
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.DisposalGuideline;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/**
 * Repository interface for DisposalGuideline entity.
 * Handles database operations for disposal guidelines.
 */
@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findByCategoryId(Long categoryId);
    Optional<DisposalGuideline> findByTitleAndCategory(String title, WasteCategory category);
    List<DisposalGuideline> findByTitleContainingIgnoreCase(String keyword);
    List<DisposalGuideline> findByDisposalMethodContainingIgnoreCase(String method);
}