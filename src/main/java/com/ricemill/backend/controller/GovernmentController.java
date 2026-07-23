package com.ricemill.backend.controller;

import com.ricemill.backend.entity.GovernmentLedger;

import com.ricemill.backend.repository.GovernmentLedgerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/government")
public class GovernmentController {

    @Autowired
    private GovernmentLedgerRepository ledgerRepository;


    @PostMapping("/receive-paddy")
    public GovernmentLedger receivePaddy(
            @RequestBody GovernmentLedger request
    ) {

        GovernmentLedger existing =
                ledgerRepository
                        .findByUserIdAndCropSeasonAndCropYear(
                                request.getUserId(),
                                request.getCropSeason(),
                                request.getCropYear()
                        );

        if(existing != null) {

            existing.setPaddyReceived(
                    existing.getPaddyReceived()
                            + request.getPaddyReceived()
            );

            return ledgerRepository.save(
                    existing
            );
        }

        request.setRiceDelivered(0.0);

        return ledgerRepository.save(
                request
        );
    }

    @GetMapping("/user/{userId}")
    public List<GovernmentLedger> getByUserId(
            @PathVariable Long userId
    ) {

        return ledgerRepository.findByUserId(
                userId
        );
    }

    @PutMapping("/deliver-rice")
    public GovernmentLedger deliverRice(
            @RequestBody Map<String,Object> body
    ) {

        Long userId =
                Long.valueOf(
                        body.get("userId")
                                .toString()
                );

        String cropSeason =
                body.get("cropSeason")
                        .toString();

        String cropYear =
                body.get("cropYear")
                        .toString();

        Double riceDelivered =
                Double.valueOf(
                        body.get("riceDelivered")
                                .toString()
                );

        GovernmentLedger ledger =
                ledgerRepository
                        .findByUserIdAndCropSeasonAndCropYear(
                                userId,
                                cropSeason,
                                cropYear
                        );

        if(ledger == null) {

            throw new RuntimeException(
                    "Season Record Not Found"
            );
        }

        ledger.setRiceDelivered(
                ledger.getRiceDelivered()
                        + riceDelivered
        );

        return ledgerRepository.save(
                ledger
        );
    }

    @PutMapping("/{id}")
    public GovernmentLedger updateLedger(
            @PathVariable Long id,
            @RequestBody GovernmentLedger updated
    ) {

        GovernmentLedger ledger =
                ledgerRepository
                        .findById(id)
                        .orElseThrow();

        ledger.setCropSeason(
                updated.getCropSeason()
        );

        ledger.setCropYear(
                updated.getCropYear()
        );

        ledger.setPaddyReceived(
                updated.getPaddyReceived()
        );

        ledger.setRiceDelivered(
                updated.getRiceDelivered()
        );

        return ledgerRepository.save(
                ledger
        );
    }

}
