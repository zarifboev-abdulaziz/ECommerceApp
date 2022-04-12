package uz.pdp.olchauzcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String fullName;

    private String phoneNumber;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private Long genderId;
}
