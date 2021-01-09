package com.datin.elms.service;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmployeeDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void AddEmployee (String name , String family, String username, String password ,String email , String phoneNumber,int roleId , Employee manager,int isActive) {
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
        employee.setActive(isActive == 1);
        employee.setDisabled(false);
        employee.setInUse(false);

        Date dTime = new Date( );
        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
        String date_created = df.format(dTime);

        employee.setDate_created(date_created);
        employee.setLast_modified(date_created);

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

    public void updateEmployee (int id , String name , String family, String username, String password,String email,String phoneNumber,int roleId,int isActive,boolean inUse) {
        BasicConfigurator.configure();

        Date dTime = new Date( );
        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
        String last_modified = df.format(dTime);

        Employee employee = new Employee() ;
        employee.setId(id);
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole(CategoryDao.getElementById(roleId));
        employee.setActive(isActive == 1);
        employee.setInUse(inUse);
        employee.setLast_modified(last_modified);
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

    public void setInUse(int id , boolean inUse ) {

        EmployeeDao employeeDao = new EmployeeDao() ;

        if (employeeDao.setInUse(id,inUse)) {
            log.info("InUse column updated");
        } else {
            log.info("The status of InUse column couldn't be changed");
        }
    }

    public boolean checkInUse (int employeeId) {
        EmployeeDao employeeDao = new EmployeeDao() ;

        return employeeDao.isInUse(employeeId) ;

    }
}
