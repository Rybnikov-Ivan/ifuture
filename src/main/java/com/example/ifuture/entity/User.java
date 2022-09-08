package com.example.ifuture.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Long value;
}
