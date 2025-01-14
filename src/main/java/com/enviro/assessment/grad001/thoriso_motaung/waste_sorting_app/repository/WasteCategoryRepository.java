/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository;

/**
 *
 * @author Admin
 */
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
/**
 * Repository interface for WasteCategory entity.
 * Provides CRUD operations and custom queries for waste categories.
 */
@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    Optional<WasteCategory> findByName(String name);
    List<WasteCategory> findByNameContainingIgnoreCase(String keyword);
    boolean existsByName(String name);
}
