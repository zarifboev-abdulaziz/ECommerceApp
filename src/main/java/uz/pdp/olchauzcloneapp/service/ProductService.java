package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public ApiResponse getProductsByCategory(Integer page, Integer size, Long categoryId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ViewProductProjection> products = productRepository.getProductsByCategory(pageable, categoryId);


        return null;
    }
}
