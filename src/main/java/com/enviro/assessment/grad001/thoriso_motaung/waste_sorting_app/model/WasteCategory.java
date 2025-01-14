package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity representing a waste category in the system.
 *  Examples: Plastic, Paper, Glass, Metal, etc.
 * This class follows best practices with:
 * - Proper JPA annotations for database mapping
 * - Input validation using Jakarta Validation
 * - Lombok for reducing boilerplate code
 * - Clear relationship mappings with related entities
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteCategory {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(unique = true)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

     @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<DisposalGuideline> guidelines;
}
