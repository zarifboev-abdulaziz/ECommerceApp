package uz.pdp.olchauzcloneapp.entity.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.olchauzcloneapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "streets")
public class Street extends AbsEntity {
    @ManyToOne
    private District district;
    private String name;
    private Integer apartmentNumber;
    private Integer flatNumber;
    private Integer entranceNumber;
    private Integer floor;




}
