package com.datin.elms.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_category")
public class Category extends entity {


    @Basic
    @Column(name = "c_name", columnDefinition = "TEXT")
    private String name;


    public Category() {

    }

    public Category(String name, String dateCreated, String lastModified) {
        this.name = name;
        setDateCreated(dateCreated);
        setLastModified(lastModified);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
