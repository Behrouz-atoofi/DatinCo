<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html dir="rtl" >
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Administator Panel : Request Form </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/Panel/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="static/Panel/js/bootstrap.min.js"></script>
    <script src="static/Panel/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/Panel/css/panel.css">
    <script src="static/Panel/js/kamadatepicker.js"></script>
    <link rel="stylesheet" href="static/Panel/css/kamadatepicker.min.css">
</head>

<body>

<h1 class="page_title">Request Form</h1>
<h1></h1>
<div class="container">
    <h1></h1>
    <form action="request?action=sendRequest" method="post">

        <div class="col-lg-4 col-lg-offset-4">

            <div class="form-group">
                <label for="fromDate">From Date</label>
                <input type="text" class="form-control" id="fromDate" name="fromDate" required="required">

                <label for="toDate">To date </label>
                <input type="text" class="form-control" id="toDate" name="toDate" required="required">

                <label for="reason">Reason</label>
                <input type="text" class="form-control" id="reason" name="reason" required="required" size="10">

            </div>
        </div>

        <button type="submit" class="btn btn-primary">Send Request</button>
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
    <script src="static/Panel/js/panel.js"></script>
    <script> kamaDatepicker('fromDate', {buttonsColor: "red"}); </script>
    <script> kamaDatepicker('toDate', {buttonsColor: "red"}); </script>
</div>
</body>
</html>
