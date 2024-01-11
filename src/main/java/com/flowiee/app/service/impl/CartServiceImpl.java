package com.flowiee.app.service.impl;

import com.flowiee.app.entity.Items;
import com.flowiee.app.entity.OrderCart;
import com.flowiee.app.entity.SystemLog;
import com.flowiee.app.model.role.SystemModule;
import com.flowiee.app.repository.ItemsRepository;
import com.flowiee.app.repository.OrderCartRepository;
import com.flowiee.app.service.CartService;
import com.flowiee.app.service.SystemLogService;

import com.flowiee.app.utils.AppConstants;
import com.flowiee.app.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderCartRepository cartRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private SystemLogService    systemLogService;

    @Override
    public List<OrderCart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public List<OrderCart> findCartByAccountId(Integer accountId) {
        return cartRepository.findByAccountId(accountId);
    }

    @Override
    public List<Items> findItemsByCartId(Integer cartId) {
        return itemsRepository.findByCartId(cartId);
    }

    @Override
    public OrderCart findCartById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Items findItemById(Integer itemId) {
        return itemsRepository.findById(itemId).orElse(null);
    }

    @Override
    public String saveCart(OrderCart orderCart) {
        if (orderCart == null) {
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
        cartRepository.save(orderCart);
        return "OK";
    }

    @Override
    public String updateCart(OrderCart cart, Integer cartId) {
        cart.setId(cartId);
        cartRepository.save(cart);
        return "OK";
    }

    @Override
    public String deleteCart(Integer id) {
        cartRepository.deleteById(id);
        SystemLog systemLog = new SystemLog();
        systemLog.setModule(SystemModule.PRODUCT.name());
        systemLog.setAction("DELETE_CART");
        systemLog.setCreatedBy(CommonUtil.getCurrentAccountId());
        systemLog.setIp(CommonUtil.getCurrentAccountIp());
        systemLog.setNoiDung("DELETE CART");
        systemLogService.writeLog(systemLog);
        return "OK";
    }

    @Override
    public String saveItem(Items items) {
        if (items == null) {
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
        itemsRepository.save(items);
        return AppConstants.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public String updateItem(Items entity, Integer entityId) {
        if (entity == null || entityId == null || entityId <= 0) {
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
        entity.setId(entityId);
        itemsRepository.save(entity);
        return AppConstants.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public String deleteItem(Integer itemId) {
        if (itemId == null || itemId <= 0) {
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
        Items items = this.findItemById(itemId);
        if (items == null) {
            return AppConstants.SERVICE_RESPONSE_FAIL;
        }
        itemsRepository.deleteById(itemId);
        return AppConstants.SERVICE_RESPONSE_SUCCESS;
    }

    @Override
    public Integer findSoLuongByBienTheSanPhamId(Integer productVariantId) {
        return itemsRepository.findSoLuongByBienTheSanPhamId(productVariantId);
    }
}