package com.harsh.BloodDonor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BloodRequestDto {

    @NotNull(message = "Hospital user id is required")
    private int hospitalUserId;

    @NotBlank(message = "Blood Group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message="Blood Group should be A+, A-, B+, B-, AB+, AB-, O+, O-")
    private String bloodGroupNeeded;

    @NotBlank(message = "Urgency Level is required")
    @Pattern(regexp = "^(?i)(URGENT|ROUTINE|CRITICAL)$", message = "Urgency Level must be ROUTINE, URGENT or CRITICAL")
    private String urgency;

    @Positive(message = "Maximum radius must be greater than 0")
    private double maxRadiusKm;
}
