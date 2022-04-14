package uz.pdp.olchauzcloneapp.service;

//Asilbek Fayzullayev 12.04.2022 11:23   


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CartDto;
import uz.pdp.olchauzcloneapp.dto.OrderItemDto;
import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.entity.User;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;
import uz.pdp.olchauzcloneapp.repository.OrderItemsRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;

import java.util.*;

@Service
public class CartService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;


    public ApiResponse getAllCart(){

        List<OrderItem> orderItemsInTheCart = orderItemsRepository.findAllByCreatedByAndOrderStatus(1L, OrderStatus.NEW);

        if (orderItemsInTheCart.size() == 0){
            return new ApiResponse("Cart is empty",false);
        }

        List<Map<String, Object>> orderItems = new ArrayList<>();

        for (OrderItem orderItem : orderItemsInTheCart) {
            Product product = orderItem.getProduct();
            Map<String, Object> orderItemMap = new HashMap<>();
            orderItemMap.put("productId", product.getId());
            orderItemMap.put("orderItemId", orderItem.getId());
            orderItemMap.put("productName", product.getName());
            orderItemMap.put("productCoverImageId", product.getCoverImage().getId());
            orderItemMap.put("productPrice", product.getPrice());
            orderItemMap.put("quantity", orderItem.getQuantity());
            orderItems.add(orderItemMap);
        }

        return new ApiResponse("ok",true, orderItems);
    }

    @Transactional
    public ApiResponse clearCart(Long id){
        try {
            orderItemsRepository.deleteByProductId(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error in deleting", false);
        }
        return new ApiResponse("Successfully Deleted",true);
    }

    public ApiResponse addProductToCart(CartDto cartDto) {
//        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long currentUserId = currentUser.getId();
        Optional<User> optionalUser = userRepository.findById(1L);

        Optional<Product> optionalProduct = productRepository.findById(cartDto.getProductId());
        if (!optionalProduct.isPresent()) return new ApiResponse("Product not found", true);

        boolean existsInTheCart = orderItemsRepository.existsByCreatedByAndProductIdAndOrderStatus(1L, cartDto.getProductId(), OrderStatus.NEW);
        if (existsInTheCart){
            return new ApiResponse("This Product is already available in the Cart", false);
        }

        OrderItem orderItem = new OrderItem(optionalProduct.get(), cartDto.getQuantity(), OrderStatus.NEW);
        orderItem.setCreatedBy(1L);
        orderItemsRepository.save(orderItem);
        return new ApiResponse("Successfully Added to cart", true);
    }

    public ApiResponse setProductQuantity(OrderItemDto orderItemDto) {
        Optional<OrderItem> optionalOrderItem = orderItemsRepository.findById(orderItemDto.getOrderItemId());

        if (!optionalOrderItem.isPresent()) return new ApiResponse("Product in the cart not found", false);
        OrderItem orderItem = optionalOrderItem.get();

        if (orderItem.getOrderStatus().equals(OrderStatus.ORDERED))
            return new ApiResponse("Setting quantity for Ordered Product is forbidden", true);

        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItemsRepository.save(orderItem);
        return new ApiResponse("Successfully saved", true);
    }

    public ApiResponse getOrderedProducts() {
        List<OrderItem> orderItemsInTheCart = orderItemsRepository.findAllByCreatedByAndOrderStatus(1L, OrderStatus.ORDERED);

        if (orderItemsInTheCart.size() == 0){
            return new ApiResponse("No Ordered Items yet",false);
        }

        List<Map<String, Object>> orderItems = new ArrayList<>();

        for (OrderItem orderItem : orderItemsInTheCart) {
            Product product = orderItem.getProduct();
            Map<String, Object> orderItemMap = new HashMap<>();
            orderItemMap.put("productId", product.getId());
            orderItemMap.put("orderItemId", orderItem.getId());
            orderItemMap.put("productName", product.getName());
            orderItemMap.put("productCoverImageId", product.getCoverImage().getId());
            orderItemMap.put("productPrice", product.getPrice());
            orderItemMap.put("quantity", orderItem.getQuantity());
            orderItems.add(orderItemMap);
        }

        return new ApiResponse("ok",true, orderItems);
    }
}
