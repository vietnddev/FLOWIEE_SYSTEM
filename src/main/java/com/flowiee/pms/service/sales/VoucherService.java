package com.flowiee.pms.service.sales;

import com.flowiee.pms.service.BaseCurd;
import com.flowiee.pms.model.dto.VoucherInfoDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface VoucherService extends BaseCurd<VoucherInfoDTO> {
    Page<VoucherInfoDTO> findAll(int pageSize, int pageNum, List<Integer> pIds, String pTitle, LocalDateTime pStartTime, LocalDateTime pEndTime, String pStatus);
}