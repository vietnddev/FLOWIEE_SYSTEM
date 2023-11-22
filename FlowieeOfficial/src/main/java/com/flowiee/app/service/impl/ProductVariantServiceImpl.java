package com.flowiee.app.service.impl;

import com.flowiee.app.model.system.SanPhamAction;
import com.flowiee.app.model.system.SystemModule;
import com.flowiee.app.entity.ProductVariant;
import com.flowiee.app.model.product.ProductVariantResponse;
import com.flowiee.app.repository.ProductVariantRepository;
import com.flowiee.app.service.PriceService;
import com.flowiee.app.service.ProductVariantService;
import com.flowiee.app.service.SystemLogService;

import com.flowiee.app.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private static final Logger logger = LoggerFactory.getLogger(ProductVariantServiceImpl.class);
    private static final String module = SystemModule.SAN_PHAM.name();

    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private PriceService priceService;
    @Autowired
    private SystemLogService systemLogService;

    @Override
    public List<ProductVariant> findAll() {
        return productVariantRepository.findAll();
    }

    @Override
    public ProductVariant findById(Integer entityId) {
        return productVariantRepository.findById(entityId).orElse(null);
    }

    @Override
    public List<ProductVariantResponse> getListVariantOfProduct(Integer sanPhamId) {
        List<ProductVariantResponse> listReturn = new ArrayList<>();
        productVariantRepository.findListBienTheOfsanPham(sanPhamId).forEach(bienTheSanPham -> {
            ProductVariantResponse dataModel = ProductVariantResponse.fromProductVariant(bienTheSanPham);
            dataModel.setPrices(priceService.findPricesByProductVariant(dataModel.getId()));
            listReturn.add(dataModel);
        });
        return listReturn;
    }

    @Override
    public Double getGiaBan(int id) {
        return priceService.findGiaHienTai(id);
    }

    @Override
    public String save(ProductVariant productVariant) {
        try {
            String tenBienTheSanPham = "";
            if (productVariant.getTenBienThe().isEmpty()) {
                tenBienTheSanPham = productVariant.getProduct().getTenSanPham() + " - Size " + productVariant.getSize().getName() + " - Màu " + productVariant.getColor().getName();
            } else {
                tenBienTheSanPham = productVariant.getProduct().getTenSanPham() + " - " + productVariant.getTenBienThe() + " - Size " + productVariant.getSize().getName() + " - Màu " + productVariant.getColor().getName();
            }
            productVariant.setTenBienThe(tenBienTheSanPham);
            productVariantRepository.save(productVariant);
            systemLogService.writeLog(module, SanPhamAction.UPDATE_SANPHAM.name(), "Thêm mới biến thể sản phẩm: " + productVariant.toString());
            logger.info(ProductVariantServiceImpl.class.getName() + ": Thêm mới biến thể sản phẩm " + productVariant.toString());
            return AppConstants.SERVICE_RESPONSE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
    }

    @Override
    public String delete(Integer entityId) {
        ProductVariant productVariantToDelete = this.findById(entityId);
        try {
            productVariantRepository.deleteById(entityId);
            systemLogService.writeLog(module, SanPhamAction.UPDATE_SANPHAM.name(), "Xóa biến thể sản phẩm: " + productVariantToDelete.toString());
            logger.info(ProductVariantServiceImpl.class.getName() + ": Xóa biến thể sản phẩm " + productVariantToDelete.toString());
            return AppConstants.SERVICE_RESPONSE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
    }

    @Override
    public String update(ProductVariant productVariant, Integer id) {
        ProductVariant productVariantBefore = this.findById(id);
        try {
            productVariant.setId(id);
            productVariantRepository.save(productVariant);
            systemLogService.writeLog(module, SanPhamAction.UPDATE_SANPHAM.name(), "Cập nhật biến thể sản phẩm: " + productVariantBefore.toString(), "Biến thể sản phẩm sau khi cập nhật: " + productVariant);
            logger.info(ProductVariantServiceImpl.class.getName() + ": Cập nhật biến thể sản phẩm " + productVariantBefore.toString());
            return AppConstants.SERVICE_RESPONSE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppConstants.SERVICE_RESPONSE_FAIL;
    }

    @Override
    public String updateSoLuong(Integer soLuong, Integer id) {
        ProductVariant productVariant = this.findById(id);
        productVariant.setSoLuongKho(productVariant.getSoLuongKho() - soLuong);
        try {
            productVariantRepository.save(productVariant);
            systemLogService.writeLog(module, SanPhamAction.UPDATE_SANPHAM.name(), "Cập nhật lại số lượng sản phẩm khi tạo đơn hàng");
            return AppConstants.SERVICE_RESPONSE_SUCCESS;
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật số lượng sản phẩm!", productVariant);
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
    }

    @Override
    public List<ProductVariant> findByImportId(Integer importId) {
        List<ProductVariant> listData = new ArrayList<>();
        if (importId != null && importId > 0) {
            listData = productVariantRepository.findByImportId(importId);
        }
        return listData;
    }

    @Override
    public List<ProductVariant> findByFabricType(Integer fabricTypeId) {
        return productVariantRepository.findByFabricType(fabricTypeId);
    }

    @Override
    public List<ProductVariant> findBySize(Integer sizeId) {
        return productVariantRepository.findBySize(sizeId);
    }

    @Override
    public List<ProductVariant> findByColor(Integer colorId) {
        return productVariantRepository.findByColor(colorId);
    }
}