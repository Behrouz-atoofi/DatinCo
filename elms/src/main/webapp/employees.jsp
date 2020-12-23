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
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Manager</th>
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
            <td><%=employee.getUsername()%></td>
            <td><%=employee.getPassword()%></td>
            <td><%=employee.getEmail()%></td>
            <td><%=employee.getPhoneNumber()%></td>
            <td><%=employee.getRole().getCode()%></td>
            <td><%=employee.getManager().getName()%></td>
            <td><a href="editEmployeeForm?id=<%=employee.getId()%>">Update</a></td>
            <td><a href="deleteEmployee?id=<%=employee.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="btn btn-primary" href="addEmployeeForm" role="button">Add Employee</a>
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
        <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>
        <%="Hi ! " + employee.getName()%>
        <ul class="list_load">
            <h1>  </h1>
            <li class="list_item"><a href="#">My Profile</a></li>
            <li class="list_item"><a href="employees">employees</a></li>
            <li class="list_item"><a href="manageRequests">manage requests</a></li>
            <li class="list_item"><a href="myRequests">my Requests</a></li>
            <li class="list_item"><a href="email">Email</a></li>
            <li class="list_item"><a href="logout">Logout</a></li>

        </ul>
        <%--        <div class="spacer_box"><p>This is a spacer box.</p></div>--%>
    </div>
</div>
<script>$(document).ready(function(){
    // Requires jQuery

    $(document).on('click','.js-menu_toggle.closed',function(e){
        e.preventDefault(); $('.list_load, .list_item').stop();
        $(this).removeClass('closed').addClass('opened');

        $('.side_menu').css({ 'left':'0px' });

        var count = $('.list_item').length;
        $('.list_load').slideDown( (count*.6)*100 );
        $('.list_item').each(function(i){
            var thisLI = $(this);
            timeOut = 100*i;
            setTimeout(function(){
                thisLI.css({
                    'opacity':'1',
                    'margin-left':'0'
                });
            },100*i);
        });
    });

    $(document).on('click','.js-menu_toggle.opened',function(e){
        e.preventDefault(); $('.list_load, .list_item').stop();
        $(this).removeClass('opened').addClass('closed');

        $('.side_menu').css({ 'left':'-250px' });

        var count = $('.list_item').length;
        $('.list_item').css({
            'opacity':'0',
            'margin-left':'-20px'
        });
        $('.list_load').slideUp(300);
    });
});</script>

</body>
</html>
