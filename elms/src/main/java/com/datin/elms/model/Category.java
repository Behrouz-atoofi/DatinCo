package com.datin.elms.model;

import javax.persistence.*;

@Entity
@Table(name = "T_CATEGORY")
public class Category extends entity {


    @Basic
    @Column(name = "C_NAME", columnDefinition = "TEXT")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Category() {

    }

    public Category(String name,String dateCreated,String lastModified) {
        this.name = name;
        setDate_created(dateCreated);
        setLast_modified(lastModified);
    }


}
