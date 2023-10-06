package com.example.pizzaDelivery.Response;

import lombok.Data;

@Data
public class SigninResponse {
    String message;
    Boolean status;

    public SigninResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
