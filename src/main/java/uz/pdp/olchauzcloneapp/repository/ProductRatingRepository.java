package uz.pdp.olchauzcloneapp.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.ProductRating;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

    Integer countByProductId(Long product_id);

    @Query(nativeQuery = true, value = "select\n" +
            "    CASE\n" +
            "     WHEN avg(pr.rate) is null  THEN 0\n" +
            "     ELSE  avg(pr.rate)\n" +
            "    END\n" +
            "from product_ratings pr\n" +
            "where pr.product_id =:productId")
    Double getAverageRatingByProductId(Long productId);

}
