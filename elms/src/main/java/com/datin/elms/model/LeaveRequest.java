package com.datin.elms.model;

import javax.persistence.*;

@Entity
@Table(name = "T_LEAVE_REQUEST")
public class LeaveRequest {


    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "C_FROM_DATE", columnDefinition = "TEXT")
    private String from_date;

    @Basic
    @Column(name = "C_TO_DATE", columnDefinition = "TEXT")
    private String to_date;

    @Basic
    @Column(name = "C_REASON", columnDefinition = "TEXT")
    private String reason;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_EMPLOYEE", referencedColumnName = "ID")
    private Employee employee;


}
