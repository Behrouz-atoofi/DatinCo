package com.datin.elms.model;

import javax.persistence.*;

@Entity
@Table(name = "t_leaveRequest")
public class LeaveRequest extends entity {


    @Basic
    @Column(name = "c_fromDate", columnDefinition = "TEXT")
    private String from_date;

    @Basic
    @Column(name = "c_toDate", columnDefinition = "TEXT")
    private String to_date;

    @Basic
    @Column(name = "c_reason", columnDefinition = "TEXT")
    private String reason;


    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "c_employee", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "C_status", referencedColumnName = "id")
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
