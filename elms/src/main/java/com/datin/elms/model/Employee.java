package com.datin.elms.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "T_EMPLOYEE")
public class Employee extends DateTime {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "C_NAME", columnDefinition = "VARCHAR(40)")
    private String name;

    @Basic
    @Column(name = "C_FAMILY", columnDefinition = "VARCHAR(40)")
    private String family;
    @Basic
    @Column(name = "C_USERNAME", columnDefinition = "VARCHAR(40)")
    private String username;
    @Basic
    @Column(name = "C_PASSWORD", columnDefinition = "VARCHAR(40)")
    private String password;
    @Basic
    @Column(name = "C_EMAIL", columnDefinition = "VARCHAR(40)")
    private String email;
    @Basic
    @Column(name = "C_PHONENUMBER", columnDefinition = "VARCHAR(11)")
    private String phoneNumber;
    @Basic
    @Column(name = "C_ISACTIVE", columnDefinition = "Bool")
    private boolean isActive;
    @Basic
    @Column(name = "C_DISABLED", columnDefinition = "Bool")
    private boolean disabled;
    @Basic
    @Column(name = "C_INUSE", columnDefinition = "Bool")
    private boolean inUse;

    @ManyToMany(mappedBy = "receivers")
    private List<Email> Emails= new ArrayList<Email>();


    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "C_MANAGER")
    private Employee manager;

    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "C_ROLE")
    private CategoryElement role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public CategoryElement getRole() {
        return role;
    }

    public void setRole(CategoryElement role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Email> getEmails() {
        return Emails;
    }

    public void setEmails(List<Email> emails) {
        Emails = emails;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabaled) {
        this.disabled = disabaled;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee() {

    }


}
