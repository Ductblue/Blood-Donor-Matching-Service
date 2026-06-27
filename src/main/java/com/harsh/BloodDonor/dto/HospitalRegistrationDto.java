package com.harsh.BloodDonor.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HospitalRegistrationDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password should be minimum of 6 length")
    private String password;

    @NotBlank(message = "Name is required")
    private String hospitalName;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "//d{10}$", message = "Contact number must be of 10 digits")
    private String contactNumber;

    @Min(value = -90, message = "Latitude must be between -90 to 90")
    @Max(value = 90, message = "Latitude must be between -90 to 90")
    private double latitude;

    @Min(value = -180, message = "Longitude must be between -180 to 180")
    @Max(value = 180, message = "Longitude must be between -180 to 180")
    private double longitude;
}
