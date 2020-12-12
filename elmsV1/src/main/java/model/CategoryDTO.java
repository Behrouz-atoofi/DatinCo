package model;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
@Entity(name = "category")
@Table(name="category_TBL")
public @Data class CategoryDTO {

    @Id
    @Column(name="CATEGORY_ID",columnDefinition = "NUMBER")
    private int category_id ;

    @Basic
    @Column(name="NAME", columnDefinition = "VARCHAR2(40)")
    private String name ;

    @OneToMany(targetEntity = Category_elementDTO.class,fetch = FetchType.EAGER)
    private Category_elementDTO elements ;

}
