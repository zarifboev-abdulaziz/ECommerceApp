package uz.pdp.olchauzcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StreetDto {

    private String name;
    private Integer apartmentNumber;
    private Integer flatNumber;
    private Integer entranceNumber;
    private Integer floor;
    private long districtId;
}
