package com.example.DashboardService.DashboardService.Restimpl;

import com.example.DashboardService.DashboardService.Feigninterfaces.Billinterface;
import com.example.DashboardService.DashboardService.Feigninterfaces.Productinterface;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardImpl{
    @Autowired
    private final Productinterface productinterface;

    @Autowired
    private final Billinterface billinterface;
    @GetMapping("/count")
    @CircuitBreaker(name="Count_service",fallbackMethod = "countfallbackmethod")
    public ResponseEntity<Map<String, Object>> getcount() {
        ResponseEntity<Map<String, Object>> ab= productinterface.getcount();
        ResponseEntity<Map<String, Object>> cd= billinterface.getcount();

        Map<String, Object> count = ab.getBody();
        Map<String, Object> count1 = cd.getBody();
        count1.putAll(count);

        return new ResponseEntity<>(count1, HttpStatus.OK);

    }

    public ResponseEntity<Map<String, Object>> countfallbackmethod(Exception ex){

        Map<String, Object> map = new HashMap<>();
        map.put("fallback","product or bill microservice is down");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
