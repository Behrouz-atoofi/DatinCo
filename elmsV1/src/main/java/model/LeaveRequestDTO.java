package model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public @Data class LeaveRequestDTO {

    private int leave_id ;
    private String from_date ;
    private String to_date;
    private String reason;

    @ManyToOne(targetEntity = EmployeeDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee" , referencedColumnName = "EMPLOYEE_ID")
    private EmployeeDTO employee;


}
