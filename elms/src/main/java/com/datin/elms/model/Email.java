package com.datin.elms.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_email")
public class Email extends entity {

    @Basic
    @Column(name = "C_subject", columnDefinition = "TEXT")
    private String subject;

    @Basic
    @Column(name = "C_content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "C_sender", referencedColumnName = "id")
    private Employee sender;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "mm_email_employee",
            joinColumns = @JoinColumn(name = "c_emailId"),
            inverseJoinColumns = @JoinColumn(name = "c_EmployeeId")
    )
    private List<Employee> receivers = new ArrayList<Employee>();


    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "c_status", referencedColumnName = "id")
    private CategoryElement status;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public List<Employee> getReceivers() {
        return receivers;
    }

    public CategoryElement getStatus() {
        return status;
    }

    public void setStatus(CategoryElement elements) {
        this.status = elements;
    }


}
