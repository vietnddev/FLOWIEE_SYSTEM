package com.flowiee.pms.service.sales;

import com.flowiee.pms.service.BaseCurd;
import com.flowiee.pms.entity.sales.VoucherTicket;
import com.flowiee.pms.model.dto.VoucherTicketDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoucherTicketService extends BaseCurd<VoucherTicket> {
    Page<VoucherTicket> findAll(int pageSize, int pageNum, Integer voucherId);

    List<VoucherTicket> findByVoucherId(Integer voucherId);

    VoucherTicket findByCode(String code);

    VoucherTicketDTO isAvailable(String voucherTicketCode);

    VoucherTicket findTicketByCode(String code);

    String checkTicketToUse(String code);
}