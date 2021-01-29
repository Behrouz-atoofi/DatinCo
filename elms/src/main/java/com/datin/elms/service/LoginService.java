package com.datin.elms.service;

import com.datin.elms.model.Employee;
import com.datin.elms.repository.LoginDao;
import org.apache.log4j.Logger;

public class LoginService {

    static Logger log = Logger.getLogger(EmailService.class.getName());
    LoginDao loginDao = new LoginDao();

    public boolean isManager(Employee employee) {

        if (employee.getRole().getCode().equals("manager")
                || employee.getRole().getCode().equals("founder") ||
                employee.getRole().getCode().equals("departmentManager")) {
            return true;
        } else {
            return false;
        }

    }
}