package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.payload.LoginDto;
import uz.pdp.olchauzcloneapp.payload.RegisterDto;
import uz.pdp.olchauzcloneapp.repository.PermissionRepository;
import uz.pdp.olchauzcloneapp.repository.RoleRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;
import uz.pdp.olchauzcloneapp.service.AuthService;


// Zuhridin Bakhriddinov 4/8/2022 10:05 AM
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        return authService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);

    }


}
