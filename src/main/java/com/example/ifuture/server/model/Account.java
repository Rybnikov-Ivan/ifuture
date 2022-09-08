package com.example.ifuture.server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Long value;

    public Account(Integer id, Long value) {
        this.id = id;
        this.value = value;
    }

    public Account() {}
}
