package com.example.BillMicroservice.BillMicroservice.Rest;


import com.example.BillMicroservice.BillMicroservice.POJO.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
public interface BillRest {
    @PostMapping("/generate-report")
    public ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);
    @GetMapping("getbills")
    public ResponseEntity<List<Bill>> getBills();
    @PostMapping("/get-pdf")
    public ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> requestMap);
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Integer id);
    @GetMapping("/count")
    public  ResponseEntity<Map<String, Object>> getcount();
}
