package com.example.kaushoporder.entity;

import com.example.kaushoporder.model.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private Long productId;
    @ColumnDefault("0")
    private OrderStatus status;
}
