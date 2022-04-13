package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.olchauzcloneapp.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String categoryName);

    @Query(nativeQuery = true, value = "select *\n" +
            "from categories\n" +
            "where parent_category_id\n" +
            "          = :parentId")
    List<Category> findCategoryByParentId(Long parentId);
}
