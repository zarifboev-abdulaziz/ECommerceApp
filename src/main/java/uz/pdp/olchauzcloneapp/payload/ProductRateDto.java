package uz.pdp.olchauzcloneapp.payload;

import lombok.Data;

// Zuhridin Bakhriddinov 4/12/2022 10:08 PM
@Data
public class ProductRateDto {
   private Long productId;
   private Integer rate;
   private String comment;


}
