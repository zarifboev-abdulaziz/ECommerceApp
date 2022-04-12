package uz.pdp.olchauzcloneapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

// Zuhridin Bakhriddinov 4/12/2022 6:05 AM
@Data
public class LoginDto {
    @NotNull
    String password;

    @NotNull
    String email;
}
