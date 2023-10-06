package com.example.pizzaDelivery.Service;

import java.util.Map;

public interface TrackOrderService {
    public Map<String,Object> findOrder(Integer groupID);
}
