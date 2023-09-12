package com.example.BillMicroservice.BillMicroservice.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;




    Claims claims =null;
    private String userName = null;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;

        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
            claims = jwtUtil.extractAllClamis(token);
        }


        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }



    public  boolean isAdmin(){
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public  boolean isUser(){
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public  String getCurrentUser(){
        return userName;
    }
}
