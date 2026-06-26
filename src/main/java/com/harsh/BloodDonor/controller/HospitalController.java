package com.harsh.BloodDonor.controller;

import com.harsh.BloodDonor.dto.BloodRequestDto;
import com.harsh.BloodDonor.model.DonorProfile;
import com.harsh.BloodDonor.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    private MatchingService matchingService;

    public HospitalController(MatchingService matchingService) {
        this.matchingService=matchingService;
    }

    @PostMapping("/requests")
    public ResponseEntity<List<DonorProfile>> createBloodRequest(@RequestBody BloodRequestDto bloodRequestDto){
        List<DonorProfile> donorProfiles=matchingService.createRequestAndFindMatches(
                bloodRequestDto.getHospitalUserId(),
                bloodRequestDto.getBloodGroupNeeded(),
                bloodRequestDto.getUrgency(),
                bloodRequestDto.getMaxRadiusKm()
        );
        return new ResponseEntity<>(donorProfiles, HttpStatus.FOUND);
    }
}
