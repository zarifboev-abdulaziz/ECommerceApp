package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.address.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

    District findDistrictByName(String districtName);
}
