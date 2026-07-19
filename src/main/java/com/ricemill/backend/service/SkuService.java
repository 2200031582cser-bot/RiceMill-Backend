package com.ricemill.backend.service;

import com.ricemill.backend.entity.Sku;
import com.ricemill.backend.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

    public List<Sku> getAll(Long userId) {
        return skuRepository.findByUserId(userId);
    }

    public Sku save(Sku sku) {
        return skuRepository.save(sku);
    }

    public Sku update(Long id, Sku sku) {

        Sku existing = skuRepository.findById(id).orElse(null);

        if(existing == null)
            return null;

        existing.setSkuName(sku.getSkuName());
        existing.setCategory(sku.getCategory());
        existing.setUnit(sku.getUnit());
        existing.setDescription(sku.getDescription());
        existing.setActive(sku.getActive());

        return skuRepository.save(existing);
    }

    public void delete(Long id) {
        skuRepository.deleteById(id);
    }

}