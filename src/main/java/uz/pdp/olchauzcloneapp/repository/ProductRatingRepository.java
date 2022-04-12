package uz.pdp.olchauzcloneapp.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.ProductRating;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

}
