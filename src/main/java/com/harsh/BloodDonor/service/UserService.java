package com.harsh.BloodDonor.service;

import com.harsh.BloodDonor.dto.DonorRegistrationDto;
import com.harsh.BloodDonor.dto.HospitalRegistrationDto;
import com.harsh.BloodDonor.model.DonorProfile;
import com.harsh.BloodDonor.model.HospitalProfile;
import com.harsh.BloodDonor.model.Role;
import com.harsh.BloodDonor.model.User;
import com.harsh.BloodDonor.repository.DonorRepo;
import com.harsh.BloodDonor.repository.HospitalRepo;
import com.harsh.BloodDonor.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final DonorRepo donorRepo;
    private final HospitalRepo hospitalRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(DonorRepo donorRepo, HospitalRepo hospitalRepo, UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.donorRepo=donorRepo;
        this.userRepo=userRepo;
        this.hospitalRepo=hospitalRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registerDonor(DonorRegistrationDto donorDto) {
        if(userRepo.findByEmail(donorDto.getEmail()).isPresent()){
            throw new RuntimeException("Email is already register!");
        }

        User user=new User();
        user.setEmail(donorDto.getEmail());
        user.setPassword(passwordEncoder.encode(donorDto.getPassword()));
        user.setRole(Role.DONOR);
        User savedUser=userRepo.save(user);

        DonorProfile donorProfile=new DonorProfile();
        donorProfile.setUser(savedUser);
        donorProfile.setName((donorDto.getName()));
        donorProfile.setBloodGroup(donorDto.getBloodGroup());
        donorProfile.setLatitude(donorDto.getLatitude());
        donorProfile.setLongitude(donorDto.getLongitude());
        donorRepo.save(donorProfile);
        return "Donor register's successfully!";
    }

    @Transactional
    public String registerHospital(HospitalRegistrationDto hospitalDto) {
        if(userRepo.findByEmail(hospitalDto.getEmail()).isPresent()){
            throw new RuntimeException("Email is already register!");
        }
        User user=new User();
        user.setEmail(hospitalDto.getEmail());
        user.setPassword(passwordEncoder.encode(hospitalDto.getPassword()));
        user.setRole(Role.HOSPITAL);
        User savedUser=userRepo.save(user);

        HospitalProfile hospitalProfile=new HospitalProfile();
        hospitalProfile.setUser(savedUser);
        hospitalProfile.setHospitalName((hospitalDto.getHospitalName()));
        hospitalProfile.setContactNumber(hospitalDto.getContactNumber());
        hospitalProfile.setLatitude(hospitalDto.getLatitude());
        hospitalProfile.setLongitude(hospitalDto.getLongitude());
        hospitalRepo.save(hospitalProfile);
        return "Hospital register's successfully!";

    }
}
