package com.datin.elms.model;

import javax.persistence.*;

@Entity
@Table(name = "T_LEAVE_REQUEST")
public class LeaveRequest extends entity {


    @Basic
    @Column(name = "C_FROM_DATE", columnDefinition = "TEXT")
    private String from_date;

    @Basic
    @Column(name = "C_TO_DATE", columnDefinition = "TEXT")
    private String to_date;

    @Basic
    @Column(name = "C_REASON", columnDefinition = "TEXT")
    private String reason;

    @Basic
    @Column(name = "C_DISABLED", columnDefinition = "Bool")
    private boolean disabled;


    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "C_EMPLOYEE", referencedColumnName = "ID")
    private Employee employee;

    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "C_STATUS", referencedColumnName = "ID")
    private CategoryElement status;


    public CategoryElement getStatus() {
        return status;
    }

    public void setStatus(CategoryElement status) {
        this.status = status;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
