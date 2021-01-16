<%@ page import="com.datin.elms.model.Attachment" %>
<%@ page import="com.datin.elms.model.Email" %>
<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="com.datin.elms.service.EmailService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administrator Panel : viewEmail </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
</head>
<body>

<h1 class="page_title">Email Details</h1>
<h1></h1>
<div class="container">
    <h1></h1>

    <%Email email = (Email) request.getAttribute("email");%>
    <label for="subject">Subject</label>
    <input type="text" class="form-control" id="subject" name="subject" value="<%=email.getSubject()%>" readonly>

    <label for="receiver">receiver</label>
    <input type="text" class="form-control" id="receiver" name="receiver" value="receivers"
           readonly>

    <label for="sender">Sender</label>
    <input type="text" class="form-control" id="sender" name="content" value="<%=email.getSender().getEmail()%>" readonly>

    <label for="sender">content</label>
    <input type="text" class="form-control" id="sender" name="content" value="<%=email.getContent()%>" readonly>
    <h1></h1>

    <%
        EmailService emailService = new EmailService();
        List<Attachment> attachments = emailService.getEmailAttachments(email.getId());
        if (attachments != null) {
            int i = 1 ;
            for (Attachment attachment : attachments) {

    %>
    <a class="btn btn-primary" href="download?id=<%=attachment.getId()%>" role="button">Download Attachment <%=i%></a>
    <% i++ ; }
    }%>


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
