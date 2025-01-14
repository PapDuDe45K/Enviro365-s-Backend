// Location: src/main/java/com/enviro/assessment/grad001/thoriso_motaung/waste_sorting_app/service/WasteCategoryService.java

package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.WasteCategory;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing waste categories.
 * Implements business logic and transaction management.
 */
@Service
@Transactional
public class WasteCategoryService {
    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public WasteCategory createCategory(WasteCategory category) {
        if (wasteCategoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category with this name already exists");
        }
        return wasteCategoryRepository.save(category);
    }

    public List<WasteCategory> getAllCategories() {
        return wasteCategoryRepository.findAll();
    }

    public Optional<WasteCategory> getCategoryById(Long id) {
        return wasteCategoryRepository.findById(id);
    }

    public WasteCategory updateCategory(Long id, WasteCategory category) {
        if (!wasteCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        category.setId(id);
        return wasteCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!wasteCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        wasteCategoryRepository.deleteById(id);
    }

    public List<WasteCategory> searchCategories(String keyword) {
        return wasteCategoryRepository.findByNameContainingIgnoreCase(keyword);
    }
}