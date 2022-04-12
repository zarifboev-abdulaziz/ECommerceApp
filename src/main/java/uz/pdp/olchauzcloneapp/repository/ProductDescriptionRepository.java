package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.ProductDescription;

import java.util.Optional;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {
    Optional<ProductDescription> findByProductId(Long product_id);
}
