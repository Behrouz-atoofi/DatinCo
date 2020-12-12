package model;

import javax.persistence.*;


@Entity(name = "element")
@Table(name = "category_element_TBL")
public class Category_elementDTO {


    @Id
    @Column(name = "ELEMENT_ID", columnDefinition = "NUMBER")
    private int element_id;

    @Basic
    @Column(name = "CODE", columnDefinition = "VARCHAR2(40)")
    private String code;

    @Basic
    @Column(name = "NAME", columnDefinition = "VARCHAR2(40)")
    private String name;

    @ManyToOne(targetEntity = CategoryDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY" , referencedColumnName = "CATEGORY_ID")
    private int category ;


    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
