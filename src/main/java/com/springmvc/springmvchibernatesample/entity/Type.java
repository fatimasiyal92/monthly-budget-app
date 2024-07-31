package com.springmvc.springmvchibernatesample.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "type", schema = "monthly-budget")
public class Type {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 45)
    @Column(name = "type", length = 45)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}