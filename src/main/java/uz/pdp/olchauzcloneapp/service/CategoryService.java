package uz.pdp.olchauzcloneapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CategoryDto;
import uz.pdp.olchauzcloneapp.entity.Attachment;
import uz.pdp.olchauzcloneapp.entity.Category;
import uz.pdp.olchauzcloneapp.repository.AttachmentRepository;
import uz.pdp.olchauzcloneapp.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    // Get All Category
    public ApiResponse getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return new ApiResponse("Category is Empty!", false);
        }
        return new ApiResponse("Success!", true, categoryList);
    }

    // Get ById Category
    public ApiResponse getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(category -> new ApiResponse("Success!", true, category))
                .orElseGet(() -> new ApiResponse("Category not found!", false));
    }

    // get Category by ParentId
    public ApiResponse getCategoryByParentId(Long parentId) {
        List<Category> categoryByParentId = categoryRepository.findCategoryByParentId(parentId);
        if (categoryByParentId.isEmpty()) {
            return new ApiResponse("Category not found!", false);
        }
        return new ApiResponse("Success!", true, categoryByParentId);
    }

    // Add New Category
    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category categoryByName = categoryRepository.findCategoryByName(categoryDto.getName());
        if (categoryByName != null) {
            return new ApiResponse("A category with this name exists", false);
        }
        try {
            Category newCategory = new Category();

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(categoryDto.getCoverImgId());
            if (!optionalAttachment.isPresent()) {
                return new ApiResponse("Image not found!", false);
            }

            if (categoryDto.getParentCategoryId() != 0) {
                Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
                if (!optionalParentCategory.isPresent()) {
                    return new ApiResponse("Parent Category not found!", false);
                }
                newCategory.setName(categoryDto.getName());
                newCategory.setParentCategory(optionalParentCategory.get());
                newCategory.setAttachment(optionalAttachment.get());

                Category saveCategory = categoryRepository.save(newCategory);

                return new ApiResponse("Success!", true, saveCategory);
            }

            newCategory.setName(categoryDto.getName());
            newCategory.setAttachment(optionalAttachment.get());
            newCategory.setParentCategory(null);

            Category saveCategory1 = categoryRepository.save(newCategory);

            return new ApiResponse("Success!", true, saveCategory1);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    // Edit Old Category
    public ApiResponse editCategory(Long categoryId, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("Category not found!", false);
        }
        try {
            Category newCategory = categoryOptional.get();

            Optional<Attachment> attachmentOptional = attachmentRepository.findById(categoryDto.getCoverImgId());
            if (!attachmentOptional.isPresent()) {
                return new ApiResponse("Image not found!", false);
            }

            Attachment attachment = attachmentOptional.get();

            if (categoryDto.getParentCategoryId() != 0) {
                Optional<Category> parentCategoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
                if (!parentCategoryOptional.isPresent()) {
                    return new ApiResponse("Parent Category not found!", false);
                }

                Category parentCategory = parentCategoryOptional.get();

                newCategory.setName(categoryDto.getName());
                newCategory.setAttachment(attachment);
                newCategory.setParentCategory(parentCategory);

                categoryRepository.save(newCategory);

                return new ApiResponse("Success!", true, newCategory);
            }

            newCategory.setName(categoryDto.getName());
            newCategory.setAttachment(attachment);
            newCategory.setParentCategory(null);

            return new ApiResponse("Success!", true, newCategory);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    // Delete Category
    public ApiResponse deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("Category not found!", false);
        }

        try {

            Category category = categoryOptional.get();

            categoryRepository.delete(category);

            return new ApiResponse("Success", true);
        } catch (Exception e) {

            return new ApiResponse("Error!", false);
        }
    }
}
