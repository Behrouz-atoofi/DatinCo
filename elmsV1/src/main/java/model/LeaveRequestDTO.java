package model;

import lombok.Data;

import javax.persistence.*;
@Entity(name = "leave_request")
@Table(name="leaveRequest_TBL")
public @Data class LeaveRequestDTO {


    @Id
    @Column(name = "LEAVE_ID", columnDefinition = "NUMBER")
    private int leave_id ;

    @Basic
    @Column(name = "FROM_DATE", columnDefinition = "VARCHAR2(100)")
    private String from_date ;

    @Basic
    @Column(name = "TO_DATE",columnDefinition = "VARCHAR2(100)")
    private String to_date;

    @Basic
    @Column(name = "REASON" , columnDefinition = "VARCHAR2(255)")
    private String reason;

    @ManyToOne(targetEntity = EmployeeDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee" , referencedColumnName = "EMPLOYEE_ID")
    private EmployeeDTO employee;


}
