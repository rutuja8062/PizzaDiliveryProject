package com.example.pizzaDelivery.Service;

import com.example.pizzaDelivery.Dto.LoginDto;
import com.example.pizzaDelivery.Dto.UserDto;
import com.example.pizzaDelivery.Entity.User;
import com.example.pizzaDelivery.Response.LoginResponse;
import com.example.pizzaDelivery.Response.SigninResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    LoginResponse loginUser(LoginDto loginDTO);

    SigninResponse addUser(UserDto userDTO, HttpServletResponse response)throws ServletException, IOException;

}
