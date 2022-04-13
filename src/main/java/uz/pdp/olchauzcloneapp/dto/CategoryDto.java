package uz.pdp.olchauzcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private String name;
    private long parentCategoryId;
    private long coverImgId;

}
