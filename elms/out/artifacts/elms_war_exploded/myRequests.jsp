<%@ page import="com.datin.elms.model.Email" %>
<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.datin.elms.model.LeaveRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administator Panel : My requests </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title">My requests</h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Request ID</th>
            <th>From Date </th>
            <th>To date</th>
            <th>Reason</th>
            <th>status</th>
            <th>delete</th>

        </tr>
        </thead>

        <%
            List<LeaveRequest> leaveRequests = (List<LeaveRequest>) request.getAttribute("leaveRequests");

            for (LeaveRequest leaveRequest : leaveRequests) {

        %>
        <tr>
            <td><%=leaveRequest.getId()%></td>
            <td><%=leaveRequest.getFrom_date()%></td>
            <td><%=leaveRequest.getTo_date()%></td>
            <td><%=leaveRequest.getReason()%></td>
            <%if (leaveRequest.getStatus().getName().equals("accepted")) {%>
            <td><img src="static/Panel/images/acc.png" style="width:50px;height:50px;" /> </td>
            <%} else {%>
            <td><img src="static/Panel/images/rejc.png"  style="width:50px;height:50px;" /> </td>
            <%}%>
            <td><a href="deleteRequest?id=<%=leaveRequest.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="btn btn-primary" href="requestForm.jsp" role="button">send Request</a>
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
