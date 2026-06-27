package com.harsh.BloodDonor.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DonorRegistrationDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password should be minimum of 6 length")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Blood Group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message="Blood Group should be A+, A-, B+, B-, AB+, AB-, O+, O-")
    private String bloodGroup;

    @Min(value = -90, message = "Latitude must be between -90 to 90")
    @Max(value = 90, message = "Latitude must be between -90 to 90")
    private double latitude;

    @Min(value = -180, message = "Longitude must be between -180 to 180")
    @Max(value = 180, message = "Longitude must be between -180 to 180")
    private double longitude;
}
