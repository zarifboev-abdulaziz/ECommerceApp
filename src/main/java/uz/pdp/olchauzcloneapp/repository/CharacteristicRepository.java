package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
