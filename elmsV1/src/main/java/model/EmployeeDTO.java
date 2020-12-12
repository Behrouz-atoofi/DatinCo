package model;


import javax.persistence.*;


@Entity
@Table(name = "EMPLOYEE_TBL")
public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="EMPLOYEE_ID",columnDefinition = "NUMBER")
    private int employee_id ;

    @Basic
    @Column(name="NAME",columnDefinition = "VARCHAR2(100)")
    private String name ;

    @Basic
    @Column(name="FAMILY",columnDefinition = "VARCHAR2(100)")
    private String family ;

    @Basic
    @Column(name = "USERNAME" , columnDefinition = "VARCHAR2(100)")
    private String username ;

    @Basic
    @Column(name = "PASSWORD" , columnDefinition = "VARCHAR2(100)")
    private String password ;

    @Basic
    @Column(name = "EMAIL" , columnDefinition = "VARCHAR2(100)")
    private String email ;

    @Basic
    @Column(name = "PHONENUMBER" , columnDefinition = "VARCHAR2(100)")
    private long phoneNumber ;

    @ManyToOne(targetEntity = EmployeeDTO.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER", referencedColumnName = "EMPLOYEE_ID")
    private EmployeeDTO manager;

    @ManyToOne(targetEntity = Category_elementDTO.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "role" , referencedColumnName = "ELEMENT_ID")
    private Category_elementDTO role ;


    public Category_elementDTO getRole() {
        return role;
    }

    public void setRole(Category_elementDTO role) {
        this.role = role;
    }

    public EmployeeDTO getManager() {
        return manager;
    }

    public void setManager(EmployeeDTO employeeDTO) {
        this.manager = employeeDTO;
    }



    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
