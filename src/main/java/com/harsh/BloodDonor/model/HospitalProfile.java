package com.harsh.BloodDonor.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class HospitalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "users", nullable = false)
    private User user;
    private String hospitalName;
    private String contactNumber;

    private double latitude;
    private double longitude;
}
