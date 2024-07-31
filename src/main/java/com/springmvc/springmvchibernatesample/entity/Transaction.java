package com.springmvc.springmvchibernatesample.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transaction", schema = "monthly-budget")
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}