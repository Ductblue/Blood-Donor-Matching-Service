package com.harsh.BloodDonor.repository;

import com.harsh.BloodDonor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
//we have used optional as it may or may not return null value coz we don't want the nullpointer exception error
}
