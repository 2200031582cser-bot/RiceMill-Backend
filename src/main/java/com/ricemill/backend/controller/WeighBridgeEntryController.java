package com.ricemill.backend.controller;

import com.ricemill.backend.entity.WeighBridgeEntry;
import com.ricemill.backend.service.WeighBridgeEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weighbridge")
@CrossOrigin(origins = "http://localhost:5173")
public class WeighBridgeEntryController {

    private final WeighBridgeEntryService service;

    public WeighBridgeEntryController(WeighBridgeEntryService service) {
        this.service = service;
    }

    // Save Entry
    @PostMapping
    public WeighBridgeEntry saveEntry(
            @RequestBody WeighBridgeEntry entry) {

        return service.save(entry);

    }

    // Get User Entries
    @GetMapping("/user/{userId}")
    public List<WeighBridgeEntry> getEntries(
            @PathVariable Long userId) {

        return service.getAll(userId);

    }

    // Delete Entry
    @DeleteMapping("/{id}")
    public void deleteEntry(
            @PathVariable Long id) {

        service.delete(id);

    }

}