package com.skeleton.order.service.provider.impl;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.exception.SkeletonException;
import com.skeleton.order.service.api.IOrderService;
import com.skeleton.order.service.api.dto.OrderCreateDTO;
import com.skeleton.order.service.api.dto.OrderUpdateDTO;
import org.springframework.stereotype.Component;

/**
 * @author yxhsea
 */
@Component
public class OrderServiceImpl implements IOrderService {
    @Override
    public ResultDTO<Void> createOrder(OrderCreateDTO orderCreateDTO) throws SkeletonException {
        return null;
    }

    @Override
    public ResultDTO<Void> updateOrder(OrderUpdateDTO orderUpdateDTO) throws SkeletonException {
        return null;
    }
}
