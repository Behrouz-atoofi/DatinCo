package model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public @Data class ReceiverDTO {

    @ManyToOne(targetEntity = EmployeeDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name="sender",referencedColumnName = "EMPLOYEE_ID")
    private EmployeeDTO employee;

    @ManyToOne(targetEntity = EmailDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name="receiver",referencedColumnName = "EMAIL_ID")
    private EmailDTO email;
}
