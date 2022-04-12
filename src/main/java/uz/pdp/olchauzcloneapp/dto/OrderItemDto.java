package uz.pdp.olchauzcloneapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderItemDto {
    @NotNull
    Long orderItemId;

    @NotNull
    Integer quantity;
}
