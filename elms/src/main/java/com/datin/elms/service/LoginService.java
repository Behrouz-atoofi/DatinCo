package com.datin.elms.service;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.LoginDao;
import com.datin.elms.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.SecondaryTableSecondPass;

import java.util.List;

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