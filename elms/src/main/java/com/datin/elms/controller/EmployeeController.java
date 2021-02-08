package com.datin.elms.controller;

import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.model.EmployeeVO;
import com.datin.elms.service.EmployeeService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/manageEmployee")
public class EmployeeController extends HttpServlet {

    private HttpSession session;
    private EmployeeService employeeService;


    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String page = "/employees.jsp";

        if (action.equalsIgnoreCase("addEmployee")) {

            List<CategoryElement> roleList = employeeService.getRoles();
            List<EmployeeVO> managerList = employeeService.getManagers() ;
            request.setAttribute("managerList",managerList);
            request.setAttribute("roleList", roleList);
            page = "/addemployee.jsp";

        } else if (action.equalsIgnoreCase("saveEmployee")) {

            String name = request.getParameter("name");
            String family = request.getParameter("family");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phonenumber");
            int roleId = Integer.parseInt(request.getParameter("roleName"));
            int managerId = Integer.parseInt(request.getParameter("manager")) ;


            if (!employeeService.isExist(email, username)) {
                employeeService.saveEmployee(name, family, username, password, email, phoneNumber, roleId, managerId);
            } else {
                request.setAttribute("msg", "Email or Username Exist in Database");
                page = "/error.jsp";
            }

        } else if (action.equalsIgnoreCase("deleteEmployee")) {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employeeService.deleteEmployee(employeeId);

        } else if (action.equalsIgnoreCase("editEmployeeForm")) {
            Employee employee;
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employee = employeeService.getEmployee(employeeId);
            List<EmployeeVO> managerList = employeeService.getManagers() ;
            List<CategoryElement> roleList = employeeService.getRoles();
            request.setAttribute("managerList",managerList);
            request.setAttribute("roleList", roleList);
            request.setAttribute("employee", employee);
            page = "/editemployee.jsp";
        } else if (action.equalsIgnoreCase("editEmployee")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String family = request.getParameter("family");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phonenumber");
            int roleId = Integer.parseInt(request.getParameter("roleName"));
            int managerId = Integer.parseInt(request.getParameter("manager"));

            if (employeeService.checkExistForUpdate(id, username, email)) {
                employeeService.updateEmployee(id, name, family, username, password, email, phoneNumber, roleId,managerId);
            } else {
                request.setAttribute("msg", "Email or Username Exist in Database");
                page = "/error.jsp";
            }

        } else if (action.equalsIgnoreCase("deActiveEmployee")) {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employeeService.deActiveEmployee(employeeId);
            page = "/employees.jsp";

        } else if (action.equalsIgnoreCase("ActiveEmployee")) {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employeeService.activeEmployee(employeeId);
            page = "/employees.jsp";
        }
        List<EmployeeVO> list = employeeService.getEmployees();
        request.setAttribute("employees", list);
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }


}
