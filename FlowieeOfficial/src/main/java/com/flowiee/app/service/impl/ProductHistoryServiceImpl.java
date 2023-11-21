package com.flowiee.app.service.impl;

import com.flowiee.app.common.utils.TagName;
import com.flowiee.app.entity.ProductHistory;
import com.flowiee.app.repository.ProductHistoryRepository;
import com.flowiee.app.service.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {
    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Override
    public List<ProductHistory> findAll() {
        return productHistoryRepository.findAll();
    }

    @Override
    public ProductHistory findById(Integer productHistoryId) {
        return productHistoryRepository.findById(productHistoryId).orElse(null);
    }

    @Override
    public String save(ProductHistory productHistory) {
        productHistoryRepository.save(productHistory);
        return TagName.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public String update(ProductHistory productHistory, Integer productHistoryId) {
        productHistory.setId(productHistoryId);
        productHistoryRepository.save(productHistory);
        return TagName.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public String delete(Integer productHistoryId) {
        productHistoryRepository.deleteById(productHistoryId);
        return TagName.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public List<ProductHistory> findByProduct(Integer productId) {
        return productHistoryRepository.findByProductId(productId);
    }

    @Override
    public List<ProductHistory> findByProductVariant(Integer productVariantId) {
        return productHistoryRepository.findByProductVariantId(productVariantId);
    }

    @Override
    public List<ProductHistory> findByProductAttribute(Integer productAttributeId) {
        return productHistoryRepository.findByProductAttributeId(productAttributeId);
    }

    @Override
    public List<ProductHistory> findByProductPrice(Integer productPriceId) {
        return productHistoryRepository.findByProductPriceId(productPriceId);
    }
}