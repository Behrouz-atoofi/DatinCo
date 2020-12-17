package com.datin.elms.controller.mangeEmployee;
import com.datin.elms.model.Employee;
import com.datin.elms.service.EmployeeService;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class GetEmployees extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeService() ;
        List<Employee> employeeList =employeeService.getEmployees() ;
        System.out.println(employeeList);
        req.setAttribute("employees",employeeList);
        req.getRequestDispatcher("employees.jsp").forward(req,res);
    }
}
