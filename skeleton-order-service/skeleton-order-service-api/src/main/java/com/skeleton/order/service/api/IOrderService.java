package com.skeleton.order.service.api;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.order.service.api.dto.OrderCreateDTO;
import com.skeleton.order.service.api.dto.OrderUpdateDTO;

/**
 * @author yxhsea
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param orderCreateDTO
     * @return
     */
    ResultDTO<Void> createOrder(OrderCreateDTO orderCreateDTO);

    /**
     * 修改订单
     * @param orderUpdateDTO
     * @return
     */
    ResultDTO<Void> updateOrder(OrderUpdateDTO orderUpdateDTO);
}
