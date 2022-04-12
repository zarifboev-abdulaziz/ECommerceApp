package uz.pdp.olchauzcloneapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String name;

    private String shortDescription;

    private double price;

    private float warrantyPeriodMonth;

    private double discount;

    private Long coverImgId;

    private List<Long> photoIds;

    private List<Long> characteristicsValueIds;

    private Long categoryId;
}
