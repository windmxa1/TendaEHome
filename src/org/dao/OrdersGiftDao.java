package org.dao;

import java.util.List;

import org.view.VOrdersGiftId;

public interface OrdersGiftDao {
	List<VOrdersGiftId> getAllByOrderId(Long orderId);

}
