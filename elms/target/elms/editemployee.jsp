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
    <style>@import url(static/Panel/css/font.css);
    body {
        background: #36BDD1;
        background: -webkit-linear-gradient(left top, #027, #36BDD1);
        background: -moz-linear-gradient(bottom right, #027, #36BDD1);
        background: linear-gradient(to bottom right, #027, #36BDD1);
        color: #fff;
        font-family: 'Raleway', Helvetica, Arial, sans-serif;
        font-size: 18px;
        font-weight: 300;
        line-height: 140%;
        margin: 0;
        min-height: 100vh;
        padding: 0;
        width: 100%;
    }
    h1.page_title{color:#fff;}
    h4.what_to_do{color:#fff;}
    h2.menu_title{color:#fff;}
    .page_title,
    .what_to_do {
        font-weight: 300;
        line-height: 120%;
        text-align: center;
        text-shadow: 0 1px 5px rgba(0,0,0,.8);
        text-transform: uppercase;
    }

    /* PEN STYLES ========== */
    a,
    .side_menu {
        -webkit-transition: all 300ms ease-in-out;
        transition: all 300ms ease-in-out;
    }

    /* MENU CONTAINER ----- */
    .side_menu {
        background: rgba(0,20,60,.9);
        height: 100vh;
        left: -250px;
        position: absolute;
        top: 0;
        width: 250px;
    }
    .side_menu .container {
        padding: 0 1em;
    }

    /* HAMBURGER STYLES ----- */
    .burger_box {
        display: block;
        float: right;
        margin-right: -45px;
    }
    .burger_box a.menu-icon {
        display: inline-block;
        float: none;
        height: 43px;
        padding: 10px;
        opacity: .5;
        width: 45px;
        z-index: 100;
    }
    .burger_box a.menu-icon:hover,
    .burger_box a.menu-icon.opened {
        opacity: 1;
    }
    .burger_box a.menu-icon.opened {
        background: rgba(0,20,60,.9);
    }
    .burger_box .menu-icon_box {
        display: inline-block;
        height: 25px;
        position: relative;
        text-align: left;
        width: 25px;
    }
    .burger_box .menu-icon_line {
        background: #fff;
        border-radius: 2px;
        display: inline-block;
        height: 3px;
        position: absolute;
        width: 100%;
    }
    .burger_box .menu-icon_line--1 {
        top: 2px;
    }
    .burger_box .menu-icon_line--2 {
        top: 10px;
    }
    .burger_box .menu-icon_line--3 {
        top: 18px;
    }
    .burger_box .menu-icon_line--1 {
        transition: top 200ms 250ms, transform 200ms;
        -webkit-transition: top 200ms 250ms, -webkit-transform 200ms;
    }
    .burger_box .menu-icon_line--2 {
        transition: opacity 0ms 300ms;
        -webkit-transition: opacity 0ms 300ms;
    }
    .burger_box .menu-icon_line--3 {
        transition: top 100ms 300ms, transform 200ms;
        -webkit-transition: top 100ms 300ms, -webkit-transform 200ms;
    }
    .burger_box .menu-icon.opened .menu-icon_box {
        transform: scale3d(0.9, 0.9, 0.9);
        -webkit-transform: scale3d(0.9, 0.9, 0.9);
    }
    .burger_box .menu-icon.opened .menu-icon_line {
        top: 10px;
    }
    .burger_box .menu-icon.opened .menu-icon_line--1 {
        transform: rotate3d(0, 0, 1, 45deg);
        -webkit-transform: rotate3d(0, 0, 1, 45deg);
        transition: top 100ms, transform 200ms 250ms;
        -webkit-transition: top 100ms, -webkit-transform 200ms 250ms;
    }
    .burger_box .menu-icon.opened .menu-icon_line--2 {
        opacity: 0;
        transition: opacity 200ms;
        -webkit-transition: opacity 200ms;
    }
    .burger_box .menu-icon.opened .menu-icon_line--3 {
        transform: rotate3d(0, 0, 1, -45deg);
        -webkit-transform: rotate3d(0, 0, 1, -45deg);
        transition: top 200ms, transform 200ms 250ms;
        -webkit-transition: top 200ms, -webkit-transform 200ms 250ms;
    }

    /* STAGGER LIST ----- */
    .list_load {
        display: none;
        list-style: none;
        padding: 0;
    }
    .list_item {
        margin-left: -20px;
        opacity: 0;
        -webkit-transition: all 200ms ease-in-out;
        transition: all 200ms ease-in-out;
    }
    .list_item a {
        color: #fff;
        display: block;
        padding: 5px 10px;
        text-decoration: none;
    }
    .list_item a:hover {
        background: rgba(255,255,255,.2);
    }</style>
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
                <input type="text" class="form-control" id="family" name="family"value="<%=employee.getFamily()%>">
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="<%=employee.getUsername()%>">
            </div>
            <div class="form-group">
                <label for="password">Passwrod</label>
                <input type="text" class="form-control" id="password" name="password" value="<%=employee.getPassword()%>" >
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" value="<%=employee.getEmail()%>" >
            </div>
            <div class="form-group">
                <label for="phonenumber">PhoneNumber</label>
                <input type="text" class="form-control" id="phonenumber" name="phonenumber" value="<%=employee.getPhoneNumber()%>" >
            </div>
            <div class="form-group">
                <label for="role">Role</label>
                <input type="option" class="form-control" id="role" name="option" value="<%=employee.getRole()%>" >
            </div>

            <div class="form-group">
                <label for="manager">Manager</label>
                <input type="option" class="form-control" id="manager" name="manager" value="<%=employee.getManager()%>" >
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
        <% Employee employee1 = (Employee) request.getSession().getAttribute("employee");%>
        <%="Hi ! " + employee1.getName()%>
        <ul class="list_load">
            <li class="list_item"><a href="#">My Profile</a></li>
            <li class="list_item"><a href="employees">employees</a></li>
            <li class="list_item"><a href="requests">Requests</a></li>
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
