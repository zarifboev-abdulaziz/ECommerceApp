package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.CharacteristicsValues;

public interface CharacteristicsValuesRepository extends JpaRepository<CharacteristicsValues,
        Long> {
}
