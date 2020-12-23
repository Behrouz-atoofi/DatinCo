package com.datin.elms.model;


import javax.persistence.*;

@Entity
@Table(name="T_EMAIL")
public class Email {

    @Id
    @Column(name="ID",columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public byte[] getAttach() {
        return attach;
    }

    public void setAttach(byte[] attach) {
        this.attach = attach;
    }

    @Basic
    @Column(name = "C_SUBJECT", columnDefinition = "TEXT")
    private String subject ;

    @Basic
    @Column(name="C_CONTENT",columnDefinition = "TEXT")
    private String content ;

    @Basic
    @Column(name="C_EMAIL_SENDER",columnDefinition = "TEXT")
    private String email_sender ;

    @Basic
    @Column(name = "C_EMAIL_RECEIVER",columnDefinition = "TEXT")
    private String email_receiver ;

    @Basic
    @Column(name="C_ATTACHMENT",columnDefinition = "BLOB")
    private byte[] attach ;

    @ManyToOne(targetEntity = CategoryElement.class,fetch =FetchType.EAGER)
    @JoinColumn(name="C_STATUS" , referencedColumnName = "ID")
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

    public String getEmail_sender() {
        return email_sender;
    }

    public void setEmail_sender(String email_sender) {
        this.email_sender = email_sender;
    }

    public String getEmail_receiver() {
        return email_receiver;
    }

    public void setEmail_receiver(String email_receiver) {
        this.email_receiver = email_receiver;
    }

    public CategoryElement getStatus() {
        return status;
    }

    public void setStatus(CategoryElement elements) {
        this.status = elements;
    }
}
