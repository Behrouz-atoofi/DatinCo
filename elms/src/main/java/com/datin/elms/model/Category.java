package com.datin.elms.model;

import javax.persistence.*;

@Entity
@Table(name = "T_CATEGORY")
public class Category {

    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "C_NAME", columnDefinition = "TEXT")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Category() {

    }

    public Category(String name) {
        this.name = name;

    }


}
