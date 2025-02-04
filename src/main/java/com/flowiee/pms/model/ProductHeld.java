package com.flowiee.pms.model;

import com.flowiee.pms.utilities.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductHeld {
    private Long productVariantId;
    private String productName;
    private String orderCode;
    private Integer quantity;
    private OrderStatus orderStatus;
}