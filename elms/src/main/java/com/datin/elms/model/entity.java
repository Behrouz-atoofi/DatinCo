package com.datin.elms.model;


import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class entity implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Basic
    @Column(name = "c_disabled", columnDefinition = "BOOLEAN")
    private Boolean disabled = false;

    @Basic
    @Column(name = "c_Active", columnDefinition = "BOOLEAN")
    private boolean active = true;

    @Basic
    @Column(name = "c_manualId", columnDefinition = "INT")
    private int manualId = 0;

    @Version
    @Column(name = "c_version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version = 0;

    @Basic
    @Column(name = "C_DateCreated", columnDefinition = "VARCHAR(40)")
    private String dateCreated;

    @Basic
    @Column(name = "C_lastModified", columnDefinition = "VARCHAR(40)")
    private String lastModified;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isActive() {
        return active;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getManualId() {
        return manualId;
    }

    public void setManualId(int manualId) {
        this.manualId = manualId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
