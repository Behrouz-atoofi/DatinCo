<%@ page import="com.datin.elms.model.CategoryElement" %>
<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administrator Panel : Edit employee</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title"> Edit Employee </h1>
<form action="editEmployee">
    <% Employee employee = (Employee) request.getAttribute("employee");%>

    <center>
        <div class="col-lg-4 col-lg-offset-4">


            <div class="form-group">
                <label for="id">id</label>
                <input type="text" class="form-control" id="id" name="id" value="<%=employee.getId()%>" readonly>
            </div>

            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" class="form-control" id="Name" name="name" value="<%=employee.getName()%>">
            </div>

            <div class="form-group">
                <label for="family">Family</label>
                <input type="text" class="form-control" id="family" name="family" value="<%=employee.getFamily()%>">
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username"
                       value="<%=employee.getUsername()%>">
            </div>
            <div class="form-group">
                <label for="password">Passwrod</label>
                <input type="text" class="form-control" id="password" name="password"
                       value="<%=employee.getPassword()%>">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" value="<%=employee.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="phonenumber">PhoneNumber</label>
                <input type="text" class="form-control" id="phonenumber" name="phonenumber"
                       value="<%=employee.getPhoneNumber()%>">
            </div>
            <label for="roleName">Role</label>
            <select class="form-control" name="roleName" id="roleName">
                <%
                    List<CategoryElement> roles = (List<CategoryElement>) request.getAttribute("roleList");
                    for (CategoryElement role : roles) {
                %>
                <option value="<%=role.getId()%>"><%=role.getName()%>
                </option>

                <% } %>
            </select>

            <label for="active">Activation</label>
            <select class="form-control" name="active" id="active">
                <option value="1">Active</option>
                <option value="0">DeActive</option>
            </select>


            <div class="form-group">
                <label for="manager">Manager</label>
                <input type="option" class="form-control" id="manager" name="manager"
                       value="<%=employee.getManager().getName()+" "+employee.getManager().getFamily()+" | managerID : "+ employee.getManager().getId()%>" readonly>
            </div>

            <button type="submit" class="btn btn-primary">update</button>
        </div>
    </center>
</form>
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
            <div class="spacer_box"><p><%="Welcome : " + employee.getName()%>
            </p></div>
            <li class="list_item"><a href="myProfile">My Profile</a></li>
            <li class="list_item"><a href="employees">employees</a></li>
            <li class="list_item"><a href="manageRequests">manage requests</a></li>
            <li class="list_item"><a href="myRequests">my Requests</a></li>
            <li class="list_item"><a href="email">Email</a></li>
            <li class="list_item"><a href="logout">Logout</a></li>

        </ul>

    </div>
</div>
<script src="static/Panel/js/panel.js"></script>

</body>
</html>
