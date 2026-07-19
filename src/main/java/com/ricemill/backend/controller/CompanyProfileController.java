package com.ricemill.backend.controller;

import com.ricemill.backend.entity.CompanyProfile;
import com.ricemill.backend.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company-profile")
@CrossOrigin(origins = "*")
public class CompanyProfileController {

    @Autowired
    private CompanyProfileService companyProfileService;

    /**
     * Get Company Profile by User ID
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCompanyProfile(@PathVariable Long userId) {

        CompanyProfile profile =
                companyProfileService.getCompanyProfile(userId);

        if (profile == null) {

            CompanyProfile emptyProfile = new CompanyProfile();

            emptyProfile.setUserId(userId);

            return ResponseEntity.ok(emptyProfile);

        }

        return ResponseEntity.ok(profile);
    }

    /**
     * Create Company Profile
     */
    @PostMapping
    public ResponseEntity<?> createCompanyProfile(
            @RequestBody CompanyProfile profile) {

        try {

            CompanyProfile savedProfile =
                    companyProfileService.createCompanyProfile(profile);

            return ResponseEntity.ok(savedProfile);

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest()
                    .body(ex.getMessage());

        }
    }

    /**
     * Update Company Profile
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateCompanyProfile(
            @PathVariable Long userId,
            @RequestBody CompanyProfile profile) {

        try {

            CompanyProfile updatedProfile =
                    companyProfileService.updateCompanyProfile(
                            userId,
                            profile
                    );

            return ResponseEntity.ok(updatedProfile);

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest()
                    .body(ex.getMessage());

        }
    }

}