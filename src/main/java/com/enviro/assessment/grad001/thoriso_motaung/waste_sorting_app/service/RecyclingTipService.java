/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.service;

import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.model.RecyclingTip;
import com.enviro.assessment.grad001.thoriso_motaung.waste_sorting_app.repository.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class RecyclingTipService {
    private final RecyclingTipRepository recyclingTipRepository;

    @Autowired
    public RecyclingTipService(RecyclingTipRepository recyclingTipRepository) {
        this.recyclingTipRepository = recyclingTipRepository;
    }

    public RecyclingTip createTip(RecyclingTip tip) {
        return recyclingTipRepository.save(tip);
    }

    public List<RecyclingTip> getAllTips() {
        return recyclingTipRepository.findAll();
    }

    public Optional<RecyclingTip> getTipById(Long id) {
        return recyclingTipRepository.findById(id);
    }

    public List<RecyclingTip> getTipsByDifficulty(String difficultyLevel) {
        return recyclingTipRepository.findByDifficultyLevel(difficultyLevel);
    }

    public RecyclingTip updateTip(Long id, RecyclingTip tip) {
        if (!recyclingTipRepository.existsById(id)) {
            throw new RuntimeException("Tip not found");
        }
        tip.setId(id);
        return recyclingTipRepository.save(tip);
    }

    public void deleteTip(Long id) {
        recyclingTipRepository.deleteById(id);
    }
}
