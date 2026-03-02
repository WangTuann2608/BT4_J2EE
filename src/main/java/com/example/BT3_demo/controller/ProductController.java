package com.example.BT3_demo.controller;

import com.example.BT3_demo.model.*;
import com.example.BT3_demo.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("items", productService.findAll());
        return "Product/products";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "Product/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("product") Product p,
                       BindingResult res) {

        if (res.hasErrors()) {
            return "Product/create";
        }

        if (p.getImageFile() != null && !p.getImageFile().isEmpty()) {
            p.setImage(p.getImageFile().getOriginalFilename());
        }

        productService.add(p);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "Product/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") Product p,
                         BindingResult res) {

        if (res.hasErrors()) {
            return "Product/edit";
        }

        if (p.getImageFile() != null && !p.getImageFile().isEmpty()) {
            p.setImage(p.getImageFile().getOriginalFilename());
        }

        productService.update(p);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws Exception {

        Product p = productService.findById(id);

        if (p != null) {

            // Xóa file ảnh nếu có
            if (p.getImage() != null) {
                String uploadDir = "src/main/resources/static/images/";
                java.nio.file.Path path =
                        java.nio.file.Paths.get(uploadDir + p.getImage());

                java.nio.file.Files.deleteIfExists(path);
            }

            productService.delete(id);
        }

        return "redirect:/products?success=delete";
    }
}