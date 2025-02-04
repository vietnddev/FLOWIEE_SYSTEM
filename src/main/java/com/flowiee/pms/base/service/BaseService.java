package com.flowiee.pms.base.service;

import com.flowiee.pms.entity.category.Category;
import com.flowiee.pms.entity.sales.Customer;
import com.flowiee.pms.entity.sales.OrderCart;
import com.flowiee.pms.entity.sales.VoucherTicket;
import com.flowiee.pms.entity.system.Account;
import com.flowiee.pms.entity.system.SystemConfig;
import com.flowiee.pms.service.system.SystemLogService;
import com.flowiee.pms.utilities.CoreUtils;
import com.flowiee.pms.utilities.enums.FilterDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Component
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SystemLogService systemLogService;

    protected Pageable getPageable(int pageNum, int pageSize) {
        return getPageable(pageNum, pageSize, null);
    }

    protected Pageable getPageable(int pageNum, int pageSize, Sort sort) {
        if (pageSize >= 0 && pageNum >= 0) {
            if (sort == null) {
                return PageRequest.of(pageNum, pageSize);
            }
            return PageRequest.of(pageNum, pageSize, sort);
        }
        return Pageable.unpaged();
    }

    public static boolean configAvailable(SystemConfig pSystemConfig) {
        if (pSystemConfig == null || CoreUtils.isNullStr(pSystemConfig.getValue())) {
            return false;
        }
        return true;
    }

    public LocalDateTime[] getFromDateToDate(FilterDate pFilterDate) {
        LocalDateTime lvFromDate = null;
        LocalDateTime lvEndDate = null;

        LocalDate today = LocalDate.now();
        LocalDateTime startOfToDay = today.atTime(LocalTime.MIN);
        LocalDateTime endOfToDay = today.atTime(LocalTime.MAX);

        YearMonth yearMonth = YearMonth.of(today.getYear(), today.getMonthValue());
        LocalDateTime startDayOfMonth = yearMonth.atDay(1).atTime(LocalTime.MIN);
        LocalDateTime endDayOfMonth = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);

        switch (pFilterDate) {
            case ToDay:
                lvFromDate = startOfToDay;
                lvEndDate = endOfToDay;
                break;
            case PreviousDay:
                lvFromDate = startOfToDay.minusDays(1);
                lvEndDate = endOfToDay.minusDays(1);
                break;
            case SevenDaysAgo:
                lvFromDate = startOfToDay.minusDays(7);
                lvEndDate = endOfToDay;
                break;
            case ThisMonth:
                lvFromDate = startDayOfMonth;
                lvEndDate = endDayOfMonth;
                break;
            case PreviousMonth:
                lvFromDate = startDayOfMonth.minusMonths(1);
                lvEndDate = endDayOfMonth.minusMonths(1);
        }

        return new LocalDateTime[] {lvFromDate, lvEndDate};
    }

    public LocalDateTime[] getFromDateToDate(LocalDateTime pFromDate, LocalDateTime pToDate, String pFilterDate) {
        LocalDateTime lvStartTime = null;
        LocalDateTime lvEndTime = null;

        LocalDate today = LocalDate.now();
        LocalDateTime startOfToDay = today.atTime(LocalTime.MIN);
        LocalDateTime endOfToDay = today.atTime(LocalTime.MAX);

        YearMonth yearMonth = YearMonth.of(today.getYear(), today.getMonthValue());
        LocalDateTime startDayOfMonth = yearMonth.atDay(1).atTime(LocalTime.MIN);
        LocalDateTime endDayOfMonth = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);

        switch (pFilterDate) {
            case "T0": //Today
                pFromDate = startOfToDay;
                pToDate = endOfToDay;
                break;
            case "T-1": //Previous day
                pFromDate = startOfToDay.minusDays(1);
                pToDate = endOfToDay.minusDays(1);
                break;
            case "T-7": //7 days ago
                pFromDate = startOfToDay.minusDays(7);
                pToDate = endOfToDay;
                break;
            case "M0": //This month
                pFromDate = startDayOfMonth;
                pToDate = endDayOfMonth;
                break;
            case "M-1": //Previous month
                pFromDate = startDayOfMonth.minusMonths(1);
                pToDate = endDayOfMonth.minusMonths(1);
        }

        lvStartTime = pFromDate;
        lvEndTime = pToDate;

        return new LocalDateTime[] {lvStartTime, lvEndTime};
    }

    protected LocalDateTime getFilterStartTime(LocalDateTime pTime) {
        if (pTime != null) {
            return pTime;
        }
        return LocalDateTime.of(1900, 1, 1, 0, 0, 0);
    }

    protected LocalDateTime getFilterEndTime(LocalDateTime pTime) {
        if (pTime != null) {
            return pTime;
        }
        return LocalDateTime.of(2100, 12, 1, 0, 0, 0);
    }

    @Data
    protected class VldModel {
        private Account       salesAssistant;
        private Category      paymentMethod;
        private Category      brand;
        private Category      color;
        private Category      fabricType;
        private Category      size;
        private Category      salesChannel;
        private Category      unit;
        private Category      productType;
        private Customer      customer;
        private OrderCart     orderCart;
        private VoucherTicket voucherTicket;
    }
}