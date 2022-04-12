package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.UserDto;
import uz.pdp.olchauzcloneapp.entity.Gender;
import uz.pdp.olchauzcloneapp.entity.Role;
import uz.pdp.olchauzcloneapp.entity.User;
import uz.pdp.olchauzcloneapp.repository.GenderRepository;
import uz.pdp.olchauzcloneapp.repository.RoleRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GenderRepository genderRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse editUser(Long id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();

        Optional<Gender> optionalGender = genderRepository.findById(userDto.getGenderId());
        if (!optionalGender.isPresent()) {
            return new ApiResponse("Gender not found", false);
        }
        Gender gender = optionalGender.get();

        user.setGender(gender);
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("Success", true);
    }
}
