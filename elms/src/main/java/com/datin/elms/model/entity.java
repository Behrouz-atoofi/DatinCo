package com.datin.elms.model;


import javax.persistence.*;

@MappedSuperclass
public class entity {

    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "C_DATE_CREATED", columnDefinition = "VARCHAR(40)")
    private String date_created ;

    @Basic
    @Column(name = "C_LAST_MODIFIED", columnDefinition = "VARCHAR(40)")
    private String last_modified ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }
}
