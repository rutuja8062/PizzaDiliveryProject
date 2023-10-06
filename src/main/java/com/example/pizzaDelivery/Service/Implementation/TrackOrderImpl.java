package com.example.pizzaDelivery.Service.Implementation;

import com.example.pizzaDelivery.Dto.OrderDto;
import com.example.pizzaDelivery.Entity.CreateOrder;
import com.example.pizzaDelivery.Entity.OrderGroup;
import com.example.pizzaDelivery.Entity.Toppings;
import com.example.pizzaDelivery.Repository.OrderGroupRepo;
import com.example.pizzaDelivery.Repository.OrderRepo;
import com.example.pizzaDelivery.Service.TrackOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TrackOrderImpl implements TrackOrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderGroupRepo orderGroupRepo;

    @Override
    public Map<String, Object> findOrder(Integer groupID) {

        Optional<OrderGroup> opt = orderGroupRepo.findById(groupID);

        Map<String, Object> trackOrder = new LinkedHashMap<>();
        if (opt.isPresent()) {
            OrderGroup order = opt.get();

            List<CreateOrder> createOrderLst = orderRepo.findAllById(order.getOrderId());

            trackOrder.put("Orderid", groupID);

            int amount = 10;

            List<OrderDto> orderItems = new ArrayList<>();
            for (CreateOrder ord : createOrderLst) {

                trackOrder.put("Status", ord.getStatus());

                OrderDto orderItem = new OrderDto();
                orderItem.setPizzaID(ord.getPizzaID().getPizzaId());
                orderItem.setPizzaQtn(ord.getPizzaQtn());
                orderItem.setPizzaSize(ord.getPizzaSize());


                if(ord.getPizzaCrust()==null){
                    orderItem.setPizzaCrust("No Crust Selected");
                }else
                    orderItem.setPizzaCrust(ord.getPizzaCrust());

                // Extract toppingsID as List<String>
                List<String> toppingsIds = new ArrayList<>();


                if(ord.getPizzaCrust()==null){
                    toppingsIds.add("No toppings Selected");
                    orderItem.setToppingsID(toppingsIds);
                }else{
                    toppingsIds= ord.getToppingsID().stream()
                            .map(Toppings::getToppingsID)
                            .collect(Collectors.toList());

                    orderItem.setToppingsID(toppingsIds);
                }

                orderItems.add(orderItem);

                amount += ord.getAmount();
            }

            trackOrder.put("Amount", amount);
            trackOrder.put("Orderitem", orderItems);

            return trackOrder;
            // return new ResponseEntity<>(trackOrder,HttpStatus.FOUND);
        } else {
            // Handle the case when the order is not found

            trackOrder.put("Status","No order found");
            //return new ResponseEntity<>(trackOrder, HttpStatus.NOT_FOUND).getBody();
            return trackOrder;

        }

    }
}
