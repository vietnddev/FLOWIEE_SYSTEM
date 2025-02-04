package com.flowiee.pms.service.sales.impl;

import com.flowiee.pms.service.sales.LedgerReceiptService;
import com.flowiee.pms.utilities.enums.LedgerTranType;
import org.springframework.stereotype.Service;

@Service
public class LedgerReceiptServiceImpl extends LedgerTransactionServiceImpl implements LedgerReceiptService {
    @Override
    protected String getTranType() {
        return LedgerTranType.PT.name();
    }
}