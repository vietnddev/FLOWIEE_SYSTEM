package com.flowiee.pms.service.sales;

public interface OrderProcessService {
    void cancelOrder(Long pOrderId, String pReason);

    void completeOrder(Long pOrderId);

    void returnOrder(Long pOrderId);

    void refundOrder(Long pOrderId);
}