package uz.pdp.olchauzcloneapp.dto;

//Asilbek Fayzullayev 12.04.2022 11:02   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    Long productId;
    Integer quantity;
}
