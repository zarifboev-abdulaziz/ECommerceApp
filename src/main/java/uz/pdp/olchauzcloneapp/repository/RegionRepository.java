package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.address.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findRegionByName(String regionName);
}
