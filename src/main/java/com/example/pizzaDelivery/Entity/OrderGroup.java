package com.example.pizzaDelivery.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrderGroup {

    @Id
    private Integer groupId;

    @ElementCollection
    private List<Integer> orderId;
}
