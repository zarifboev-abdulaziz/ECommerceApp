package uz.pdp.olchauzcloneapp.projection;

//Asilbek Fayzullayev 12.04.2022 11:52   

public interface ViewCartProjection {
    Long getProductId();
    String getProductName();
    Double getDiscount();
    Double getProductPrice();
    Long getProductPhotoId();
    Integer getQuantity();

}
