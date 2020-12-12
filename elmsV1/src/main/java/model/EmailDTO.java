package model;


import javax.persistence.*;
@Entity(name = "email")
@Table(name="email_TBL")
public class EmailDTO {

    @Id
    @Column(name="EMAIL_ID",columnDefinition = "NUMBER")
    private int email_id ;

    @Basic
    @Column(name = "SUBJECT", columnDefinition = "VARCHAR2(100)")
    private String subject ;

    @Basic
    @Column(name="CONTENT",columnDefinition = "VARCHAR2(255)")
    private String content ;

    @Basic
    @Column(name="EMAIL_SENDER",columnDefinition = "VARCHAR2(100)")
    private String email_sender ;

    @Basic
    @Column(name = "EMAIL_RECEIVER",columnDefinition = "VARCHAR2(100)")
    private String email_receiver ;

    @ManyToOne(targetEntity = Category_elementDTO.class,fetch =FetchType.EAGER)
    @JoinColumn(name="status" , referencedColumnName = "ELEMENT_ID")
    private Category_elementDTO elements ;

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
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

    public Category_elementDTO getElements() {
        return elements;
    }

    public void setElements(Category_elementDTO elements) {
        this.elements = elements;
    }
}
