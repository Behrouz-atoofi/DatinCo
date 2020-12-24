package com.datin.elms.model;

import javax.persistence.*;


@Entity
@Table(name = "T_CATEGORY_ELEMENT")
public class CategoryElement {

    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "C_CODE", columnDefinition = "TEXT")
    private String code;

    @Basic
    @Column(name = "C_NAME", columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "C_CATEGORY")
    private Category category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public CategoryElement() {

    }

    public CategoryElement(int id, String code, String name, Category category) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.category = category;
    }


}
