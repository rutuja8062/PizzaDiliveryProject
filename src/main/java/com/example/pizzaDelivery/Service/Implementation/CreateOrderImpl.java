package com.example.pizzaDelivery.Service.Implementation;

import com.example.pizzaDelivery.Dto.OrderDto;
import com.example.pizzaDelivery.Entity.CreateOrder;
import com.example.pizzaDelivery.Entity.OrderGroup;
import com.example.pizzaDelivery.Entity.PizzaMenu;
import com.example.pizzaDelivery.Entity.Toppings;
import com.example.pizzaDelivery.Repository.OrderGroupRepo;
import com.example.pizzaDelivery.Repository.OrderRepo;
import com.example.pizzaDelivery.Repository.PizzaRepository;
import com.example.pizzaDelivery.Repository.ToppingsRepository;
import com.example.pizzaDelivery.Service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateOrderImpl implements CreateOrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private PizzaRepository pizzaRepo;

    @Autowired
    private ToppingsRepository toppingRepo;

    @Autowired
    private OrderGroupRepo orderGroupRepo;
    private int generateOrderId() {
        UUID groupUUId = UUID.randomUUID();
        long mostSignificantBits = groupUUId.getMostSignificantBits();
        return (int) (mostSignificantBits & Integer.MAX_VALUE);
    }

    private int calculatePrice(PizzaMenu pizza, List<Toppings> toppingsList) {
        int pizzaPrice = pizza.getPizzaPrice();
        int toppingsPrice = toppingsList.stream().mapToInt(Toppings::getPrice).sum();
        return pizzaPrice + toppingsPrice;
    }
    @Override
    public Integer addOrder(List<OrderDto> orderDto) {



        List<CreateOrder> saveOrdersLst = new ArrayList<>();

        int totalPrice = 0;

        for(OrderDto order1 : orderDto){

            PizzaMenu pizzaId = pizzaRepo.findById(order1.getPizzaID()).orElse(null);

            List<Toppings> toppingsList = toppingRepo.findAllById(order1.getToppingsID());
            Toppings toppings = toppingsList.isEmpty() ? null : toppingsList.get(0);

            int price = calculatePrice(pizzaId , toppingsList) * order1.getPizzaQtn();

            totalPrice += price;

            /*List<String> toppingsName = new ArrayList<>();

            int price=0;

            for(Toppings top : toppingsList){
                toppingsName.add(top.getToppingsName());
                price = price + top.getPrice();
            }*/

            CreateOrder order = new CreateOrder();

            order.setPizzaID(pizzaId);
            order.setAmount(price);
            order.setPizzaName(pizzaId.getPizzaName());
            order.setPizzaQtn(order1.getPizzaQtn());
            order.setPizzaSize(order1.getPizzaSize());
            order.setPizzaCrust(order1.getPizzaCrust());
            order.setToppingsID(toppingsList);

            saveOrdersLst.add(order);

        }

       orderRepo.saveAll(saveOrdersLst);

        List<Integer> orderIds = saveOrdersLst.stream()
                .map(CreateOrder::getOrderID)
                .collect(Collectors.toList());

        Integer groupId = generateOrderId(); // Generate a unique group ID

        OrderGroup groupedOrder = new OrderGroup();

        groupedOrder.setOrderId(orderIds);
        groupedOrder.setGroupId(groupId);

        orderGroupRepo.save(groupedOrder);

        return groupId;

    }

}
