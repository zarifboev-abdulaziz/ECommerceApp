package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.projection.SearchProductProjection;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "select p.id as productId,\n" +
            "                   p.name as productName,\n" +
            "                   p.price as productPrice,\n" +
            "                   p.cover_image_id as productPhotoId,\n" +
            "                   c.id as categoryId,\n" +
            "                   c.name as categoryName\n" +
            "            from products p\n" +
            "            join categories c on c.id = p.category_id\n" +
            "            where c.id =:categoryId AND lower(p.name) like lower(concat('%', :search, '%'))")
    Page<ViewProductProjection> getProductsByCategory(Pageable pageable, Long categoryId, String search);



}
