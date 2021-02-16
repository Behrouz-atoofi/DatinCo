<%@ page import="com.datin.elms.model.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administrator Panel : My profile </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title"> My profile </h1>
<form action="editEmployee">
    <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>

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
            <div class="form-group">
                <label for="Role">My Role </label>
                <input type="text" class="form-control" id="Role" name="Role" value="<%=employee.getRole().getName()%>"
                       readonly>
            </div>

            <%--            <button type="submit" class="btn btn-primary">update</button>--%>
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

            <li class="list_item"><a href="profile">My Profile</a></li>
            <li class="list_item"><a href="manageEmployee?action=employees">employees</a></li>
            <li class="list_item"><a href="manageRequests?action=viewRequests">manage requests</a></li>
            <li class="list_item"><a href="request?action=myRequests">my Requests</a></li>
            <li class="list_item"><a href="email?action=emails">Email</a></li>
            <li class="list_item"><a href="login?action=signOut">Logout</a></li>

        </ul>

    </div>
</div>
<script src="static/Panel/js/panel.js"></script>

</body>
</html>
