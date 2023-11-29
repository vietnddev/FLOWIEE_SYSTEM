package com.flowiee.app.repository;

import com.flowiee.app.entity.VoucherTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherTicketlRepository extends JpaRepository<VoucherTicket, Integer> {
    @Query("from VoucherTicket v where v.voucherInfo.id=:voucherId")
    List<VoucherTicket> findByVoucherId(Integer voucherId);
}