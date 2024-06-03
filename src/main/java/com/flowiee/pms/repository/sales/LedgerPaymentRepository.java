package com.flowiee.pms.repository.sales;

import com.flowiee.pms.entity.sales.LedgerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerPaymentRepository extends JpaRepository<LedgerPayment, Integer> {
    @Query(value = "select payment_index from ledger_payment order by id desc fetch first 1 rows only", nativeQuery = true)
    Integer findLastIndex();
}