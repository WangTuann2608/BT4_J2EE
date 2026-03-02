package com.example.BT3_demo.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá tối thiểu là 1")
    private Double price;

    private String image; // Lưu tên file
    private MultipartFile imageFile; // Nhận file từ form

    @NotBlank(message = "Vui lòng chọn danh mục")
    private String categoryId;
}