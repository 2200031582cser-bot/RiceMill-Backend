package com.ricemill.backend.controller;

import com.ricemill.backend.entity.MillingSession;
import com.ricemill.backend.service.MillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milling")
@CrossOrigin(origins="http://localhost:5173")
public class MillingController {

    @Autowired
    private MillingService service;

    @PostMapping

    public MillingSession save(

            @RequestBody MillingSession session

    ){

        return service.save(session);

    }

    @GetMapping("/user/{id}")

    public List<MillingSession> getUser(

            @PathVariable Long id

    ){

        return service.getUserSessions(id);

    }

    @DeleteMapping("/{id}")

    public void delete(

            @PathVariable Long id

    ){

        service.delete(id);

    }

}