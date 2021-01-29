package com.datin.elms.model;

import javax.persistence.*;


@Entity
@Table(name = "t_employee")
public class Employee extends entity {


    @Basic
    @Column(name = "c_name", columnDefinition = "VARCHAR(40)")
    private String name;

    @Basic
    @Column(name = "c_family", columnDefinition = "VARCHAR(40)")
    private String family;
    @Basic
    @Column(name = "c_username", unique = true, columnDefinition = "VARCHAR(40)")
    private String username;
    @Basic
    @Column(name = "c_password", columnDefinition = "VARCHAR(40)")
    private String password;
    @Basic
    @Column(name = "c_email", unique = true, columnDefinition = "VARCHAR(40)")
    private String email;
    @Basic
    @Column(name = "c_phoneNumber", columnDefinition = "VARCHAR(11)")
    private String phoneNumber;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "c_manager")
    private Employee manager;

    @ManyToOne(targetEntity = CategoryElement.class)
    @JoinColumn(name = "c_role")
    private CategoryElement role;


    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee() {

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


}
