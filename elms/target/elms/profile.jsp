<%@ page import="com.datin.elms.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.datin.elms.model.CategoryElement" %>
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
    <% Employee me = (Employee) request.getAttribute("me");%>

    <center>
        <div class="col-lg-4 col-lg-offset-4">


            <div class="form-group">
                <label for="id">id</label>
                <input type="text" class="form-control" id="id" name="id" value="<%=me.getId()%>" readonly>
            </div>

            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" class="form-control" id="Name" name="name" value="<%=me.getName()%>">
            </div>

            <div class="form-group">
                <label for="family">Family</label>
                <input type="text" class="form-control" id="family" name="family"value="<%=me.getFamily()%>">
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="<%=me.getUsername()%>">
            </div>
            <div class="form-group">
                <label for="password">Passwrod</label>
                <input type="text" class="form-control" id="password" name="password" value="<%=me.getPassword()%>" >
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" value="<%=me.getEmail()%>" >
            </div>
            <div class="form-group">
                <label for="phonenumber">PhoneNumber</label>
                <input type="text" class="form-control" id="phonenumber" name="phonenumber" value="<%=me.getPhoneNumber()%>" >
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
        <% Employee employee1 = (Employee) request.getSession().getAttribute("employee");%>
        <%="Hi ! " + employee1.getName()%>
        <ul class="list_load">
            <h1>  </h1>
            <li class="list_item"><a href="myProfile">My Profile</a></li>
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
