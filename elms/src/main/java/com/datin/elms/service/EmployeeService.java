package com.datin.elms.service;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmployeeDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.List;

public class EmployeeService {
    static Logger log = Logger.getLogger(EmailService.class.getName());
    EmployeeDao employeeDao = new EmployeeDao() ;


    public List<CategoryElement> getRoles() {
        BasicConfigurator.configure();
        List<CategoryElement> roleList = employeeDao.getRole() ;

        if (roleList != null) {
            log.info("All roles loaded successfully ...");
        } else {
            log.warn("Error for loading roles ...");
        }

        return roleList ;
    }

    public void AddEmployee (String name , String family, String username, String password ,String email , String phoneNumber,int roleId  , Employee manager) {
            BasicConfigurator.configure();
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setRole(CategoryDao.getElementById(roleId));
        employee.setPhoneNumber(phoneNumber);
        employee.setManager(manager);


        EmployeeDao employeeDao = new EmployeeDao();

        if ( employeeDao.saveEmployee(employee)) {
            log.info("Employee added successfully ...");
        } else {
            log.warn("Employee Couldn't to be saved ...");
        }


    }

    public void deleteEmployee (int employeeId) {
        BasicConfigurator.configure();
        EmployeeDao employeeDao = new EmployeeDao();

        if ( employeeDao.deleteEmployee(employeeId)) {
            log.info("Employee deleted successfully...");
        } else {
            log.warn("Error for deleting employee ...");
        }

    }

    public Employee getEmployee(int employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId) ;
        BasicConfigurator.configure();
        if (employee !=null) {
            log.info("Employee exists in database ...");
        } else {
            log.warn("Employee does not exists in database ...");
        }
            return employee ;
    }

    public void updateEmployee (int id , String name , String family, String username, String password,String email,String phoneNumber,int roleId) {
        BasicConfigurator.configure();
        Employee employee = new Employee() ;
        employee.setId(id);
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole(CategoryDao.getElementById(roleId));

        EmployeeDao employeeDao = new EmployeeDao() ;


        if (employeeDao.updateEmployee(employee)) {
            log.info("Employee updated successfully ...");
        } else {
            log.warn("Error for saving changes info ...");
        }


    }

    public List<Employee> getEmployees() {
        BasicConfigurator.configure();

        List<Employee> employeeList = employeeDao.getEmployees() ;

        log.info("Number of employees is :" + employeeList.size());

        return employeeList ;
    }
}
