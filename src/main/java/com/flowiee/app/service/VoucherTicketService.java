package com.flowiee.app.service;

import com.flowiee.app.base.BaseService;
import com.flowiee.app.entity.VoucherTicket;

import java.util.List;

public interface VoucherTicketService extends BaseService<VoucherTicket> {
    List<VoucherTicket> findByVoucherInfoId(Integer voucherId);
}