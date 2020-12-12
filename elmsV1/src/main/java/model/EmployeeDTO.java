package model;
import lombok.Data;

import javax.persistence.*;
@Entity(name = "employee")
@Table(name="employee_TBL")
public @Data class EmployeeDTO {

    @Id
    @Column(name="EMPLOYEE_ID",columnDefinition = "NUMBER")
    private int employee_id ;

    @Basic
    @Column(name="NAME",columnDefinition = "VARCHAR2(100)")
    private String name ;

    @Basic
    @Column(name="FAMILY",columnDefinition = "VARCHAR2(100)")
    private String family ;

    @Basic
    @Column(name = "USERNAME" , columnDefinition = "VARCHAR2(100)")
    private String username ;

    @Basic
    @Column(name = "PASSWORD" , columnDefinition = "VARCHAR2(100)")
    private String password ;

    @Basic
    @Column(name = "EMAIL" , columnDefinition = "VARCHAR2(100)")
    private String email ;

    @Basic
    @Column(name = "PHONE_NUMBER" , columnDefinition = "NUMBER")
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
