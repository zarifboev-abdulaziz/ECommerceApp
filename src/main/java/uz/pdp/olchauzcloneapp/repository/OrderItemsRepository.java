package uz.pdp.olchauzcloneapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {

 /*   @Query(nativeQuery = true,value = "select *\n" +
            "from order_items o\n" +
            "where o.created_by =:userId and o.order_status =: status")
    List<OrderItem> allProductByUserIdAndStatus(Long userId, OrderStatus status);*/
    List<OrderItem>findByCreatedByAndOrderStatus(Long createdBy, OrderStatus orderStatus);
}
