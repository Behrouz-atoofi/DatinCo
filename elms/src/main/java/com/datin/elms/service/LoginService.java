package com.datin.elms.service;

import com.datin.elms.model.Employee;

public class LoginService {

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