package com.datin.elms.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "T_EMPLOYEE")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "C_NAME", columnDefinition = "VARCHAR(40)")
    private String name;


    @Column(name = "C_FAMILY", columnDefinition = "VARCHAR(40)")
    private String family;

    @Column(name = "C_USERNAME", columnDefinition = "VARCHAR(40)")
    private String username;

    @Column(name = "C_PASSWORD", columnDefinition = "VARCHAR(40)")
    private String password;

    @Column(name = "C_EMAIL", columnDefinition = "VARCHAR(40)")
    private String email;

    @Column(name = "C_PHONENUMBER", columnDefinition = "VARCHAR(11)")
    private String phoneNumber;

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


    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee() {

    }


}
