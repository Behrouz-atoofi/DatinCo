package com.datin.elms.model;

import javax.persistence.*;


@Entity
@Table(name = "T_CATEGORY_ELEMENT")
public class CategoryElement extends entity{


    @Basic
    @Column(name = "C_CODE", columnDefinition = "TEXT")
    private String code;

    @Basic
    @Column(name = "C_NAME", columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "C_CATEGORY")
    private Category category;


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

    public CategoryElement(String code, String name, Category category,String dateCreated,String dateModified) {
        this.code = code;
        this.name = name;
        this.category = category;
        setDate_created(dateCreated);
        setLast_modified(dateModified);
    }


}
