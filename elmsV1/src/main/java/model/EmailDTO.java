package model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public @Data class EmailDTO {

    private int email_id ;
    private String subject ;
    private String content ;
    private String email_sender ;
    private String email_receiver ;

    @OneToMany(targetEntity = ReceiverDTO.class,fetch = FetchType.EAGER)
    private ReceiverDTO receiver ;

    @ManyToOne(targetEntity = Category_elementDTO.class,fetch =FetchType.EAGER)
    @JoinColumn(name="status" , referencedColumnName = "category_id")
    private Category_elementDTO elements ;

}
