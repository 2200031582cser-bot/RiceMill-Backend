package com.ricemill.backend.service;

import com.ricemill.backend.entity.WeighBridgeEntry;
import com.ricemill.backend.repository.WeighBridgeEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeighBridgeEntryService {

    private final WeighBridgeEntryRepository repository;

    public WeighBridgeEntryService(WeighBridgeEntryRepository repository) {
        this.repository = repository;
    }

    public WeighBridgeEntry save(WeighBridgeEntry entry) {
        return repository.save(entry);
    }

    public List<WeighBridgeEntry> getAll(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}