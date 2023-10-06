package com.example.pizzaDelivery.Filter;

import com.example.pizzaDelivery.Service.Implementation.JwtGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            final String authHeader = request.getHeader("Authorization");
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(request, response);
            } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                throw new ServletException("Invalid Token or No Token Found");
            }
            String token = authHeader.substring(7);

            Claims claims = Jwts.parserBuilder().setSigningKey(JwtGenerator.SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            request.setAttribute("claims", claims);

            filterChain.doFilter(request, response);
        }
        catch (Exception ex) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + ex.getMessage());
        }

    }

}
