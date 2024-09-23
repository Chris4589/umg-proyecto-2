package com.example.proyecto2.dto;

import com.example.proyecto2.domain.Product;
import com.example.proyecto2.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductDto extends Product {
    private List<Long> categories;

    @Override
    public void validate() {
        if (this.categories == null || this.categories.isEmpty()) {
            throw new BadRequestException("Categories list is empty");
        }
        super.validate();
    }

    public Product toProduct() {
        var product = new Product();
        BeanUtils.copyProperties(this, product, "categories");
        return product;
    }
}
