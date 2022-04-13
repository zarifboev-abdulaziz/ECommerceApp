package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.entity.ProductRating;
import uz.pdp.olchauzcloneapp.payload.ProductRateDto;
import uz.pdp.olchauzcloneapp.projection.ProductRateProjection;
import uz.pdp.olchauzcloneapp.repository.ProductRateRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

// Zuhridin Bakhriddinov 4/12/2022 10:12 PM
@Service
public class ProductRateService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRateRepository productRateRepository;
    public HttpEntity<?> addProductRate(ProductRateDto productRateDto) {
        ProductRating productRating = new ProductRating();
        Optional<Product> productOptional = productRepository.findById(productRateDto.getProductId());
        if (!productOptional.isPresent()) {
            return new ResponseEntity(new ApiResponse("Wrong",
                    false, null), HttpStatus.BAD_REQUEST);
        }
        productRating.setProduct(productOptional.get());
        productRating.setRate(productRateDto.getRate());
        productRating.setComment(productRateDto.getComment());
        productRateRepository.save(productRating);

        return new ResponseEntity(new ApiResponse("Success",
                true, true), HttpStatus.OK);
    }


    public HttpEntity<?> deleteProductRate(Long id) {
        try {
            productRateRepository.deleteById(id);
            return new ResponseEntity(new ApiResponse("Success",  true, true), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("Wrong",  false, false), HttpStatus.NOT_FOUND);
        }
    }


    public HttpEntity<?> getProductRate(Long id) {
        try {
            List<ProductRateProjection> productRating = productRateRepository.getProductRating(id);
            return new ResponseEntity(new ApiResponse("Success",  true, productRating), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("Wrong",  false, false), HttpStatus.NOT_FOUND);
        }
    }
}
