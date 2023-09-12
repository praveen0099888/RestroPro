package com.example.BillMicroservice.BillMicroservice.dao;

import com.example.BillMicroservice.BillMicroservice.POJO.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDao extends JpaRepository<Bill, Integer> {
    List<Bill> getBills();

    List<Bill> getBillByUsername(@Param("username") String username);
}
