package uz.pdp.olchauzcloneapp.dto;

import lombok.Data;
import uz.pdp.olchauzcloneapp.entity.address.District;

import javax.persistence.ManyToOne;

@Data
public class AddressDto {
    private Long regionId;
    private Long districtId;
    private String streetName;
    private Integer apartmentNumber;
    private Integer flatNumber;
    private Integer entranceNumber;
    private Integer floor;


}
