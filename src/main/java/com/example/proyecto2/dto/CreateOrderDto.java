package com.example.proyecto2.dto;

import com.example.proyecto2.domain.Order;
import com.example.proyecto2.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto extends Order {
    private List<Long> products;

    @Override
    public void validate() {
        if (this.products == null || this.products.isEmpty()) {
            throw new BadRequestException("Products list is empty");
        }
        super.validate();
    }

    public Order toOrder() {
        var order = new Order();
        BeanUtils.copyProperties(this, order, "products");
        return order;
    }
}
