package com.example.BillMicroservice.BillMicroservice.Service;

import com.example.BillMicroservice.BillMicroservice.POJO.Bill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BillService {
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    public ResponseEntity<List<Bill>> getBills();

    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    public ResponseEntity<String> deleteBill(Integer id);
    public ResponseEntity<Map<String, Object>> getCount();
}
