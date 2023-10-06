package com.example.pizzaDelivery;

import com.example.pizzaDelivery.Configuration.SecurityConfiguration;
import com.example.pizzaDelivery.Filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PizzaDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaDeliveryApplication.class, args);

		}
	@Bean
	public FilterRegistrationBean JwtFilter() {
		FilterRegistrationBean<JwtFilter> frb = new FilterRegistrationBean<>();
		frb.setFilter(new JwtFilter());
		frb.addUrlPatterns("/api/user/ViewMenu","/api/user/createOrder","/api/user/trackOrder/*"
				,"/api/user/test");
		return frb;
	}

}
