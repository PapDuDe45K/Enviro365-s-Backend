/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.controller;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.RecyclingTip;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
/**
 *
 * @author Admin
 * REST Controller for managing recycling tips.
 * Provides endpoints for CRUD operations and filtering tips by difficulty.
 * 
 * Endpoints:
 * - POST /api/tips - Create new tip
 * - GET /api/tips - Get all tips
 * - GET /api/tips/{id} - Get tip by ID
 * - GET /api/tips/difficulty/{level} - Get tips by difficulty level
 * - PUT /api/tips/{id} - Update tip
 * - DELETE /api/tips/{id} - Delete tip
 */

@RestController
@RequestMapping("/api/tips")
public class RecyclingTipController {
    private final RecyclingTipService recyclingTipService;

    @Autowired
    public RecyclingTipController(RecyclingTipService recyclingTipService) {
        this.recyclingTipService = recyclingTipService;
    }

    @PostMapping
    public ResponseEntity<RecyclingTip> createTip(@Valid @RequestBody RecyclingTip tip) {
        RecyclingTip createdTip = recyclingTipService.createTip(tip);
        return new ResponseEntity<>(createdTip, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTip>> getAllTips() {
        List<RecyclingTip> tips = recyclingTipService.getAllTips();
        return ResponseEntity.ok(tips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTip> getTipById(@PathVariable Long id) {
        return recyclingTipService.getTipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/difficulty/{level}")
    public ResponseEntity<List<RecyclingTip>> getTipsByDifficulty(@PathVariable String level) {
        List<RecyclingTip> tips = recyclingTipService.getTipsByDifficulty(level);
        return ResponseEntity.ok(tips);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTip> updateTip(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTip tip) {
        try {
            RecyclingTip updatedTip = recyclingTipService.updateTip(id, tip);
            return ResponseEntity.ok(updatedTip);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
        try {
            recyclingTipService.deleteTip(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}