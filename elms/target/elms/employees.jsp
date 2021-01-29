<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administator Panel : Employees</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title">Employees</h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Family</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Manager</th>
            <th>Active</th>
            <th>update</th>
            <th>delete</th>

        </tr>
        </thead>

        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employees");
            for (Employee employee : employees) {

        %>
        <tr>
            <td><%=employee.getId()%></td>
            <td><%=employee.getName()%></td>
            <td><%=employee.getFamily()%></td>
            <td><%=employee.getEmail()%></td>
            <td><%=employee.getPhoneNumber()%></td>
            <td><%=employee.getRole().getCode()%></td>
            <td><%=employee.getManager().getId()%></td>
            <%if (employee.isActive()) {%>
            <td><img src="static/Panel/images/tick.png" style="width:30px;height:30px;" /> </td>
            <%} else {%>
            <td><img src="static/Panel/images/untick.png"  style="width:30px;height:30px;" /> </td>
            <%}%>
            <td><a href="manageEmployee?action=editEmployeeForm&id=<%=employee.getId()%>"><img src="static/Panel/images/update.png" style="width:30px;height:30px;"></a></td>
            <td><a href="manageEmployee?action=deleteEmployee&id=<%=employee.getId()%>"><img src="static/Panel/images/untick.png" style="width:30px;height:30px;"></a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="btn btn-primary" href="manageEmployee?action=addEmployee" role="button">Add Employee</a>
</div>
<div class="side_menu">
    <div class="burger_box">
        <div class="menu-icon-container">
            <a href="#" class="menu-icon js-menu_toggle closed">
				<span class="menu-icon_box">
					<span class="menu-icon_line menu-icon_line--1"></span>
					<span class="menu-icon_line menu-icon_line--2"></span>
					<span class="menu-icon_line menu-icon_line--3"></span>
				</span>
            </a>
        </div>
    </div>
    <div class="px-5">
        <h2 class="menu_title">Menu</h2>


        <ul class="list_load">

            <h1></h1>
            <li class="list_item"><a href="profile">My Profile</a></li>
            <li class="list_item"><a href="manageEmployee?action=employees">employees</a></li>
            <li class="list_item"><a href="manageRequests?action=viewRequests">manage requests</a></li>
            <li class="list_item"><a href="request?action=myRequests">my Requests</a></li>
            <li class="list_item"><a href="email?action=emails" >Email</a></li>
            <li class="list_item"><a href="login?action=signOut">Logout</a></li>

        </ul>

    </div>
</div>
<script src="static/Panel/js/panel.js"></script>

</body>
</html>
