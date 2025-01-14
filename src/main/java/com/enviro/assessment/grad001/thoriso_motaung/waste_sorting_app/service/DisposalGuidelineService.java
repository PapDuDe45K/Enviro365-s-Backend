package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.DisposalGuideline;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Admin
 * Service class for managing disposal guidelines.
 * Handles business logic for disposal instructions.
 */
@Service
@Transactional
public class DisposalGuidelineService {
    private static final Logger logger = Logger.getLogger(DisposalGuidelineService.class.getName());
    
    private final DisposalGuidelineRepository disposalGuidelineRepository;
    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public DisposalGuidelineService(DisposalGuidelineRepository disposalGuidelineRepository,
                                  WasteCategoryRepository wasteCategoryRepository) {
        this.disposalGuidelineRepository = disposalGuidelineRepository;
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public DisposalGuideline createGuideline(DisposalGuideline guideline) {
        logger.info("Creating new guideline: " + guideline.getTitle());
        
        if (!wasteCategoryRepository.existsById(guideline.getCategory().getId())) {
            logger.warning("Category not found with ID: " + guideline.getCategory().getId());
            throw new RuntimeException("Waste category not found");
        }
        
        DisposalGuideline savedGuideline = disposalGuidelineRepository.save(guideline);
        logger.info("Created guideline with ID: " + savedGuideline.getId());
        return savedGuideline;
    }

    public List<DisposalGuideline> getAllGuidelines() {
        logger.info("Fetching all guidelines");
        return disposalGuidelineRepository.findAll();
    }

    public Optional<DisposalGuideline> getGuidelineById(Long id) {
        logger.info("Fetching guideline with ID: " + id);
        return disposalGuidelineRepository.findById(id);
    }

    public List<DisposalGuideline> getGuidelinesByCategory(Long categoryId) {
        logger.info("Fetching guidelines for category ID: " + categoryId);
        
        if (!wasteCategoryRepository.existsById(categoryId)) {
            logger.warning("Category not found with ID: " + categoryId);
            throw new RuntimeException("Category not found");
        }
        
        return disposalGuidelineRepository.findByCategoryId(categoryId);
    }

    @Transactional
public DisposalGuideline updateGuideline(Long id, DisposalGuideline guideline) {
    logger.info("Updating guideline with ID: " + id);

    // Get existing guideline
    DisposalGuideline existingGuideline = disposalGuidelineRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Guideline not found"));

    // Verify category exists
    if (!wasteCategoryRepository.existsById(guideline.getCategory().getId())) {
        throw new RuntimeException("Category not found");
    }

    // Update fields
    existingGuideline.setTitle(guideline.getTitle());
    existingGuideline.setDescription(guideline.getDescription());
    existingGuideline.setCategory(guideline.getCategory());
    existingGuideline.setDisposalMethod(guideline.getDisposalMethod());
    existingGuideline.setSafetyPrecautions(guideline.getSafetyPrecautions());

    // Save and return
    return disposalGuidelineRepository.save(existingGuideline);
}

    @Transactional
    public void deleteGuideline(Long id) {
        logger.info("Attempting to delete guideline with ID: " + id);
        
        if (!disposalGuidelineRepository.existsById(id)) {
            logger.warning("Guideline not found with ID: " + id);
            throw new RuntimeException("Guideline not found");
        }
        
        disposalGuidelineRepository.deleteById(id);
        logger.info("Successfully deleted guideline with ID: " + id);
    }

    // Helper method to validate guideline data
    private void validateGuideline(DisposalGuideline guideline) {
        if (guideline.getTitle() == null || guideline.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Title is required");
        }
        if (guideline.getDescription() == null || guideline.getDescription().trim().isEmpty()) {
            throw new RuntimeException("Description is required");
        }
        if (guideline.getDisposalMethod() == null || guideline.getDisposalMethod().trim().isEmpty()) {
            throw new RuntimeException("Disposal method is required");
        }
        if (guideline.getCategory() == null || guideline.getCategory().getId() == null) {
            throw new RuntimeException("Category is required");
        }
    }
}