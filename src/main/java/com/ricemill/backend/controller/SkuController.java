package com.ricemill.backend.controller;

import com.ricemill.backend.entity.Sku;
import com.ricemill.backend.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")

public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/user/{userId}")
    public List<Sku> getAll(@PathVariable Long userId){
        return skuService.getAll(userId);
    }

    @PostMapping
    public Sku save(@RequestBody Sku sku){
        return skuService.save(sku);
    }

    @PutMapping("/{id}")
    public Sku update(@PathVariable Long id,
                      @RequestBody Sku sku){

        return skuService.update(id, sku);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        skuService.delete(id);
    }

}
