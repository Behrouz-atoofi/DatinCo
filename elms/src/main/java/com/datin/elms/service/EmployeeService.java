package com.datin.elms.service;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.model.EmployeeVO;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmployeeDao;
import com.github.mfathi91.time.PersianDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    static Logger log = Logger.getLogger(EmailService.class.getName());
    EmployeeDao employeeDao = new EmployeeDao();

    public List<CategoryElement> getRoles() {
        BasicConfigurator.configure();
        List<CategoryElement> roleList = employeeDao.getRole();

        if (roleList != null) {
            log.info("All roles loaded successfully ...");
        } else {
            log.warn("Error for loading roles ...");
        }

        return roleList;
    }
    public void saveEmployee(String name, String family, String username, String password, String email, String phoneNumber, int roleId, int managerId) {
        BasicConfigurator.configure();
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setRole(CategoryDao.getElementById(roleId));
        employee.setPhoneNumber(phoneNumber);
        employee.setManager(employeeDao.getEmployeeById(managerId));
        employee.setDisabled(false);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String now = PersianDate.now().format(dtf);

        employee.setDateCreated(now);
        employee.setLastModified(now);

        EmployeeDao employeeDao = new EmployeeDao();

        employeeDao.saveEmployee(employee);


    }
    public void deleteEmployee(int employeeId) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.deleteEmployee(employeeId);

    }
    public Employee getEmployee(int employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        BasicConfigurator.configure();
        if (employee != null) {
            log.info("Employee exists in database ...");
        } else {
            log.warn("Employee does not exists in database ...");
        }
        return employee;
    }
    public void updateEmployee(int id, String name, String family, String username, String password, String email, String phoneNumber, int roleId,int managerId) {
        BasicConfigurator.configure();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String now = PersianDate.now().format(dtf);

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole(CategoryDao.getElementById(roleId));
        employee.setManager(employeeDao.getEmployeeById(managerId));
        employee.setLastModified(now);
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.updateEmployee(employee) ;



    }
    public List<EmployeeVO> getEmployees() {
        BasicConfigurator.configure();

        List<Employee> employeeList = employeeDao.getEmployees();
        List<EmployeeVO> list = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeVO tempEmployee = new EmployeeVO();
            tempEmployee.setId(employee.getId());
            tempEmployee.setName(employee.getName());
            tempEmployee.setFamily(employee.getFamily());
            tempEmployee.setEmail(employee.getEmail());
            tempEmployee.setPhoneNumber(employee.getPhoneNumber());
            tempEmployee.setRole(employee.getRole());
            tempEmployee.setManager(employee.getManager());
            tempEmployee.setActive(employee.getActive());
            tempEmployee.setDisabled(employee.getDisabled());
            list.add(tempEmployee);
        }

        log.info("Number of employees is :" + employeeList.size());

        return list;

    }
    public boolean isExist(String email, String username) {

        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.isExist(email, username);

    }
    public boolean checkExistForUpdate(int id, String username, String email) {

        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.checkExistForUpdate(id, username, email);

    }
    public void deActiveEmployee(int employeeId) {
        employeeDao.deActiveEmployee(employeeId);
    }
    public void activeEmployee(int employeeId) {
        employeeDao.ActiveEmployee(employeeId);
    }
    public List<EmployeeVO>  getManagers( ) {
        List<EmployeeVO> managers = employeeDao.getManagers() ;
        return managers ;
    }


}
