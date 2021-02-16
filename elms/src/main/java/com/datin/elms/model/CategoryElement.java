package com.datin.elms.model;

import javax.persistence.*;


@Entity
@Table(name = "t_categoryElement")
public class CategoryElement extends entity {


    @Basic
    @Column(name = "C_code", columnDefinition = "TEXT")
    private String code;

    @Basic
    @Column(name = "C_name", columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "C_category")
    private Category category;


    public CategoryElement() {

    }

    public CategoryElement(String code, String name, Category category, String dateCreated, String dateModified) {
        this.code = code;
        this.name = name;
        this.category = category;
        setDateCreated(dateCreated);
        setLastModified(dateModified);
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


}
