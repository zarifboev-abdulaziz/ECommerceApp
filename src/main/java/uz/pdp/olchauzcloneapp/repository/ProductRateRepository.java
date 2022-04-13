package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.ProductRating;
import uz.pdp.olchauzcloneapp.projection.ProductRateProjection;

import java.util.List;

public interface ProductRateRepository extends JpaRepository<ProductRating, Long> {

        @Query(nativeQuery = true,value = "select pr.rate as rate ,pr.comment as comment,u.full_name as name\n" +
                "from product_ratings pr\n" +
                "join products p on p.id = pr.product_id\n" +
                "join users u on p.created_by = u.created_by\n" +
                "where pr.product_id =:product_id\n" +
                "group by pr.comment, p.name, u.full_name, pr.rate")
        List<ProductRateProjection> getProductRating(Long product_id);

}
