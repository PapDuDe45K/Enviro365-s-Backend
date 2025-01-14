package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.controller;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.DisposalGuideline;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/guidelines")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4173"})
public class DisposalGuidelineController {
    private static final Logger logger = Logger.getLogger(DisposalGuidelineController.class.getName());
    private final DisposalGuidelineService disposalGuidelineService;

    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService disposalGuidelineService) {
        this.disposalGuidelineService = disposalGuidelineService;
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("Test endpoint called");
        return ResponseEntity.ok("Guidelines API is working!");
    }
    
    // CREATE
    @PostMapping
    public ResponseEntity<?> createGuideline(@Valid @RequestBody DisposalGuideline guideline) {
        try {
            logger.info("Attempting to create guideline: " + guideline.getTitle());
            
            if (guideline.getCategory() == null || guideline.getCategory().getId() == null) {
                logger.warning("Category information is missing");
                return ResponseEntity
                    .badRequest()
                    .body("Category information is required");
            }

            DisposalGuideline createdGuideline = disposalGuidelineService.createGuideline(guideline);
            logger.info("Successfully created guideline with ID: " + createdGuideline.getId());
            
            return new ResponseEntity<>(createdGuideline, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.warning("Error creating guideline: " + e.getMessage());
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());
        } catch (Exception e) {
            logger.severe("Error creating guideline: " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating guideline: " + e.getMessage());
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllGuidelines() {
        try {
            logger.info("Fetching all guidelines");
            List<DisposalGuideline> guidelines = disposalGuidelineService.getAllGuidelines();
            logger.info("Found " + guidelines.size() + " guidelines");
            return ResponseEntity.ok(guidelines);
        } catch (Exception e) {
            logger.severe("Error fetching all guidelines: " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching guidelines" + e.getMessage());
        }
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getGuidelineById(@PathVariable Long id) {
        try {
            logger.info("Fetching guideline with ID: " + id);
            return disposalGuidelineService.getGuidelineById(id)
                    .map(guideline -> {
                        logger.info("Found guideline with ID: " + id);
                        return ResponseEntity.ok(guideline);
                    })
                    .orElseGet(() -> {
                        logger.warning("Guideline not found with ID: " + id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.severe("Error fetching guideline with ID " + id + ": " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching guideline: " + e.getMessage());
        }
    }

    // READ BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getGuidelinesByCategory(@PathVariable Long categoryId) {
        try {
            logger.info("Fetching guidelines for category ID: " + categoryId);
            
            if (categoryId == null) {
                logger.warning("Category ID is null");
                return ResponseEntity
                    .badRequest()
                    .body("Category ID is required");
            }

            List<DisposalGuideline> guidelines = disposalGuidelineService.getGuidelinesByCategory(categoryId);
            logger.info("Found " + guidelines.size() + " guidelines for category ID: " + categoryId);
            return ResponseEntity.ok(guidelines);
        } catch (RuntimeException e) {
            logger.warning("Error fetching guidelines: " + e.getMessage());
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());
        } catch (Exception e) {
            logger.severe("Error fetching guidelines: " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching guidelines: " + e.getMessage());
        }
    }

    // UPDATE
   @PutMapping("/{id}")
public ResponseEntity<?> updateGuideline(@PathVariable Long id, @Valid @RequestBody DisposalGuideline guideline) {
    try {
        logger.info("Attempting to update guideline with ID: " + id);
        
        // Validate category
        if (guideline.getCategory() == null || guideline.getCategory().getId() == null) {
            logger.warning("Category information is missing");
            return ResponseEntity
                .badRequest()
                .body("Category information is required");
        }

        // Set the ID from the path parameter
        guideline.setId(id);
        
        // Perform update
        DisposalGuideline updatedGuideline = disposalGuidelineService.updateGuideline(id, guideline);
        logger.info("Successfully updated guideline with ID: " + id);
        
        return ResponseEntity.ok(updatedGuideline);
    } catch (RuntimeException e) {
        logger.warning("Error updating guideline: " + e.getMessage());
        return ResponseEntity
            .badRequest()
            .body(e.getMessage());
    } catch (Exception e) {
        logger.severe("Error updating guideline: " + e.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to save guideline");
    }
}

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuideline(@PathVariable Long id) {
        try {
            logger.info("Attempting to delete guideline with ID: " + id);
            disposalGuidelineService.deleteGuideline(id);
            logger.info("Successfully deleted guideline with ID: " + id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.warning("Error deleting guideline: " + e.getMessage());
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());
        } catch (Exception e) {
            logger.severe("Error deleting guideline: " + e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting guideline: " + e.getMessage());
        }
    }
}