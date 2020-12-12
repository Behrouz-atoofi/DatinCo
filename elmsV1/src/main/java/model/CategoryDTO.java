package model;

import javax.persistence.*;

@Entity(name = "category")
@Table(name = "category_TBL")
public class CategoryDTO {

    @Id
    @Column(name = "CATEGORY_ID", columnDefinition = "NUMBER")
    private int category_id;

    @Basic
    @Column(name = "NAME", columnDefinition = "VARCHAR2(40)")
    private String name;



}
