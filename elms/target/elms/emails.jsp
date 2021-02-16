<%@ page import="com.datin.elms.model.Email" %>
<%@ page import="java.util.List" %>
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

<h1 class="page_title">Inbox</h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Row</th>
            <th>subject</th>
            <th>sender</th>
            <th>view</th>


        </tr>
        </thead>

        <%
            int i = 1;
            List<Email> inbox = (List<Email>) request.getAttribute("inbox");

            for (Email email : inbox) {

        %>
        <tr>
            <td><%=i%>
            </td>
            <td><%=email.getSubject()%>
            </td>
            <td><%=email.getSender().getEmail()%>
            </td>
            <td><a href="email?action=viewEmail&id=<%=email.getId()%>"><img src="static/Panel/images/view.png"
                                                                            style="width:30px;height:30px;"></a></td>
        </tr>
        <%
                i++;
            }
        %>
    </table>
</div>

<div class="spacer_box"><p></p></div>

<h1></h1>
<h1></h1>
<h1></h1>

<h1 class="page_title">sent</h1>
<h1></h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Row</th>
            <th>Subject</th>
            <th>Status</th>
            <th>Delete</th>

        </tr>
        </thead>

        <%
            int j = 1;
            List<Email> sent = (List<Email>) request.getAttribute("sent");

            for (Email email : sent) {

        %>
        <tr>
            <td><%=i%>
            </td>
            <td><%=email.getSubject()%>
            </td>
            <td><%=email.getStatus().getName()%>
            </td>
            <td><a href="email?action=deleteEmail&id=<%=email.getId()%>"><img src="static/Panel/images/untick.png"
                                                                              style="width:30px;height:30px;"></a></td>
        </tr>
        <%
                j++;
            }
        %>
    </table>
    <a class="btn btn-primary" href="email?action=emailForm" role="button">send Email</a>
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
            <li class="list_item"><a href="email?action=emails">Email</a></li>
            <li class="list_item"><a href="login?action=signOut">Logout</a></li>

        </ul>

    </div>
</div>
<script src="static/Panel/js/panel.js"></script>
</body>
</html>
