package com.datin.elms.service;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmployeeDao;
import com.github.mfathi91.time.PersianDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.time.format.DateTimeFormatter;
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

    public void saveEmployee(String name, String family, String username, String password, String email, String phoneNumber, int roleId, Employee manager, int isActive) {
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
        employee.setActive(true);
        employee.setDisabled(false);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String now = PersianDate.now().format(dtf);

        employee.setDateCreated(now);
        employee.setLastModified(now);

        EmployeeDao employeeDao = new EmployeeDao();

        employeeDao.saveEmployee(employee);


    }

    public void deleteEmployee(int employeeId) {
        BasicConfigurator.configure();
        EmployeeDao employeeDao = new EmployeeDao();

        if (employeeDao.deleteEmployee(employeeId)) {
            log.info("Employee deleted successfully...");
        } else {
            log.warn("Error for deleting employee ...");
        }

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

    public void updateEmployee(int id, String name, String family, String username, String password, String email, String phoneNumber, int roleId, int isActive) {
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
        employee.setActive(true);
        employee.setLastModified(now);
        EmployeeDao employeeDao = new EmployeeDao();


        if (employeeDao.updateEmployee(employee)) {
            log.info("Employee updated successfully ...");
        } else {
            log.warn("Error for saving changes info ...");
        }


    }

    public List<Employee> getEmployees() {
        BasicConfigurator.configure();

        List<Employee> employeeList = employeeDao.getEmployees();

        log.info("Number of employees is :" + employeeList.size());

        return employeeList;
    }

    public boolean checkExistByEmail(String emails) {
        BasicConfigurator.configure();
        EmployeeDao employeeDao = new EmployeeDao();

        String[] SplitReceiverBox = emails.split(",");

        for (String splitReceiverBox : SplitReceiverBox) {

            if (employeeDao.checkExistByEmail(splitReceiverBox)) {

            } else {
                log.warn("The Email" + splitReceiverBox + "does not exist in db");
                return false;
            }
        }
        return true;

    }

    public boolean isExist(String email, String username) {

        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.isExist(email, username);

    }

    public boolean checkExistForUpdate(int id, String username, String email) {

        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.checkExistForUpdate(id, username, email);

    }


}
