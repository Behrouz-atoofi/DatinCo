package com.datin.elms.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "t_FILE")
public class Attachment extends entity{

    @Basic
    @Column(name="C_FILE_NAME" ,columnDefinition = "TEXT")
    private String fileName ;


    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name="C_FILE_TYPE")
    private CategoryElement fileType ;

    @Lob
    @Column(name="C_FILE_DATA", nullable=false, columnDefinition = "longblob")
    private Blob data;

    @ManyToOne(targetEntity = Email.class)
    @JoinColumn(name = "EMAIL_ID")
    private Email email ;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CategoryElement getFileType() {
        return fileType;
    }

    public void setFileType(CategoryElement fileType) {
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
