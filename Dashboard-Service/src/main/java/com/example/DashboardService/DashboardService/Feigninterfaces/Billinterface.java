package com.example.DashboardService.DashboardService.Feigninterfaces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "BILL-SERVICE")
public interface Billinterface {
    @GetMapping("bill/count")
    public ResponseEntity<Map<String, Object>> getcount();
}

