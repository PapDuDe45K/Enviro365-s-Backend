/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Controller.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.controller;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.WasteCategory;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
/**
 *
 * @author Admin
 * * REST Controller for managing waste categories.
 * Provides endpoints for CRUD operations on waste categories.
 * 
 * Endpoints:
 * - POST /api/categories - Create new category
 * - GET /api/categories - Get all categories
 * - GET /api/categories/{id} - Get category by ID
 * - PUT /api/categories/{id} - Update category
 * - DELETE /api/categories/{id} - Delete category
 */

@RestController
@RequestMapping("/api/categories")
public class WasteCategoryController {
    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createCategory(@Valid @RequestBody WasteCategory category) {
        WasteCategory createdCategory = wasteCategoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        List<WasteCategory> categories = wasteCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getCategoryById(@PathVariable Long id) {
        return wasteCategoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody WasteCategory category) {
        try {
            WasteCategory updatedCategory = wasteCategoryService.updateCategory(id, category);
            return ResponseEntity.ok(updatedCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            wasteCategoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}