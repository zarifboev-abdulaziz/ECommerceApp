package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.address.Street;

public interface StreetRepository extends JpaRepository<Street, Long> {

    Street findStreetByName(String streetName);
}
