package uz.pdp.olchauzcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacteristicDto {
    private String name;

    private Long categoryId;
}
