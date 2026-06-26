package com.harsh.BloodDonor.repository;

import com.harsh.BloodDonor.model.HospitalProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepo extends JpaRepository<HospitalProfile, Integer> {

}
