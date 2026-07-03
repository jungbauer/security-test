package com.jungbauer.securitytest.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TestStuff {
    @Id
    private Long id;

    private String name;
    private String message;
    private Integer count;
}
