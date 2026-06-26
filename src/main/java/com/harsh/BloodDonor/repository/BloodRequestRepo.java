package com.harsh.BloodDonor.repository;

import com.harsh.BloodDonor.model.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRequestRepo extends JpaRepository<BloodRequest, Integer> {
}
