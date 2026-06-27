package com.harsh.BloodDonor.controller;

import com.harsh.BloodDonor.dto.AuthRequestDto;
import com.harsh.BloodDonor.dto.DonorRegistrationDto;
import com.harsh.BloodDonor.dto.HospitalRegistrationDto;
import com.harsh.BloodDonor.model.User;
import com.harsh.BloodDonor.repository.UserRepo;
import com.harsh.BloodDonor.security.JwtUtil;
import com.harsh.BloodDonor.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public AuthController(UserService userService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder,
                          UserRepo userRepo){
        this.userService=userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @PostMapping("/register/donor")
    public ResponseEntity<String> registerDonor(@Valid @RequestBody DonorRegistrationDto donorDto){
        String result = userService.registerDonor(donorDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/register/hospital")
    public ResponseEntity<String> registerHospital(@Valid @RequestBody HospitalRegistrationDto hospitalDto){
        String result = userService.registerHospital(hospitalDto);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequestDto authRequestDto){
        User user=userRepo.findByEmail(authRequestDto.getEmail()).orElseThrow(
                ()->new RuntimeException("Wrong Email")
        );

        if(!passwordEncoder.matches(user.getPassword(), authRequestDto.getPassword())){
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }

        String token= jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new ResponseEntity<>("Token Created",HttpStatus.CREATED);
    }
}
