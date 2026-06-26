package com.harsh.BloodDonor.dto;

import lombok.Data;

@Data
public class BloodRequestDto {
    private int hospitalUserId;
    private String bloodGroupNeeded;
    private String urgency;
    private double maxRadiusKm;
}
