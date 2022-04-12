package uz.pdp.olchauzcloneapp.service;

//Asilbek Fayzullayev 12.04.2022 11:23   

import com.sun.tools.javac.util.DefinedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CartDto;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.entity.User;
import uz.pdp.olchauzcloneapp.projection.ViewCartProjection;
import uz.pdp.olchauzcloneapp.repository.CartRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;


    public ApiResponse getAllCart(){
        List<ViewCartProjection> allCart = cartRepository.getAllCart();
        if (allCart.size() == 0){
            return new ApiResponse("Cart is empty",false);
        }
        return new ApiResponse("Success",true,allCart);
    }

    public ApiResponse clearCart(){
        cartRepository.deleteAll();
        return new ApiResponse("Deleted",true);
    }
}
