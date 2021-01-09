package com.datin.elms.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Basic
    @Column(name = "C_EMAIL_SENDER", columnDefinition = "TEXT")
    private String email_sender;

    @Basic
    @Column(name = "C_EMAIL_RECEIVER", columnDefinition = "TEXT")
    private String email_receiver;

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
