<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.datin.elms.model.LeaveRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administator Panel : Manage Requests </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title">Manage Requests </h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Request ID</th>
            <th>From Date</th>
            <th>To date</th>
            <th>Reason</th>
            <th>Employee Id</th>
            <th>Employee Family </th>
            <th>Status</th>
            <th>Accept</th>
            <th>Reject</th>

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
            <td><%=leaveRequest.getEmployee().getId()%></td>
            <td><%=leaveRequest.getEmployee().getFamily()%></td>
            <%if (leaveRequest.getStatus().getName().equals("accepted")) {%>
            <td><img src="static/Panel/images/acc.png" style="width:50px;height:50px;" /> </td>
            <%} else if (leaveRequest.getStatus().getName().equals("pending")) {%>
            <td><img src="static/Panel/images/pending.png"  style="width:50px;height:50px;" /> </td>
            <%} else if (leaveRequest.getStatus().getName().equals("rejected")) { %>
            <td><img src="static/Panel/images/rejc.png"  style="width:50px;height:50px;" /> </td>
            <%}%>
            <td><a href="manageRequests?action=acceptRequest&id=<%=leaveRequest.getId()%>">Accept</a></td>
            <td><a href="manageRequests?action=rejectRequest&id=<%=leaveRequest.getId()%>">Reject</a></td>
        </tr>
        <%
            }
        %>
    </table>
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
