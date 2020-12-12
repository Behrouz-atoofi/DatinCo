package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Locale;


@Entity(name = "element")
@Table(name="category_element_TBL")
public @Data class Category_elementDTO {


    @Id
    @Column(name = "ELEMENT_ID",columnDefinition = "NUMBER")
    private int element_id ;

    @Basic
    @Column(name = "CODE" , columnDefinition = "VARCHAR2(40)")
    private String code ;

    @Basic
    @Column(name="NAME",columnDefinition = "VARCHAR2(40)")
    private String name ;


}
