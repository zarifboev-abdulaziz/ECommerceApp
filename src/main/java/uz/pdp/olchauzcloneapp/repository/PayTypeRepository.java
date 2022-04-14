package uz.pdp.olchauzcloneapp.repository;

//Asilbek Fayzullayev 12.04.2022 9:20   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.PayType;
import java.util.Optional;

public interface PayTypeRepository extends JpaRepository<PayType,Long> {
    Optional<PayType> findByName(String name);
}
