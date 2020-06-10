package com.skeleton.gateway.service.web.controller;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.order.service.api.IOrderService;
import com.skeleton.order.service.api.dto.OrderCreateDTO;
import com.skeleton.order.service.api.dto.OrderUpdateDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxhsea
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("/create")
    public ResultDTO<Void> createUser(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderService.createOrder(orderCreateDTO);
    }

    @ApiOperation("更新订单")
    @PostMapping("/update")
    public ResultDTO<Void> updateUser(@RequestBody OrderUpdateDTO orderCreateDTO) {
        return orderService.updateOrder(orderCreateDTO);
    }
}
