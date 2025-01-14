/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model;

/**
 *
 * @author Admin
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity representing disposal guidelines for specific waste categories.
 * Contains specific instructions for waste disposal.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "disposal_guidelines")
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @JsonBackReference 
    @NotNull(message = "Category is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory category;

    @NotBlank(message = "Disposal method is required")
    @Size(max = 100, message = "Disposal method cannot exceed 100 characters")
    private String disposalMethod;

    @Size(max = 200, message = "Safety precautions cannot exceed 200 characters")
    private String safetyPrecautions;
}
