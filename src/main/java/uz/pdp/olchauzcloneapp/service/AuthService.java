package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.User;
import uz.pdp.olchauzcloneapp.payload.LoginDto;
import uz.pdp.olchauzcloneapp.payload.RegisterDto;
import uz.pdp.olchauzcloneapp.repository.RoleRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;
import uz.pdp.olchauzcloneapp.security.JwtProvider;


import java.util.Collections;

// Zuhridin Bakhriddinov 4/5/2022 11:45 AM
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public HttpEntity registerUser(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>(new ApiResponse("Wrong", false), HttpStatus.ALREADY_REPORTED);
        }
        User user = new User(registerDto.getFullName(), registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                Collections.singleton(roleRepository.findByName("ROLE_USER"))
        );


        userRepository.save(user);


        return new ResponseEntity<>(new ApiResponse("Successfully Registered.", true), HttpStatus.OK);


    }


    public HttpEntity login(LoginDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()
            ));

            User principal = (User) authentication.getPrincipal();
            String generatedToken = jwtProvider.generateToken(principal.getEmail(), true);
            return new ResponseEntity<>(new ApiResponse("Token", true, generatedToken), HttpStatus.OK);


        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApiResponse("Email or password not found", false), HttpStatus.NOT_FOUND);

        }
    }


}






