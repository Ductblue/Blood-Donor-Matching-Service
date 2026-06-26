package com.harsh.BloodDonor.dto;

import lombok.Data;

@Data
public class HospitalRegistrationDto {
    private String email;
    private String password;
    private String hospitalName;
    private String contactNumber;
    private double latitude;
    private double longitude;
}
