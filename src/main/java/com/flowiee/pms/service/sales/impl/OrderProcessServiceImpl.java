package com.flowiee.pms.service.sales.impl;

import com.flowiee.pms.base.service.BaseService;
import com.flowiee.pms.entity.sales.Order;
import com.flowiee.pms.entity.system.SystemConfig;
import com.flowiee.pms.repository.sales.OrderRepository;
import com.flowiee.pms.repository.system.ConfigRepository;
import com.flowiee.pms.service.sales.LoyaltyProgramService;
import com.flowiee.pms.service.sales.OrderProcessService;
import com.flowiee.pms.service.sales.OrderReadService;
import com.flowiee.pms.service.sales.TicketImportService;
import com.flowiee.pms.service.system.SendCustomerNotificationService;
import com.flowiee.pms.utilities.enums.ConfigCode;
import com.flowiee.pms.utilities.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderProcessServiceImpl extends BaseService implements OrderProcessService {
    private final SendCustomerNotificationService sendCustomerNotificationService;
    private final LoyaltyProgramService loyaltyProgramService;
    private final TicketImportService ticketImportService;
    private final ConfigRepository configRepository;
    private final OrderReadService orderReadService;
    private final OrderRepository orderRepository;

    @Override
    public void cancelOrder(Long pOrderId, String pReason) {
        Order lvOrder = orderReadService.findById(pOrderId, true);
        lvOrder.setCancellationReason(null);
        lvOrder.setCancellationDate(LocalDateTime.now());
        Order lvOrderUpdated = orderRepository.save(lvOrder);

        SystemConfig lvSendNotifyConfig = configRepository.findByCode(ConfigCode.sendNotifyCustomerOnOrderConfirmation.name());
        if (configAvailable(lvSendNotifyConfig) && lvSendNotifyConfig.isYesOption()) {
            sendCustomerNotificationService.notifyOrderConfirmation(lvOrderUpdated, lvOrderUpdated.getReceiverEmail());
        }

        //...
    }

    @Override
    public void completeOrder(Long pOrderId) {
        Order lvOrder = orderReadService.findById(pOrderId, true);
        lvOrder.setOrderStatus(OrderStatus.DLVD);
        lvOrder.setSuccessfulDeliveryTime(LocalDateTime.now());
        Order lvOrderUpdated = orderRepository.save(lvOrder);

        Long lvProgramId = null;
        loyaltyProgramService.accumulatePoints(lvOrderUpdated, lvProgramId);

        //...
    }

    @Override
    public void returnOrder(Long pOrderId) {
        Order lvOrder = orderReadService.findById(pOrderId, true);
        Long lvStorageId = null;
        String lvOrderCode = lvOrder.getCode();
        ticketImportService.restockReturnedItems(lvStorageId, lvOrderCode);

        //...
    }

    @Override
    public void refundOrder(Long pOrderId) {
        Order lvOrder = orderReadService.findById(pOrderId, true);

        //...
    }
}