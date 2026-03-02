package com.example.BT3_demo.service;

import com.example.BT3_demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> list = new ArrayList<>();

    public List<Product> findAll() { return list; }
    public void add(Product p) {
        p.setId(list.size() + 1);
        list.add(p);
    }
    public Product findById(Integer id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
    public void update(Product p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(p.getId())) {
                list.set(i, p);
                break;
            }
        }
    }
}