package com.harsh.BloodDonor.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DonorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "users", nullable = false)
    @OneToOne
    private User user;
    private String name;

    private String bloodGroup;
    private boolean isAvailable;

    private double longitude;
    private double latitude;
}
