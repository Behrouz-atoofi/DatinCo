package com.datin.elms.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "T_EMAIL")
public class Email extends DateTime {

    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "C_SUBJECT", columnDefinition = "TEXT")
    private String subject;

    @Basic
    @Column(name = "C_CONTENT", columnDefinition = "TEXT")
    private String content;

    @ManyToOne (targetEntity = Employee.class)
    @JoinColumn(name="C_SENDER" , referencedColumnName = "ID")
    private Employee sender ;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "T_EMAIL_EMPLOYEE",
            joinColumns = @JoinColumn(name = "EMAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    private List<Employee> receivers = new ArrayList<Employee>();

    @Basic
    @Column (name = "C_ATTACHMENT" ,columnDefinition = "BOOLEAN")
    private Boolean attachment ;


    @Basic
    @Column (name = "C_DISABLED",columnDefinition = "BOOLEAN")
    private Boolean disabled ;

    @OneToMany(targetEntity = Attachment.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "EMAIL_ID")
    private Set<Attachment> attachments  ;


    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "C_STATUS", referencedColumnName = "ID")
    private CategoryElement status;




    public int getId() {
        return id;
    }

    public void setId(int email_id) {
        this.id = email_id;
    }

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

    public void setReceivers(List<Employee> receivers) {
        this.receivers = receivers;
    }

    public CategoryElement getStatus() {
        return status;
    }

    public void setStatus(CategoryElement elements) {
        this.status = elements;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }
}
