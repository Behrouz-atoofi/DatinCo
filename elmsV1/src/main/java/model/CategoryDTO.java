package model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public @Data class CategoryDTO {

    private int category_id ;
    private String name ;

    @OneToMany(targetEntity = Category_elementDTO.class,fetch = FetchType.EAGER)
    private Category_elementDTO elements ;

}
