package uz.pdp.olchauzcloneapp.service;

//Asilbek Fayzullayev 12.04.2022 9:21   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.FavouriteItemDto;
import uz.pdp.olchauzcloneapp.entity.FavouriteItem;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.repository.FavouriteItemRepository;
import uz.pdp.olchauzcloneapp.repository.PayTypeRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteItemService {

    @Autowired
    FavouriteItemRepository favouriteItemRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse getAllFavouriteItem() {
        List<FavouriteItem> favouriteItemList = favouriteItemRepository.findAll();
        if (favouriteItemList.size() == 0) {
            return new ApiResponse("List Empty", false);
        }
        return new ApiResponse("Success", true, favouriteItemList);
    }

    public ApiResponse getFavouriteItemById(Long id) {
        Optional<FavouriteItem> optionalFavouriteItem = favouriteItemRepository.findById(id);
        if (!optionalFavouriteItem.isPresent()) {
            return new ApiResponse("PayType not found", false);
        }
        return new ApiResponse("Success", true, optionalFavouriteItem);
    }

    public ApiResponse addFavouriteItem(Long productId) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (!optionalProduct.isPresent()) {
                return new ApiResponse("Product is not found", false);
            }
            Product product = optionalProduct.get();

            FavouriteItem favouriteItem = new FavouriteItem();
            favouriteItem.setProduct(product);
//            favouriteItem.setCreatedBy(1L);
            FavouriteItem save = favouriteItemRepository.save(favouriteItem);
            return new ApiResponse("Successfully added", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse delete(Long id){
        try {
            favouriteItemRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
