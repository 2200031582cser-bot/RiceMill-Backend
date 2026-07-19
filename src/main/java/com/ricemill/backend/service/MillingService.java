package com.ricemill.backend.service;

import com.ricemill.backend.entity.MillingSession;
import com.ricemill.backend.repository.MillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MillingService {

    @Autowired
    private MillingRepository repository;

    public MillingSession save(MillingSession session){

        List<MillingSession> todaySessions =
                repository.findByUserIdAndDate(
                        session.getUserId(),
                        session.getDate()
                );

        session.setSessionNo(todaySessions.size()+1);

        return repository.save(session);
    }

    public List<MillingSession> getUserSessions(Long userId){

        return repository.findByUserId(userId);

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

}