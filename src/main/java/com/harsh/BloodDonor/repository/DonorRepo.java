package com.harsh.BloodDonor.repository;

import com.harsh.BloodDonor.model.DonorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepo extends JpaRepository<DonorProfile, Integer> {

    List<DonorProfile> findByIsAvailableTrueAndBloodGroupIn(List<String> compitableGroup);

    @Query(value="select * from donor_profile d "+
    "where d.is_available=true "+
    "and d.blood_group in (:compatibleGroups) "+
    "and ST_Distance_Sphere(POINT(d.longitude, d.latitude), POINT(:hospitalLng, :hospitalLat))<= :radiusInMeters",
    nativeQuery = true)
    List<DonorProfile> findNearbyCompatibleDonors(
        @Param("compatibleGroups") List<String> compatibleGroups,
        @Param("hospitalLng") double hospitalLng,
        @Param("hospitalLat") double hospitalLat,
        @Param("radiusInMeters") double radiusInMeters
        );
}
