<%@ page import="java.util.List" %>
<%@ page import="com.datin.elms.model.CategoryElement" %>
<%@ page import="com.datin.elms.model.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administator Panel : Add Employee</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title"> Add Employee </h1>
<form action="addEmployeeToDb" method="post" >
    <center>
        <div class="col-lg-4 col-lg-offset-4">

            <div class="form-group" >
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name">

                <label for="family">Family</label>
                <input type="text" class="form-control" id="family" name="family">

                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username">

                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password">

                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email">

                <label for="phonenumber">PhoneNumber</label>
                <input type="text" class="form-control" id="phonenumber" name="phonenumber">

                <label for="roleName">Role</label>
                <select class="form-control" name="roleName" id="roleName">
                <%
                    List<CategoryElement> roles = (List<CategoryElement>) request.getAttribute("roleList");
                    for (CategoryElement role : roles) {
                %>
                        <option value="<%=role.getId()%>"><%=role.getName()%></option>

                    <% } %>
                </select>

            </div></div>

            <button type="submit" class="btn btn-primary">Add</button>
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
        <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>

        <ul class="list_load">
            <h1>  </h1>
            <div class="spacer_box"><p><%="Welcome : " + employee.getName()%></p></div>
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
