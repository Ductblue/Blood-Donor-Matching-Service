package com.harsh.BloodDonor.dto;

import lombok.Data;

@Data
public class DonorRegistrationDto {
    private String email;
    private String password;
    private String name;
    private String bloodGroup;
    private double latitude;
    private double longitude;
}
