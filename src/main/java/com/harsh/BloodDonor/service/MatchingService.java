package com.harsh.BloodDonor.service;

import com.harsh.BloodDonor.model.BloodRequest;
import com.harsh.BloodDonor.model.DonorProfile;
import com.harsh.BloodDonor.model.HospitalProfile;
import com.harsh.BloodDonor.model.UrgencyLevel;
import com.harsh.BloodDonor.repository.BloodRequestRepo;
import com.harsh.BloodDonor.repository.DonorRepo;
import com.harsh.BloodDonor.repository.HospitalRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchingService {

    private BloodCompatibilityMatrix bloodCompatibilityMatrix;
    private DonorRepo donorRepo;
    private HospitalRepo hospitalRepo;
    private BloodRequestRepo bloodRequestRepo;

    public MatchingService(BloodCompatibilityMatrix bloodCompatibilityMatrix, DonorRepo donorRepo, HospitalRepo hospitalRepo,BloodRequestRepo bloodRequestRepo){
        this.bloodCompatibilityMatrix=bloodCompatibilityMatrix;
        this.bloodRequestRepo=bloodRequestRepo;
        this.donorRepo=donorRepo;
        this.hospitalRepo=hospitalRepo;
    }

    @Transactional
    public List<DonorProfile> createRequestAndFindMatches(int hospitalUserId, String bloodGroup, String urgency, double maxRadiusKm){
        // finding the hospital
        HospitalProfile hospitalProfile=hospitalRepo.findById(hospitalUserId)
                .orElseThrow(()-> new RuntimeException("Hospital Not Found!"));

        // finding the urgency Level
        UrgencyLevel urgencyLevel=UrgencyLevel.valueOf(urgency.toUpperCase());

        // Storing the info of Blood Request record
        BloodRequest bloodRequest=new BloodRequest();
        bloodRequest.setHospitalProfile(hospitalProfile);
        bloodRequest.setBloodGroupNeeded(bloodGroup);
        bloodRequest.setUrgencyLevel(urgencyLevel);

        double maxRadiusInMeter=maxRadiusKm*1000.0;

        // Getting the list of compatible list of Blood group
        List<String> compatibleGroup=bloodCompatibilityMatrix.getCompatibleDonorGroups(bloodGroup);

        // finding the donor on the basis of compatible list of blood group
        //List<DonorProfile> matched=donorRepo.findByIsAvailableTrueAndBloodGroupIn(compatibleGroup);

        List<DonorProfile> matched=donorRepo.findNearbyCompatibleDonors(
                compatibleGroup,
                hospitalProfile.getLongitude(),
                hospitalProfile.getLatitude(),
                maxRadiusInMeter);

        return matched;

    }
}
