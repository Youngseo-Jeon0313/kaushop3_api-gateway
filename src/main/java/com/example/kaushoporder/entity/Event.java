package com.example.kaushoporder.entity;


import com.example.kaushoporder.model.TransactionPhase;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private Long orderId;
    @ColumnDefault("0")
    private TransactionPhase phase;
}
