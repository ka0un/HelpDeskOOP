<%--
  Created by IntelliJ IDEA.
  User: Kasun
  Date: 10/15/2024
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="project.core.api.CoreAPI" %>
<%@ page import="project.features.tickets.model.dao.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="project.features.tickets.service.interfaces.TicketService" %>
<%@ page import="project.features.tickets.service.TicketServiceImpl" %>
<%
    HttpSession session1 = request.getSession(false);
    CoreAPI coreAPI = CoreAPI.getInstance();
    int userId = (int) session1.getAttribute("userId");

    if (!CoreAPI.getInstance().isUserLoggedIn(session1)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">


    <title>Open Help Desk | Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

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


    <style type="text/css">
        body {
            font-family: Roboto,sans-serif;
            font-size: 13px;
            line-height: 1.42857143;
            color: #767676;
            background-color: #edecec;
        }

        #messages-main {
            position: relative;
            margin: 0 auto;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
        }
        #messages-main:after, #messages-main:before {
            content: " ";
            display: table;
        }
        #messages-main .ms-menu {
            position: absolute;
            left: 0;
            top: 0;
            border-right: 1px solid #eee;
            padding-bottom: 50px;
            height: 100%;
            width: 240px;
            background: #fff;
        }
        @media (min-width:768px) {
            #messages-main .ms-body {
                padding-left: 240px;
            }
        }@media (max-width:767px) {
            #messages-main .ms-menu {
                height: calc(100% - 58px);
                display: none;
                z-index: 1;
                top: 58px;
            }
            #messages-main .ms-menu.toggled {
                display: block;
            }
            #messages-main .ms-body {
                overflow: hidden;
            }
        }
        #messages-main .ms-user {
            padding: 15px;
            background: #f8f8f8;
        }
        #messages-main .ms-user>div {
            overflow: hidden;
            padding: 3px 5px 0 15px;
            font-size: 11px;
        }
        #messages-main #ms-compose {
            position: fixed;
            bottom: 120px;
            z-index: 1;
            right: 30px;
            box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
        }
        #ms-menu-trigger {
            user-select: none;
            position: absolute;
            left: 0;
            top: 0;
            width: 50px;
            height: 100%;
            padding-right: 10px;
            padding-top: 19px;
        }
        #ms-menu-trigger i {
            font-size: 21px;
        }
        #ms-menu-trigger.toggled i:before {
            content: '\f2ea'
        }
        .fc-toolbar:before, .login-content:after {
            content: ""
        }
        .message-feed {
            padding: 20px;
        }
        #footer, .fc-toolbar .ui-button, .fileinput .thumbnail, .four-zero, .four-zero footer>a, .ie-warning, .login-content, .login-navigation, .pt-inner, .pt-inner .pti-footer>a {
            text-align: center;
        }
        .message-feed.right>.pull-right {
            margin-left: 15px;
        }
        .message-feed:not(.right) .mf-content {
            background: #03a9f4;
            color: #fff;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
        }
        .message-feed.right .mf-content {
            background: #eee;
        }
        .mf-content {
            padding: 12px 17px 13px;
            border-radius: 2px;
            display: inline-block;
            max-width: 80%
        }
        .mf-date {
            display: block;
            color: #B3B3B3;
            margin-top: 7px;
        }
        .mf-date>i {
            font-size: 14px;
            line-height: 100%;
            position: relative;
            top: 1px;
        }
        .msb-reply {
            box-shadow: 0 -20px 20px -5px #fff;
            position: relative;
            margin-top: 30px;
            border-top: 1px solid #eee;
            background: #f8f8f8;
        }
        .four-zero, .lc-block {
            box-shadow: 0 1px 11px rgba(0, 0, 0, .27);
        }
        .msb-reply textarea {
            width: 100%;
            font-size: 13px;
            border: 0;
            padding: 10px 15px;
            resize: none;
            height: 60px;
            background: 0 0;
        }
        .msb-reply button {
            position: absolute;
            top: 0;
            right: 0;
            border: 0;
            height: 100%;
            width: 60px;
            font-size: 25px;
            color: #2196f3;
            background: 0 0;
        }
        .msb-reply button:hover {
            background: #f2f2f2;
        }
        .img-avatar {
            height: 37px;
            border-radius: 2px;
            width: 37px;
        }
        .list-group.lg-alt .list-group-item {
            border: 0;
        }
        .p-15 {
            padding: 15px!important;
        }
        .btn:not(.btn-alt) {
            border: 0;
        }
        .action-header {
            position: relative;
            background: #f8f8f8;
            padding: 15px 13px 15px 17px;
        }
        .ah-actions {
            z-index: 3;
            float: right;
            margin-top: 7px;
            position: relative;
        }
        .actions {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .actions>li {
            display: inline-block;
        }

        .actions:not(.a-alt)>li>a>i {
            color: #939393;
        }
        .actions>li>a>i {
            font-size: 20px;
        }
        .actions>li>a {
            display: block;
            padding: 0 10px;
        }
        .ms-body{
            background:#fff;
        }
        #ms-menu-trigger {
            user-select: none;
            position: absolute;
            left: 0;
            top: 0;
            width: 50px;
            height: 100%;
            padding-right: 10px;
            padding-top: 19px;
            cursor:pointer;
        }
        #ms-menu-trigger, .message-feed.right {
            text-align: right;
        }
        #ms-menu-trigger, .toggle-switch {
            -webkit-user-select: none;
            -moz-user-select: none;
        }
    </style>

</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<%
    int ticketId = Integer.parseInt(request.getParameter("ticketId"));
    List<Message> messages = (List<Message>) request.getAttribute("messages");
    %>

<div id="wrapper" class="wrapper-content">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    Open Help Desk
                </a>
            </li>
            <li>
                <a href="../dashboard.jsp">Dashboard</a>
            </li>
            <% if (coreAPI.getPermissionRegisterService().hasPermission(userId, "view_tickets")) { %>
            <li class="active">
                <a href="TicketController?action=getAllTickets">Tickets</a>
            </li>
            <% } %>
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
                            <a href="/UserControllerServlet?command=LOGOUT" class="dropdown-toggle" data-toggle="dropdown">
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
                    <div class="container bootstrap snippets bootdey">

                        <div class="ms-body">
                            <div class="action-header clearfix">
                                <div class="visible-xs" id="ms-menu-trigger">
                                    <i class="fa fa-bars"></i>
                                </div>
                                <div class="pull-left hidden-xs">
<%--                                    <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt class="img-avatar m-r-10">--%>
                                    <div class="lv-avatar pull-left">
                                    </div>
                <% TicketService ticketService = new TicketServiceImpl();
                    String ticketTitle = ticketService.getTicketById(ticketId).getTitle();
                %>
                                        <%=ticketTitle%></span>
                                </div>
                                <ul class="ah-actions actions">
                                    <% if (CoreAPI.getInstance().getPermissionRegisterService().hasPermission(userId, "ticket_admin_controls")) { %>
                                    <li>
                                        <a href="TicketController?action=markAsResolved&ticketId=<%=ticketId%>" onclick="return confirm('Are you sure you want to mark this ticket as resolved?');">
                                            <i class="fa fa-check"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="TicketController?action=deleteTicket&ticketId=<%=ticketId%>" onclick="return confirm('Are you sure you want to delete this ticket?');">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </li>
                                    <%} %>
                                    <li>
                                        <a href ="TicketController?action=getMessages&ticketId=<%=ticketId%>">
                                            <i class="fa fa-rotate-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <%
                                for (Message message : messages) {
                                    boolean isSender = message.getSenderId() == userId;

                            %>
                            <div class="message-feed <%=(isSender) ? "media" : "right"%>">

                                <% if (isSender) { %>
                                <div class="pull-left"><% } else { %><div class="pull-right"><% } %>

                                    <img src="https://api.dicebear.com/9.x/initials/svg?seed=<%=CoreAPI.getInstance().getUserService().getUser(message.getSenderId()).getUserName()%>" alt class="img-avatar">

                                    </div>
                                    <div class="media-body">
                                        <div class="mf-content">
                                            <%=message.getMessage()%>
                                        </div>
                                        <small class="mf-date"><i class="fa fa-clock-o"></i> <%=message.getSentTime().toLocalDateTime().toString()%></small>
                                    </div>
                                </div>
                                <% } %>

                                <% if (!ticketService.getTicketById(ticketId).isClosed()) { %>
                                <div class="msb-reply">
                                    <form action="TicketController" method="post">
                                        <input type="hidden" name="action" value="addMessage">
                                        <input type="hidden" name="ticketId" value="<%= ticketId %>">
                                        <input type="hidden" name="senderId" value="<%= userId %>">
                                        <input type="hidden" name="isSenderAdmin" value="<%= CoreAPI.getInstance().getUserService().getUser(userId).getRole().equalsIgnoreCase("admin")%>">
                                        <textarea name="message" placeholder="What's on your mind..."></textarea>
                                        <button type="submit"><i class="fa fa-paper-plane-o"></i></button>
                                    </form>
                                </div>
                                <% } %>
                </div>
                <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
                <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
                <script type="text/javascript">
                    $(function(){
                        if ($('#ms-menu-trigger')[0]) {
                            $('body').on('click', '#ms-menu-trigger', function() {
                                $('.ms-menu').toggleClass('toggled');
                            });
                        }
                    });



                </script>

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
