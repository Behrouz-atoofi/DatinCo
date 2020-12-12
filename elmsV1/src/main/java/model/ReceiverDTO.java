package model;


import javax.persistence.*;


@Entity(name = "receiver")
@Table(name = "receiver_TBL")
public class ReceiverDTO {

    @Id
    @Column(name = "ID",columnDefinition = "NUMBER")
    private int id ;

    @ManyToOne(targetEntity = EmployeeDTO.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private EmployeeDTO employee;

    @ManyToOne(targetEntity = EmailDTO.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL_ID", referencedColumnName = "EMAIL_ID")
    private EmailDTO email;
}
