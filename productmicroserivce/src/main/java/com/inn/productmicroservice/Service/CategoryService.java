package com.inn.productmicroservice.Service;

import com.inn.productmicroservice.POJO.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    public ResponseEntity<String> addCategory(Map<String, String> requestMap);

    public ResponseEntity<List<Category>> getCategories(String filterValue);
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap);

    public ResponseEntity<Map<String, Object>> getCount();
    public ResponseEntity<String> deleteCategory(Integer id);
}
