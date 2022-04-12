package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.ProductRating;
import uz.pdp.olchauzcloneapp.projection.SearchProductProjection;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;
import uz.pdp.olchauzcloneapp.repository.ProductRatingRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRatingRepository productRatingRepository;



    public ApiResponse getProductsByCategory(Integer page, Integer size, Long categoryId, String search) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ViewProductProjection> products = productRepository.getProductsByCategory(pageable, categoryId, search);

        return new ApiResponse("ok", true, products);
    }

    public ApiResponse getProductsById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) return new ApiResponse("Product not found", false);
        Product product = optionalProduct.get();

        List<Long> photoIds = new ArrayList<>();
        product.getPhotos().forEach(attachment -> photoIds.add(attachment.getId()));


        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", product.getId());
        objectMap.put("name", product.getName());
        objectMap.put("coverImageId", product.getCoverImage().getId());
        objectMap.put("photoIds", photoIds);
        objectMap.put("price", product.getPrice());
        objectMap.put("shortDescription", product.getShortDescription());
        objectMap.put("numberOfRatings", productRatingRepository.countByProductId(productId));
        objectMap.put("averageRatings", productRatingRepository.getAverageRatingByProductId(productId));


//        One Product Projection
//                =======================
//        product id  w
//        product name w
//        product photo ids [] w
//        product price w
//        product short description w
//        average rating w
//        number of ratings w


        return new ApiResponse("ok", true, objectMap);
    }

}
