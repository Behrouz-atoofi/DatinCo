<%@ page import="com.datin.elms.model.Email" %>
<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administator Panel : Email </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title">Mail Box</h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Email ID</th>
            <th>subject</th>
            <th>sender</th>
            <th>view</th>
            <th>delete</th>

        </tr>
        </thead>

        <%
            List<Email> emails = (List<Email>) request.getAttribute("emails");

            for (Email email : emails) {

        %>
        <tr>
            <td><%=email.getId()%></td>
            <td><%=email.getSubject()%></td>
            <td><%=email.getEmail_sender()%></td>
            <td><a href="viewEmail?id=<%=email.getId()%>">view</a></td>
            <td><a href="deleteEmail?id=<%=email.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="btn btn-primary" href="emailForm.jsp" role="button">send Email</a>
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
