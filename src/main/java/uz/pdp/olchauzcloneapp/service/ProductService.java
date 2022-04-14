package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.ProductDto;
import uz.pdp.olchauzcloneapp.entity.*;
import uz.pdp.olchauzcloneapp.projection.ProductCharacteristics;
import uz.pdp.olchauzcloneapp.projection.SearchProductProjection;
import uz.pdp.olchauzcloneapp.entity.Product;
import uz.pdp.olchauzcloneapp.projection.ViewProductProjection;
import uz.pdp.olchauzcloneapp.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRatingRepository productRatingRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    CharacteristicsValuesRepository characteristicsValuesRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    public ApiResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ApiResponse("Product is not found", false);
        }

        List<Map<String, Object>> allProduct = new ArrayList<>();

        for (Product product : products) {
            List<Long> photoIds = new ArrayList<>();
            product.getPhotos().forEach(attachment -> photoIds.add(attachment.getId()));

            List<Long> valueIds = new ArrayList<>();
            product.getCharacteristicsValues().forEach(characteristicsValues1 -> valueIds.add(characteristicsValues1.getId()));

            Map<String, Object> mapObjects = new HashMap<>();
            mapObjects.put("Id", product.getId());
            mapObjects.put("Name", product.getName());
            mapObjects.put("ShortDescription", product.getShortDescription());
            mapObjects.put("Price", product.getPrice());
            mapObjects.put("WarrantyPeriodMonth", product.getWarrantyPeriodMonth());
            mapObjects.put("Discount", product.getDiscount());
            mapObjects.put("Photos", photoIds);
            mapObjects.put("CoverImage", product.getCoverImage());
            mapObjects.put("CharacteristicsValues", valueIds);
            mapObjects.put("Category", product.getCategory());
            allProduct.add(mapObjects);
        }
        return new ApiResponse("Success", true, allProduct);
    }

    public ApiResponse getProductsByCategory(Integer page, Integer size, Long categoryId, String search) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ViewProductProjection> products = productRepository.getProductsByCategory(pageable, categoryId, search);

        return new ApiResponse("ok", true, products);
    }

    public ApiResponse getProductById(Long productId) {
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
        objectMap.put("warrantyInMonth", product.getWarrantyPeriodMonth());

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

    public ApiResponse deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product is not found", false);
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
        return new ApiResponse("Success", true);
    }

    public ApiResponse getProductFullDescription(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) return new ApiResponse("Product not found", false);

        Optional<ProductDescription> optionalProductDescription = productDescriptionRepository.findByProductId(productId);
        if (!optionalProductDescription.isPresent()) return new ApiResponse("Product Description not found", false);
        ProductDescription productDescription = optionalProductDescription.get();
        Map<String, Object> productDescriptions = new HashMap<>();
        productDescriptions.put("productId", productId);
        productDescriptions.put("fullDescription", productDescription.getDescription());

        return new ApiResponse("ok", true, productDescriptions);
    }

    public ApiResponse getProductFullCharacteristics(Long productId) {

        List<ProductCharacteristics> productCharacteristics = productRepository.getProductCharacteristics(productId);

        if (productCharacteristics.size() == 0)
            return new ApiResponse("No Characteristics found for that Product", false);

        return new ApiResponse("Ok", true, productCharacteristics);
    }

    public ApiResponse getProductByPrice(Integer page, Integer size, Long categoryId, double startingPrice,double finalPrise) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ViewProductProjection> productsFromPrice = productRepository.getProductsFromPrice(pageable,categoryId, startingPrice, finalPrise);
        return new ApiResponse("Success", true, productsFromPrice);
    }

//    public ApiResponse getProductsByCategory(Integer page, Integer size, Long categoryId, String search) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<ViewProductProjection> products = productRepository.getProductsByCategory(pageable, categoryId, search);
//
//        return new ApiResponse("ok", true, products);
//    }
}
