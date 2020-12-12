package model;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public @Data class EmployeeDTO {

    private int employee_id ;
    private String name ;
    private String family ;
    private String username ;
    private String password ;
    private String email ;
    private long phoneNumber ;

    @OneToMany(targetEntity = EmployeeDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name= "manager" , referencedColumnName = "MANAGER")
    private EmployeeDTO employees ;

    @ManyToOne(targetEntity = Category_elementDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "role" , referencedColumnName = "CATEGORY_ID")
    private Category_elementDTO elements ;

    @OneToMany(targetEntity = ReceiverDTO.class,fetch = FetchType.EAGER)
    private ReceiverDTO receiver;

}
