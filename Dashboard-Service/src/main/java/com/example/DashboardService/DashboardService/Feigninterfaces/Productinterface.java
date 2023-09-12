package com.example.DashboardService.DashboardService.Feigninterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "PRODUCT-SERVICE",url = "http://localhost:8082")
public interface Productinterface {
    @GetMapping("/category/count")
    public ResponseEntity<Map<String, Object>> getcount();

}
