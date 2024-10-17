<%--
  Created by IntelliJ IDEA.
  User: Kasun
  Date: 10/14/2024
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="project.core.api.CoreAPI" %>
<%@ page import="project.features.tickets.model.dao.Ticket" %>
<%@ page import="java.util.List" %>
<%
    HttpSession session1 = request.getSession(false);

    if (!CoreAPI.getInstance().isUserLoggedIn(session1)) {
        response.sendRedirect("login.jsp");
        return;

    }else{

        if (!CoreAPI.getInstance().isUserLoggedInAndHasPermission(session1, "view_tickets")) {
            response.sendRedirect("errors/noperm.jsp");
            return;
        }

    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">


    <title>Open Help Desk | Tickets Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
        body{margin-top:20px;
            background:#eee;
        }

        .padding {
            padding: 10px;
        }

        /* GRID */
        .col {
            padding: 10px 20px;
            margin-bottom: 10px;
            background: #fff;
            color: #666666;
            text-align: center;
            font-weight: 400;
            box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.1);
        }

        .row h3 {
            color: #666666;
        }

        .row.grid {
            margin-left: 0;
        }

        .grid {
            position: relative;
            width: 100%;
            background: #fff;
            color: #666666;
            border-radius: 2px;
            margin-bottom: 25px;
            box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.1);
        }

        .grid .grid-header {
            position: relative;
            border-bottom: 1px solid #ddd;
            padding: 15px 10px 10px 20px;
        }

        .grid .grid-header:before,
        .grid .grid-header:after {
            display: table;
            content: " ";
        }

        .grid .grid-header:after {
            clear: both;
        }

        .grid .grid-header span,
        .grid .grid-header > .fa {
            display: inline-block;
            margin: 0;
            font-weight: 300;
            font-size: 1.5em;
            float: left;
        }

        .grid .grid-header span {
            padding: 0 5px;
        }

        .grid .grid-header > .fa {
            padding: 5px 10px 0 0;
        }

        .grid .grid-header > .grid-tools {
            padding: 4px 10px;
        }

        .grid .grid-header > .grid-tools a {
            color: #999999;
            padding-left: 10px;
            cursor: pointer;
        }

        .grid .grid-header > .grid-tools a:hover {
            color: #666666;
        }

        .grid .grid-body {
            padding: 15px 20px 15px 20px;
            font-size: 0.9em;
            line-height: 1.9em;
        }

        .grid .full {
            padding: 0 !important;
        }

        .grid .transparent {
            box-shadow: none !important;
            margin: 0px !important;
            border-radius: 0px !important;
        }

        .grid.top.black > .grid-header {
            border-top-color: #000000 !important;
        }

        .grid.bottom.black > .grid-body {
            border-bottom-color: #000000 !important;
        }

        .grid.top.blue > .grid-header {
            border-top-color: #007be9 !important;
        }

        .grid.bottom.blue > .grid-body {
            border-bottom-color: #007be9 !important;
        }

        .grid.top.green > .grid-header {
            border-top-color: #00c273 !important;
        }

        .grid.bottom.green > .grid-body {
            border-bottom-color: #00c273 !important;
        }

        .grid.top.purple > .grid-header {
            border-top-color: #a700d3 !important;
        }

        .grid.bottom.purple > .grid-body {
            border-bottom-color: #a700d3 !important;
        }

        .grid.top.red > .grid-header {
            border-top-color: #dc1200 !important;
        }

        .grid.bottom.red > .grid-body {
            border-bottom-color: #dc1200 !important;
        }

        .grid.top.orange > .grid-header {
            border-top-color: #f46100 !important;
        }

        .grid.bottom.orange > .grid-body {
            border-bottom-color: #f46100 !important;
        }

        .grid.no-border > .grid-header {
            border-bottom: 0px !important;
        }

        .grid.top > .grid-header {
            border-top-width: 4px !important;
            border-top-style: solid !important;
        }

        .grid.bottom > .grid-body {
            border-bottom-width: 4px !important;
            border-bottom-style: solid !important;
        }


        /* SUPPORT TICKET */
        .support ul {
            list-style: none;
            padding: 0px;
        }

        .support ul li {
            padding: 8px 10px;
        }

        .support ul li a {
            color: #999;
            display: block;
        }

        .support ul li a:hover {
            color: #666;
        }

        .support ul li.active {
            background: #0073b7;
        }

        .support ul li.active a {
            color: #fff;
        }

        .support ul.support-label li {
            padding: 2px 0px;
        }

        .support h2,
        .support-content h2 {
            margin-top: 5px;
        }

        .support-content .list-group li {
            padding: 15px 20px 12px 20px;
            cursor: pointer;
        }

        .support-content .list-group li:hover {
            background: #eee;
        }

        .support-content .fa-padding .fa {
            padding-top: 5px;
            width: 1.5em;
        }

        .support-content .info {
            color: #777;
            margin: 0px;
        }

        .support-content a {
            color: #111;
        }

        .support-content .info a:hover {
            text-decoration: underline;
        }

        .support-content .info .fa {
            width: 1.5em;
            text-align: center;
        }

        .support-content .number {
            color: #777;
        }

        .support-content img {
            margin: 0 auto;
            display: block;
        }

        .support-content .modal-body {
            padding-bottom: 0px;
        }

        .support-content-comment {
            padding: 10px 10px 10px 30px;
            background: #eee;
            border-top: 1px solid #ccc;
        }


        /* BACKGROUND COLORS */
        .bg-red, .bg-yellow, .bg-aqua, .bg-blue, .bg-light-blue, .bg-green, .bg-navy, .bg-teal, .bg-olive, .bg-lime, .bg-orange, .bg-fuchsia, .bg-purple, .bg-maroon, bg-gray, bg-black, .bg-red a, .bg-yellow a, .bg-aqua a, .bg-blue a, .bg-light-blue a, .bg-green a, .bg-navy a, .bg-teal a, .bg-olive a, .bg-lime a, .bg-orange a, .bg-fuchsia a, .bg-purple a, .bg-maroon a, bg-gray a, .bg-black a {
            color: #f9f9f9 !important;
        }
        .bg-white, .bg-white a {
            color: #999999 !important;
        }
        .bg-red {
            background-color: #f56954 !important;
        }
        .bg-yellow {
            background-color: #f39c12 !important;
        }
        .bg-aqua {
            background-color: #00c0ef !important;
        }
        .bg-blue {
            background-color: #0073b7 !important;
        }
        .bg-light-blue {
            background-color: #3c8dbc !important;
        }
        .bg-green {
            background-color: #00a65a !important;
        }
        .bg-navy {
            background-color: #001f3f !important;
        }
        .bg-teal {
            background-color: #39cccc !important;
        }
        .bg-olive {
            background-color: #3d9970 !important;
        }
        .bg-lime {
            background-color: #01ff70 !important;
        }
        .bg-orange {
            background-color: #ff851b !important;
        }
        .bg-fuchsia {
            background-color: #f012be !important;
        }
        .bg-purple {
            background-color: #932ab6 !important;
        }
        .bg-maroon {
            background-color: #85144b !important;
        }
        .bg-gray {
            background-color: #eaeaec !important;
        }
        .bg-black {
            background-color: #222222 !important;
        }

    </style>

    <style type="text/css">
        body{
            background:#f4f3ef;
        }

        #wrapper {
            padding-left: 0;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
        }

        #wrapper.toggled {
            padding-left: 250px;
        }

        #sidebar-wrapper {
            z-index: 1000;
            position: fixed;
            left: 250px;
            width: 0;
            height: 100%;
            margin-left: -250px;
            overflow-y: auto;
            background:#fff;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
        }

        #sidebar-wrapper {
            box-shadow: inset -1px 0px 0px 0px #DDDDDD;
        }

        #wrapper.toggled #sidebar-wrapper {
            width: 250px;
        }

        #page-content-wrapper {
            width: 100%;
            position: absolute;
            padding: 15px;
        }

        #wrapper.toggled #page-content-wrapper {
            position: absolute;
            margin-right: -250px;
        }

        /* Sidebar Styles */

        .sidebar-nav {
            position: absolute;
            top: 0;
            width: 250px;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .sidebar-nav li {
            text-indent: 20px;
            line-height: 40px;
        }

        .sidebar-nav li a {
            display: block;
            text-decoration: none;
            color: #999999;
        }

        .sidebar-nav li a:hover {
            text-decoration: none;
        }

        .sidebar-nav li a:active,
        .sidebar-nav li a:focus {
            text-decoration: none;
        }

        .sidebar-nav > .sidebar-brand {
            height: 65px;
            font-size: 18px;
            line-height: 60px;
        }

        .sidebar-nav > .sidebar-brand a {
            color: #999999;
        }

        .sidebar-nav > .sidebar-brand a:hover {
            color: #fff;
            background: none;
        }

        @media(min-width:768px) {
            #wrapper {
                padding-left: 250px;
            }

            #wrapper.toggled {
                padding-left: 0;
            }

            #sidebar-wrapper {
                width: 250px;
            }

            #wrapper.toggled #sidebar-wrapper {
                width: 0;
            }

            #page-content-wrapper {
                padding: 20px;
                position: relative;
            }

            #wrapper.toggled #page-content-wrapper {
                position: relative;
                margin-right: 0;
            }
        }

        #sidebar-wrapper li.active > a:after {
            border-right: 17px solid #f4f3ef;
            border-top: 17px solid transparent;
            border-bottom: 17px solid transparent;
            content: "";
            display: inline-block;
            position: absolute;
            right: -1px;
        }

        .sidebar-brand {
            border-bottom: 1px solid rgba(102, 97, 91, 0.3);
        }

        .sidebar-brand {
            padding: 18px 0px;
            margin: 0 20px;
        }

        .navbar .navbar-nav > li > a p {
            display: inline-block;
            margin: 0;
        }
        p {
            font-size: 16px;
            line-height: 1.4em;
        }

        .navbar-default {
            background-color: #f4f3ef;
            border:0px;
            border-bottom: 1px solid #DDDDDD;
        }

        btn-menu {
            border-radius: 3px;
            padding: 4px 12px;
            margin: 14px 5px 5px 20px;
            font-size: 14px;
            float: left;
        }
    </style>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div id="wrapper" class="wrapper-content">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    Open Help Desk
                </a>
            </li>
            <li>
                <a href="/dashboard.jsp">Dashboard</a>
            </li>
            <li class="active">
                <a href="#">Tickets</a>
            </li>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button class="btn-menu btn btn-success btn-toggle-menu" type="button">
                        <i class="fa fa-bars"></i>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${pageContext.request.contextPath}/UserControllerServlet?command=LOGOUT" >
                                <i class="ti-panel"></i>
                                <p>Logout</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">

                    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
                    <div class="container">
                        <section class="content">
                            <div class="row">


                                <div class="col-md-9">
                                    <div class="grid support-content">
                                        <div class="grid-body">
                                            <h2>Tickets</h2>
                                            <hr>
                                            <div class="btn-group">
                                                <span class="btn btn-default active"><%= request.getAttribute("openTickets") %> Open</span>
                                                <span class="btn btn-default"><%= request.getAttribute("closedTickets") %> Closed</span>
                                            </div>

                                            <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#newTicket">New Ticket</button>
                                            <div class="modal fade" id="newTicket" tabindex="-1" role="dialog" aria-labelledby="newTicket" aria-hidden="true">
                                                <div class="modal-wrapper">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <% if (CoreAPI.getInstance().getPermissionRegisterService().hasPermission(session1.getId(), "create_ticket")) { %>
                                                            <div class="modal-header bg-blue">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                <h4 class="modal-title"><i class="fa fa-pencil"></i> Create New Ticket</h4>
                                                            </div>
                                                            <% } %>
                                                            <form action="${pageContext.request.contextPath}/TicketController" method="post">
                                                                <div class="modal-body">
                                                                    <input name = "action" type="hidden" value="createTicket">
                                                                    <input name = "userId" type="hidden" value="<%= session1.getAttribute("userId") %>">
                                                                    <div class="form-group">
                                                                        <input name="title" type="text" class="form-control" placeholder="Subject">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <textarea name="description" class="form-control" placeholder="Please detail your issue or question" style="height: 120px;"></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Discard</button>
                                                                    <button type="submit" class="btn btn-primary pull-right"><i class="fa fa-pencil"></i> Create</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="padding"></div>
                                            <div class="row">

                                                <div class="col-md-12">
                                                    <ul class="list-group fa-padding">
                                                        <%
                                                            List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
                                                            if (tickets != null) {
                                                                for (Ticket ticket : tickets) {
                                                        %>
                                                        <a href = "TicketController?action=getMessages&ticketId=<%=ticket.getId()%>" >
                                                        <li class="list-group-item" data-toggle="modal" data-target="#ticket<%= ticket.getId() %>">
                                                            <div class="media">
                                                                <i class="fa fa-cog pull-left"></i>
                                                                <div class="media-body">
                                                                    <strong><%= ticket.getTitle() %></strong>
                                                                    <span class="label <%= ticket.isClosed() ? "label-success" : "label-danger" %>">
                        <%= ticket.isClosed() ? "CLOSED" : "OPEN" %>
                    </span>
                                                                    <span class="number pull-right"># <%= ticket.getId() %></span>
                                                                    <p class="info">Opened by <a href="#"><%= CoreAPI.getInstance().getUserService().getUser(ticket.getUserId()).getUserName() %></a>
                                                                        <%= ticket.getCreatedDate().toLocalDateTime().toString() %>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        </a>
                                                        <%
                                                                }
                                                            }else{

                                                                }
                                                        %>
                                                    </ul>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </section>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">




    $(function(){
        $(".btn-toggle-menu").click(function() {
            $("#wrapper").toggleClass("toggled");
        });
    })
</script>
</body>
</html>
