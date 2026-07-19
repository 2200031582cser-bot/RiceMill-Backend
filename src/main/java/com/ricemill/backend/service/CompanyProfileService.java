package com.ricemill.backend.service;

import com.ricemill.backend.entity.CompanyProfile;
import com.ricemill.backend.repository.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyProfileService {

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    /**
     * Get company profile by userId
     */
    public CompanyProfile getCompanyProfile(Long userId) {

        Optional<CompanyProfile> profile =
                companyProfileRepository.findByUserId(userId);

        return profile.orElse(null);
    }

    /**
     * Create company profile
     */
    public CompanyProfile createCompanyProfile(CompanyProfile profile) {

        Optional<CompanyProfile> existingProfile =
                companyProfileRepository.findByUserId(profile.getUserId());

        if (existingProfile.isPresent()) {
            throw new RuntimeException("Company Profile already exists for this user.");
        }

        return companyProfileRepository.save(profile);
    }

    /**
     * Update company profile
     */
    public CompanyProfile updateCompanyProfile(Long userId,
                                               CompanyProfile updatedProfile) {

        Optional<CompanyProfile> existing =
                companyProfileRepository.findByUserId(userId);

        if (existing.isEmpty()) {
            throw new RuntimeException("Company Profile not found.");
        }

        CompanyProfile profile = existing.get();

        profile.setCompanyName(updatedProfile.getCompanyName());
        profile.setOwnerName(updatedProfile.getOwnerName());
        profile.setGstNumber(updatedProfile.getGstNumber());
        profile.setPanNumber(updatedProfile.getPanNumber());

        profile.setAddress(updatedProfile.getAddress());
        profile.setCity(updatedProfile.getCity());
        profile.setDistrict(updatedProfile.getDistrict());
        profile.setState(updatedProfile.getState());
        profile.setPincode(updatedProfile.getPincode());

        profile.setPhone(updatedProfile.getPhone());
        profile.setEmail(updatedProfile.getEmail());
        profile.setWebsite(updatedProfile.getWebsite());

        profile.setBankName(updatedProfile.getBankName());
        profile.setAccountNumber(updatedProfile.getAccountNumber());
        profile.setIfsc(updatedProfile.getIfsc());
        profile.setBranch(updatedProfile.getBranch());
        profile.setUpiId(updatedProfile.getUpiId());

        profile.setLogoPath(updatedProfile.getLogoPath());
        profile.setSignaturePath(updatedProfile.getSignaturePath());
        profile.setSealPath(updatedProfile.getSealPath());

        return companyProfileRepository.save(profile);
    }

}