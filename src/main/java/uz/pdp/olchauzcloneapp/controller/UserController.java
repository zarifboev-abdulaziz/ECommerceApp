package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.UserDto;
import uz.pdp.olchauzcloneapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/{userId}")
    public HttpEntity<?> editUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.editUser(userId, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
