package com.flowiee.pms.model.dto;

import com.flowiee.pms.entity.category.Category;
import com.flowiee.pms.entity.sales.Customer;
import com.flowiee.pms.entity.sales.Order;
import com.flowiee.pms.entity.system.Account;
import com.flowiee.pms.entity.system.FileStorage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO extends Order implements Serializable {
	@Serial
	static final long serialVersionUID = 1L;

    Integer customerId;
    String customerName;
    Integer salesChannelId;
    String salesChannelName;
    Integer orderStatusId;
    String orderStatusName;
    Integer payMethodId;
    String payMethodName;
    Integer cashierId;
    String cashierName;
	BigDecimal totalAmount;
	BigDecimal totalAmountDiscount;
	Integer totalProduct;
    String qrCode;
	Integer cartId;
	Integer ticketExportId;
	Boolean accumulateBonusPoints;
	List<OrderDetailDTO> listOrderDetailDTO;

	public OrderDTO(Integer id, String code, LocalDateTime orderTime, String receiptName, String receiptPhone, String receiptEmail, String receiptAddress,
					Customer customer, Category salesChannel, Category paymentMethod, Account cashier, Category orderStatus) {
		this.id = id;
		setCode(code);
		setOrderTime(orderTime);
		setReceiverName(receiptName);
		setReceiverPhone(receiptPhone);
		setReceiverEmail(receiptEmail);
		setReceiverAddress(receiptAddress);
		setCustomer(customer);
		setKenhBanHang(salesChannel);
		setPaymentMethod(paymentMethod);
		setNhanVienBanHang(cashier);
		setTrangThaiDonHang(orderStatus);
	}

	public static OrderDTO fromOrder(Order order) {
		OrderDTO dto = new OrderDTO(order.getId(), order.getCode(), order.getOrderTime(), order.getReceiverName(), order.getReceiverPhone(), order.getReceiverEmail(), order.getReceiverAddress(),
									order.getCustomer(), order.getKenhBanHang(), order.getPaymentMethod(), order.getNhanVienBanHang(), order.getTrangThaiDonHang());
		dto.setCreatedAt(order.getCreatedAt());

		dto.setCustomerId(order.getCustomer().getId());
		dto.setCustomerName(order.getCustomer().getCustomerName());

		dto.setSalesChannelId(order.getKenhBanHang().getId());
		dto.setSalesChannelName(order.getKenhBanHang().getName());

		dto.setOrderStatusId(dto.getPaymentMethod() != null ? order.getTrangThaiDonHang().getId() : null);
		dto.setOrderStatusName(dto.getPaymentMethod() != null ? order.getTrangThaiDonHang().getName() : null);

		dto.setPayMethodId(dto.getPaymentMethod() != null ? order.getPaymentMethod().getId() : null);
		dto.setPayMethodName(dto.getPaymentMethod() != null ? order.getPaymentMethod().getName() : null);

		dto.setCashierId(order.getNhanVienBanHang().getId());
		dto.setCashierName(order.getNhanVienBanHang().getFullName());

		dto.setTicketExportId(order.getTicketExport() != null ? order.getTicketExport().getId() : null);

		if (ObjectUtils.isNotEmpty(order.getListImageQR())) {
			FileStorage imageQRCode = order.getListImageQR().get(0);
			dto.setQrCode(imageQRCode.getDirectoryPath() + "/" + imageQRCode.getStorageName());
		}

		dto.setAmountDiscount(order.getAmountDiscount() != null ? order.getAmountDiscount() : new BigDecimal(0));
		dto.setTotalAmount(calTotalAmount(order.getListOrderDetail()));
		dto.setTotalAmountDiscount(calTotalAmountDiscount(dto.getTotalAmount(), dto.getAmountDiscount()));
		dto.setTotalProduct(calTotalProduct(order.getListOrderDetail()));
		dto.setVoucherUsedCode(order.getVoucherUsedCode());
		dto.setPaymentStatus(order.getPaymentStatus() != null && order.getPaymentStatus());
		dto.setPaymentTime(order.getPaymentTime());
		dto.setPaymentAmount(order.getPaymentAmount());
		dto.setPaymentNote(order.getPaymentNote());
		dto.setNote(order.getNote());

		dto.setListOrderDetailDTO(OrderDetailDTO.fromOrderDetails(order.getListOrderDetail()));

		return dto;
	}

	public static List<OrderDTO> fromOrders(List<Order> orders) {
		List<OrderDTO> list = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orders)) {
			for (Order p : orders) {
				list.add(OrderDTO.fromOrder(p));
			}
		}
		return list;
	}
}