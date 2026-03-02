package com.example.BT3_demo.service;

import com.example.BT3_demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> list = Arrays.asList(
            new Category("LAP", "Laptop"),
            new Category("PHO", "Điện thoại"),
            new Category("TAB", "Máy tính bảng")
    );

    public List<Category> findAll() { return list; }

    public Category findById(String id) {
        return list.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}