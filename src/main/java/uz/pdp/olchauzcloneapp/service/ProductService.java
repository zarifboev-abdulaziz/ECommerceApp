package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.ProductDto;
import uz.pdp.olchauzcloneapp.entity.Attachment;
import uz.pdp.olchauzcloneapp.entity.Category;
import uz.pdp.olchauzcloneapp.entity.CharacteristicsValues;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.entity.ProductRating;
import uz.pdp.olchauzcloneapp.projection.SearchProductProjection;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;
import uz.pdp.olchauzcloneapp.repository.AttachmentRepository;
import uz.pdp.olchauzcloneapp.repository.CategoryRepository;
import uz.pdp.olchauzcloneapp.repository.CharacteristicsValuesRepository;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    CharacteristicsValuesRepository characteristicsValuesRepository;

    @Autowired
    CategoryRepository categoryRepository;


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

    public ApiResponse addOrUpdateProduct(ProductDto productDto, Long id) {
        if (id != null) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (!optionalProduct.isPresent()) {
                return new ApiResponse("Product is not found", false);
            }
            Product product = optionalProduct.get();

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getCoverImgId());
            if (!optionalAttachment.isPresent()) {
                return new ApiResponse("Attachment is not found", false);
            }
            Attachment attachment = optionalAttachment.get();

            List<Long> photoIds = productDto.getPhotoIds();
            List<Attachment> attachmentList = new ArrayList<>();
            for (Long photoId : photoIds) {
                Optional<Attachment> optionalAttachment1 = attachmentRepository.findById(photoId);
                if (!optionalAttachment1.isPresent()) {
                    return new ApiResponse("Attachment is not found", false);
                }
                Attachment attachment1 = optionalAttachment1.get();
                attachmentList.add(attachment1);
            }

            List<CharacteristicsValues> characteristicsValuesList = new ArrayList<>();
            List<Long> characteristicsValueIds = productDto.getCharacteristicsValueIds();
            for (Long characteristicsValueId : characteristicsValueIds) {
                Optional<CharacteristicsValues> optionalCharacteristicsValues = characteristicsValuesRepository.findById(characteristicsValueId);
                if (!optionalCharacteristicsValues.isPresent()) {
                    return new ApiResponse("CharacteristicsValues is not found", false);
                }
                CharacteristicsValues characteristicsValues = optionalCharacteristicsValues.get();
                characteristicsValuesList.add(characteristicsValues);
            }

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return new ApiResponse("Category is not found", false);
            }
            Category category = optionalCategory.get();


            product.setCategory(category);
            product.setCharacteristicsValues(characteristicsValuesList);
            product.setPhotos(attachmentList);
            product.setCoverImage(attachment);
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDiscount(productDto.getDiscount());
            product.setShortDescription(productDto.getShortDescription());
            product.setWarrantyPeriodMonth(productDto.getWarrantyPeriodMonth());
            productRepository.save(product);
            return new ApiResponse("Success", true);
        } else {
            Product product = new Product();

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getCoverImgId());
            if (!optionalAttachment.isPresent()) {
                return new ApiResponse("Attachment is not found", false);
            }
            Attachment attachment = optionalAttachment.get();

            List<Long> photoIds = productDto.getPhotoIds();
            List<Attachment> attachmentList = new ArrayList<>();
            for (Long photoId : photoIds) {
                Optional<Attachment> optionalAttachment1 = attachmentRepository.findById(photoId);
                if (!optionalAttachment1.isPresent()) {
                    return new ApiResponse("Attachment is not found", false);
                }
                Attachment attachment1 = optionalAttachment1.get();
                attachmentList.add(attachment1);
            }

            List<CharacteristicsValues> characteristicsValuesList = new ArrayList<>();
            List<Long> characteristicsValueIds = productDto.getCharacteristicsValueIds();
            for (Long characteristicsValueId : characteristicsValueIds) {
                Optional<CharacteristicsValues> optionalCharacteristicsValues = characteristicsValuesRepository.findById(characteristicsValueId);
                if (!optionalCharacteristicsValues.isPresent()) {
                    return new ApiResponse("CharacteristicsValues is not found", false);
                }
                CharacteristicsValues characteristicsValues = optionalCharacteristicsValues.get();
                characteristicsValuesList.add(characteristicsValues);
            }
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return new ApiResponse("Category is not found", false);
            }
            Category category = optionalCategory.get();


            product.setCategory(category);
            product.setCharacteristicsValues(characteristicsValuesList);
            product.setPhotos(attachmentList);
            product.setCoverImage(attachment);
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDiscount(productDto.getDiscount());
            product.setShortDescription(productDto.getShortDescription());
            product.setWarrantyPeriodMonth(productDto.getWarrantyPeriodMonth());
            productRepository.save(product);
            return new ApiResponse("Success", true);
        }
    }

    public ApiResponse deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product is not found", false);
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
        return new ApiResponse("Success", true);
    }

}
