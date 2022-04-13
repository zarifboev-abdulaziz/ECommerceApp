package uz.pdp.olchauzcloneapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

 /*   @Query(nativeQuery = true,value = "select *\n" +
            "from order_items o\n" +
            "where o.created_by =:userId and o.order_status =: status")
    List<OrderItem> allProductByUserIdAndStatus(Long userId, OrderStatus status);*/
    List<OrderItem> findAllByCreatedByAndOrderStatus(Long createdBy, OrderStatus orderStatus);

    void deleteAllByCreatedByAndOrderStatus(Long createdBy, OrderStatus orderStatus);

    boolean existsByCreatedByAndProductIdAndOrderStatus(Long createdBy, Long product_id, OrderStatus orderStatus);

    void deleteByCreatedByAndOrderStatus(Long createdBy, OrderStatus orderStatus);

    void deleteByProductId(Long productId);


}
