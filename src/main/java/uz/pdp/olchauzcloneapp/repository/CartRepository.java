package uz.pdp.olchauzcloneapp.repository;

//Asilbek Fayzullayev 12.04.2022 10:59   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.projection.ViewCartProjection;

import java.util.List;

public interface CartRepository extends JpaRepository<OrderItem, Long> {
    @Query(nativeQuery = true,value = "select p.id as productId,\n" +
            "       p.name as productName,\n" +
            "       p.discount as discount,\n" +
            "       p.price as productPrice,\n" +
            "       p.cover_image_id as ProductPhotoId,\n" +
            "       oi.quantity as quantity\n" +
            "       from order_items oi\n" +
            "join products p on oi.product_id = p.id\n" +
            "join categories c on p.category_id = c.id")
    List<ViewCartProjection> getAllCart();

    @Query(nativeQuery = true,value = "delete from order_items")
    void deleteAll();
}
