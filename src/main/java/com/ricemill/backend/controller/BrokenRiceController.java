package com.ricemill.backend.controller;

import com.ricemill.backend.entity.BrokenRice;
import com.ricemill.backend.repository.BrokenRiceRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")

@RequestMapping("/brokenrice")

public class BrokenRiceController {

    @Autowired

    private BrokenRiceRepository brokenRiceRepository;

    // SAVE
    @GetMapping("/user/{userId}")
    public List<BrokenRice> getByUserId(
            @PathVariable Long userId
    ) {

        return brokenRiceRepository.findByUserId(
                userId
        );
    }

    @PostMapping

    public BrokenRice saveBrokenRice(
            @RequestBody BrokenRice brokenRice
    ) {

        return brokenRiceRepository.save(
                brokenRice
        );
    }

    // GET ALL

    @GetMapping

    public List<BrokenRice> getAllBrokenRice() {

        return brokenRiceRepository.findAll();
    }

    // DELETE

    @DeleteMapping("/{id}")

    public String deleteBrokenRice(
            @PathVariable Long id
    ) {

        brokenRiceRepository.deleteById(id);

        return "Deleted Successfully";
    }
}