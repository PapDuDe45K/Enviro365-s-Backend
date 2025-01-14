/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.config;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.*;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.RecyclingTip;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.RecyclingTip;

@Configuration
public class DataLoader {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(
            WasteCategoryRepository categoryRepository,
            DisposalGuidelineRepository guidelineRepository,
            RecyclingTipRepository tipRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                log.info("Loading sample data...");

                // Create Categories
                WasteCategory plastic = new WasteCategory();
                plastic.setName("Plastic");
                plastic.setDescription("All types of plastic materials including bottles, containers, and packaging");
                plastic = categoryRepository.save(plastic);
                log.info("Created category:", plastic.getName());

                WasteCategory glass = new WasteCategory();
                glass.setName("Glass");
                glass.setDescription("Glass bottles, jars, containers, and broken glass items");
                glass = categoryRepository.save(glass);
                log.info("Created category: ", glass.getName());

                WasteCategory paper = new WasteCategory();
                paper.setName("Paper");
                paper.setDescription("Newspapers, magazines, cardboard boxes, and paper packaging");
                paper = categoryRepository.save(paper);
                log.info("Created category: ", paper.getName());

                WasteCategory electronic = new WasteCategory();
                electronic.setName("Electronic Waste");
                electronic.setDescription("Old electronics, batteries, cables, and electronic devices");
                electronic = categoryRepository.save(electronic);
                log.info("Created category: ", electronic.getName());

                WasteCategory organic = new WasteCategory();
                organic.setName("Organic Waste");
                organic.setDescription("Food waste, garden waste, and biodegradable materials");
                organic = categoryRepository.save(organic);
                log.info("Created category: ", organic.getName());

                // Create Guidelines
                DisposalGuideline plasticBottles = new DisposalGuideline();
                plasticBottles.setTitle("Plastic Bottle Recycling");
                plasticBottles.setDescription("Clean and crush plastic bottles before disposal. Remove caps and labels if possible.");
                plasticBottles.setCategory(plastic);
                plasticBottles.setDisposalMethod("Recycling Bin");
                plasticBottles.setSafetyPrecautions("Ensure bottles are empty and clean");
                guidelineRepository.save(plasticBottles);
                log.info("Created guideline: ", plasticBottles.getTitle());

                DisposalGuideline glassJars = new DisposalGuideline();
                glassJars.setTitle("Glass Container Disposal");
                glassJars.setDescription("Carefully handle glass items. Rinse containers before recycling.");
                glassJars.setCategory(glass);
                glassJars.setDisposalMethod("Glass Recycling Bin");
                glassJars.setSafetyPrecautions("Handle with care, use gloves for broken glass");
                guidelineRepository.save(glassJars);
                log.info("Created guideline: ", glassJars.getTitle());

                DisposalGuideline cardboardBoxes = new DisposalGuideline();
                cardboardBoxes.setTitle("Cardboard Recycling");
                cardboardBoxes.setDescription("Flatten boxes to save space. Remove any non-paper materials.");
                cardboardBoxes.setCategory(paper);
                cardboardBoxes.setDisposalMethod("Paper Recycling Bin");
                cardboardBoxes.setSafetyPrecautions("Remove staples and tape");
                guidelineRepository.save(cardboardBoxes);
                log.info("Created guideline: ", cardboardBoxes.getTitle());

                DisposalGuideline batteries = new DisposalGuideline();
                batteries.setTitle("Battery Disposal");
                batteries.setDescription("Never dispose of batteries in regular trash. Use designated collection points.");
                batteries.setCategory(electronic);
                batteries.setDisposalMethod("E-Waste Collection Point");
                batteries.setSafetyPrecautions("Keep away from heat and water");
                guidelineRepository.save(batteries);
                log.info("Created guideline: {}", batteries.getTitle());

                // Create Tips
                RecyclingTip bottleCrushing = new RecyclingTip();
                bottleCrushing.setTitle("Efficient Bottle Crushing");
                bottleCrushing.setDescription("Crush bottles lengthwise to save more space in recycling bins");
                bottleCrushing.setDifficultyLevel("EASY");
                tipRepository.save(bottleCrushing);
                log.info("Created tip", bottleCrushing.getTitle());

                RecyclingTip paperSorting = new RecyclingTip();
                paperSorting.setTitle("Paper Sorting Guide");
                paperSorting.setDescription("Sort paper by type: newspapers, magazines, and cardboard separately");
                paperSorting.setDifficultyLevel("EASY");
                tipRepository.save(paperSorting);
                log.info("Created tip ", paperSorting.getTitle());

                RecyclingTip composting = new RecyclingTip();
                composting.setTitle("Home Composting");
                composting.setDescription("Create a composting system for organic waste to reduce landfill impact");
                composting.setDifficultyLevel("MEDIUM");
                tipRepository.save(composting);
                log.info("Created tip: ", composting.getTitle());

                RecyclingTip electronicRepair = new RecyclingTip();
                electronicRepair.setTitle("Electronics Repair");
                electronicRepair.setDescription("Consider repairing electronics before disposing of them");
                electronicRepair.setDifficultyLevel("HARD");
                tipRepository.save(electronicRepair);
                log.info("Created tip: ", electronicRepair.getTitle());

                log.info("Sample data loading complete!");
            } else {
                log.info("Database already contains data - skipping sample data loading");
            }
        };
    }
}