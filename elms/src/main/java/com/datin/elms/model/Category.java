package com.datin.elms.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CATEGORY")
public class Category {

    public Category() {

    }

    public Category(int id , String name) {
        this.id = id ;
        this.name = name;

    }


    @Id
    @Column(name = "ID", columnDefinition = "INT")
    public int id;

    @Basic
    @Column(name = "C_NAME", columnDefinition = "TEXT")
    private String name;

    @OneToMany(targetEntity = Category_element.class , fetch = FetchType.EAGER)
    private List<Category_element> category_elements ;

    public List<Category_element> getCategory_elements() {
        return category_elements;
    }

    public void setCategory_elements(List<Category_element> category_elements) {
        this.category_elements = category_elements;
    }

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

}
