package com.flowiee.pms.controller.product;

import com.flowiee.pms.controller.BaseController;
import com.flowiee.pms.entity.product.ProductAttribute;
import com.flowiee.pms.exception.AppException;
import com.flowiee.pms.exception.ResourceNotFoundException;
import com.flowiee.pms.model.AppResponse;
import com.flowiee.pms.service.product.ProductAttributeService;
import com.flowiee.pms.utils.constants.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.api.prefix}/product")
@Tag(name = "Product API", description = "Quản lý thuộc tính mở rộng của sản phẩm")
public class ProductAttributeController extends BaseController {
    private final ProductAttributeService productAttributeService;

    public ProductAttributeController(ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @Operation(summary = "Create product attribute")
    @PostMapping("/attribute/create")
    @PreAuthorize("@vldModuleProduct.insertProduct(true)")
    public AppResponse<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttribute) {
        try {
            return success(productAttributeService.save(productAttribute));
        } catch (RuntimeException ex) {
            throw new AppException(String.format(ErrorCode.CREATE_ERROR_OCCURRED.getDescription(), "product attribute"), ex);
        }
    }

    @Operation(summary = "Update product attribute")
    @PutMapping("/attribute/update/{id}")
    @PreAuthorize("@vldModuleProduct.updateProduct(true)")
    public AppResponse<ProductAttribute> updateProductAttribute(@RequestBody ProductAttribute productAttribute, @PathVariable("id") Integer productAttributeId) {
        try {
            return success(productAttributeService.update(productAttribute, productAttributeId));
        } catch (RuntimeException ex) {
            throw new AppException(String.format(ErrorCode.UPDATE_ERROR_OCCURRED.getDescription(), "product attribute"), ex);
        }
    }

    @Operation(summary = "Delete product attribute")
    @DeleteMapping("/attribute/delete/{id}")
    @PreAuthorize("@vldModuleProduct.deleteProduct(true)")
    public AppResponse<String> deleteProductAttribute(@PathVariable("id") Integer productAttributeId) {
        return success(productAttributeService.delete(productAttributeId));
    }
}