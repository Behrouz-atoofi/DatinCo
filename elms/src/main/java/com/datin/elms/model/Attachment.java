package com.datin.elms.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "t_file")
public class Attachment extends entity {

    @Basic
    @Column(name = "c_fileName", columnDefinition = "TEXT")
    private String fileName;

    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "c_fileType")
    private CategoryElement fileType;

    @Lob
    @Column(name = "c_fileData", nullable = false, columnDefinition = "longblob")
    private Blob data;

    @ManyToOne(targetEntity = Email.class)
    @JoinColumn(name = "c_emailId")
    private Email email;


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
