package com.datin.elms.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "t_FILE")
public class EmailFile {

    @Id
    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name="C_FILE_NAME" ,columnDefinition = "TEXT")
    private String fileName ;

    @Basic
    @Column(name="C_FILE_TYPE" , columnDefinition = "TEXT")
    private String fileType ;

    @Lob
    @Column(name="C_FILE_DATA", nullable=false)
    private Blob data;

    @ManyToOne
    @JoinColumn(name ="EMAIL_ID")
    private Email email ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }
}
