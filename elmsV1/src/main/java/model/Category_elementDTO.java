package model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Locale;

public @Data class Category_elementDTO {

    private int element_id ;
    private String code ;
    private String name ;


}
