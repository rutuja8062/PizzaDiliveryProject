package com.example.pizzaDelivery.UserController;


import com.example.pizzaDelivery.Dto.LoginDto;
import com.example.pizzaDelivery.Dto.OrderDto;
import com.example.pizzaDelivery.Dto.UserDto;
import com.example.pizzaDelivery.Entity.*;
import com.example.pizzaDelivery.Repository.*;
import com.example.pizzaDelivery.Response.LoginResponse;
import com.example.pizzaDelivery.Service.CreateOrderService;
import com.example.pizzaDelivery.Service.Token;
import com.example.pizzaDelivery.Service.TrackOrderService;
import com.example.pizzaDelivery.Service.UserService;
import net.bytebuddy.asm.Advice;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;


@CrossOrigin
@RestController
@RequestMapping("api/user")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Token token;

    @Autowired
    private PizzaRepository repo;

    @Autowired
    private ToppingsRepository toppingRepo;

    @Autowired
    private PizzaCrustRepository crustRepository;

    @Autowired
    private PizzaSizeRepository sizeRepo;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateOrderService orderService;

    @Autowired
    private OrderRepo orderRepo;

    private LoginDto loginDTO;

    @Autowired
    private OrderGroupRepo orderGroupRepo;

    private CreateOrderService CreateOrderImpl;

    @Autowired
    private TrackOrderService trackOrderService;


        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception ex) {
            // For other unhandled exceptions, return a generic error message
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }

        @PostMapping("/signup")
        public ResponseEntity<?> saveUser(@RequestBody UserDto userDTO, HttpServletResponse servletResponse)
                throws ServletException, IOException {

            //String name = userService.addUser1(userDTO, servletResponse);
            //return name;
            return ResponseEntity.ok(userService.addUser(userDTO, servletResponse));
        }

        @PostMapping(path = "/login")
        public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDTO) {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
        }


        @GetMapping("/ViewMenu")
        public ResponseEntity<Map<String, Object>> viewMenu() {

            List<PizzaSize> sizeLst = sizeRepo.findAll();
            List<PizzaCrust> pizzaCrustList = crustRepository.findAll();
            List<PizzaMenu> pizzaLst = repo.findAll();
            List<Toppings> toppings = toppingRepo.findAll();

            Map<String, Object> menu = new LinkedHashMap<>();
            menu.put("pizza", pizzaLst);
            menu.put("size", sizeLst);
            menu.put("crust", pizzaCrustList);
            menu.put("toppings", toppings);
            menu.put("tax", 10);

            return ResponseEntity.ok(menu);
        }

        @PostMapping("/createOrder")
        public Integer createOrder(@RequestBody List<OrderDto> orderDto) {
            Integer groupOrderID;
            groupOrderID = orderService.addOrder(orderDto);


            return groupOrderID;
        }

        @GetMapping("/trackOrder/{groupID}")
        public ResponseEntity<Map<String, Object>> trackOrder(@PathVariable Integer groupID) {


            Map<String, Object> userOrder;
            userOrder = trackOrderService.findOrder(groupID);

            //if (!userOrder.isEmpty()) {

                return ResponseEntity.ok(userOrder);
                //return new ResponseEntity<>(userOrder,HttpStatus.FOUND);
            /*} else {
                // Handle the case when the order is not found

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }*/
        }

        @GetMapping("/test")
        public String test() {
            return "Hello";
        }

        @PostMapping(path = "/jwt")
        public String JwtResponse(@RequestBody User user) {

            String jwtToken = token.generateToken(user);
            return jwtToken;
        }
    }

