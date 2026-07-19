package com.ricemill.backend.controller;

import com.ricemill.backend.entity.PaddyProcurement;
import com.ricemill.backend.repository.PaddyProcurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/paddy")
@CrossOrigin("*")
public class PaddyProcurementController {

    @Autowired
    private PaddyProcurementRepository repository;

    @GetMapping("/user/{userId}")
    public List<PaddyProcurement> getByUserId(
            @PathVariable Long userId
    ){
        return repository.findByUserId(userId);
    }

    @PostMapping
    public PaddyProcurement savePaddy(
            @RequestBody PaddyProcurement paddyProcurement) {

        return repository.save(paddyProcurement);
    }

    @GetMapping
    public List<PaddyProcurement> getAllPaddy() {

        return repository.findAll();
    }

    @DeleteMapping("/{id}")

    public void deletePaddy(
            @PathVariable Long id
    ) {

        repository.deleteById(id);
    }
}