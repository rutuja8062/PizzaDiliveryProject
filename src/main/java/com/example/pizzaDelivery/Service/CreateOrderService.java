package com.example.pizzaDelivery.Service;

import com.example.pizzaDelivery.Dto.OrderDto;

import java.util.List;

public interface CreateOrderService {

    Integer addOrder(List<OrderDto> orderDto);
}
