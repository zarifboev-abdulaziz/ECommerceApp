package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.projection.ProductCharacteristics;
import uz.pdp.olchauzcloneapp.projection.SearchProductProjection;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;

import java.util.List;
import java.util.Optional;

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

    @Query(nativeQuery = true, value = "select c.name as characteristic," +
            " v.values as characteristicValue \n" +
            "from products p\n" +
            "join products_characteristics_values pcv on p.id = pcv.products_id\n" +
            "join characteristics_values cv on pcv.characteristics_values_id = cv.id\n" +
            "join characteristics c on c.id = cv.characteristic_id\n" +
            "join values v on cv.value_id = v.id\n" +
            "where p.id =:productId")
    List<ProductCharacteristics> getProductCharacteristics(Long productId);




}
