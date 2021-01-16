<%@ page import="com.datin.elms.model.Employee" %>
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


<h1></h1>
<div class="container">
    <h1></h1>

        <h1>Email form </h1>
        <form method="post" action="sendEmail" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Subject: </td>
                    <td><input type="text" name="subject" size="70" required="required" pattern="[A-Za-z0-9]{1,20}{ }" /></td>
                </tr>
                <tr>
                    <td>Receiver: </td>
                    <td><input type="text" name="receiver" size="70" required="required"  /></td>
<%--                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"--%>
                </tr>
                <tr>
                    <td>text: </td>
                    <td><input type="text" name="content" size="70" required="required" pattern="[A-Za-z0-9]{1,20}{ }" /></td>
                </tr>
                <tr>
                    <td>Attach File: </td>
                    <td><input type="file" name="file" multiple /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Send">
                    </td>
                </tr>
            </table>
        </form>


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
