package model;

import lombok.Data;

import javax.persistence.*;
@Entity(name = "email")
@Table(name="email_TBL")
public @Data class EmailDTO {

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



    @OneToMany(targetEntity = ReceiverDTO.class,fetch = FetchType.EAGER)
    private ReceiverDTO receiver ;

    @ManyToOne(targetEntity = Category_elementDTO.class,fetch =FetchType.EAGER)
    @JoinColumn(name="status" , referencedColumnName = "category_id")
    private Category_elementDTO elements ;

}
