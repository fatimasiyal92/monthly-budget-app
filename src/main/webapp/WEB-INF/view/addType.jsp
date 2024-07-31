<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring MVC Form Handling</title>
    <link href="webjars/bootstrap/5.3.0/css/bootstrap.min.css"
          rel="stylesheet">
    <style type="text/css">
        @import url('https://fonts.googleapis.com/css?family=Open+Sans:400,700,800');
        @import url('https://fonts.googleapis.com/css?family=Lobster');
        html {
            font-size: 62.5%;
        }
        body {
            font-family: 'Open Sans', sans-serif;
            font-size: 1.6rem;
            font-weight: 400;
        }
        h1 {
            margin-bottom: 0.5em;
            font-size: 3.6rem;
        }
        .heading{
            color: #5bc0de;
        }
        .data{
            background-color:rgba(112,215,239,0.16);
        }
        p {
            margin-bottom: 0.5em;
            font-size: 1.6rem;
            line-height: 1.6;
        }
        .button {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 25px;
            border-radius: 4px;
        }
        .button-primary {
            position: relative;
            background-color: #c0ca33;
            color: #fff;
            font-size: 1.8rem;
            font-weight: 700;
            transition: color 0.3s ease-in;
            z-index: 1;
        }
        .button-primary:hover {
            color: #c0ca33;
            text-decoration: none;
        }
        .button-primary::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            top: 0;
            background-color: #fff;
            border-radius: 4px;
            opacity: 0;
            -webkit-transform: scaleX(0.8);
            -ms-transform: scaleX(0.8);
            transform: scaleX(0.8);
            transition: all 0.3s ease-in;
            z-index: -1;
        }
        .button-primary:hover::after {
            opacity: 1;
            -webkit-transform: scaleX(1);
            -ms-transform: scaleX(1);
            transform: scaleX(1);
        }
        .overlay::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            top: 0;
            background-color: rgba(0, 0, 0, .3);
        }
        .header-area {
            position: relative;
            height: 8vh;
            background: #5bc0de;
            background-attachment: fixed;
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        .banner {
            display: flex;
            align-items: center;
            position: relative;
            height: 100%;
            color: #fff;
            text-align: center;
            z-index: 1;
        }
        .banner h1 {
            font-weight: 800;
        }
        .banner p {
            font-weight: 700;
        }
        .navbar {
            position: absolute;
            left: 0;
            top: 0;
            padding: 0;
            width: 100%;
            transition: background 0.6s ease-in;
            z-index: 99999;
        }
        .navbar .navbar-brand {
            font-family: 'Lobster', cursive;
            font-size: 2.5rem;
        }
        .navbar .navbar-toggler {
            position: relative;
            height: 50px;
            width: 50px;
            border: none;
            cursor: pointer;
            outline: none;
        }
        .navbar .navbar-toggler .menu-icon-bar {
            position: absolute;
            left: 15px;
            right: 15px;
            height: 2px;
            background-color: #fff;
            opacity: 0;
            -webkit-transform: translateY(-1px);
            -ms-transform: translateY(-1px);
            transform: translateY(-1px);
            transition: all 0.3s ease-in;
        }
        .navbar .navbar-toggler .menu-icon-bar:first-child {
            opacity: 1;
            -webkit-transform: translateY(-1px) rotate(45deg);
            -ms-sform: translateY(-1px) rotate(45deg);
            transform: translateY(-1px) rotate(45deg);
        }
        .navbar .navbar-toggler .menu-icon-bar:last-child {
            opacity: 1;
            -webkit-transform: translateY(-1px) rotate(135deg);
            -ms-sform: translateY(-1px) rotate(135deg);
            transform: translateY(-1px) rotate(135deg);
        }
        .navbar .navbar-toggler.collapsed .menu-icon-bar {
            opacity: 1;
        }
        .navbar .navbar-toggler.collapsed .menu-icon-bar:first-child {
            -webkit-transform: translateY(-7px) rotate(0);
            -ms-sform: translateY(-7px) rotate(0);
            transform: translateY(-7px) rotate(0);
        }
        .navbar .navbar-toggler.collapsed .menu-icon-bar:last-child {
            -webkit-transform: translateY(5px) rotate(0);
            -ms-sform: translateY(5px) rotate(0);
            transform: translateY(5px) rotate(0);
        }
        .navbar-dark .navbar-nav .nav-link {
            position: relative;
            color: #fff;
            font-size: 1.6rem;
        }
        .navbar-dark .navbar-nav .nav-link:focus, .navbar-dark .navbar-nav .nav-link:hover {
            color: #fff;
        }
        .navbar .dropdown-menu {
            padding: 0;
            background-color: rgba(0, 0, 0, .9);
        }
        .navbar .dropdown-menu .dropdown-item {
            position: relative;
            padding: 10px 20px;
            color: #fff;
            font-size: 1.4rem;
            border-bottom: 1px solid rgba(255, 255, 255, .1);
            transition: color 0.2s ease-in;
        }
        .navbar .dropdown-menu .dropdown-item:last-child {
            border-bottom: none;
        }
        .navbar .dropdown-menu .dropdown-item:hover {
            background: transparent;
            color: #c0ca33;
        }
        .navbar .dropdown-menu .dropdown-item::before {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            top: 0;
            width: 5px;
            background-color: #c0ca33;
            opacity: 0;
            transition: opacity 0.2s ease-in;
        }
        .navbar .dropdown-menu .dropdown-item:hover::before {
            opacity: 1;
        }
        .navbar.fixed-top {
            position: fixed;
            -webkit-animation: navbar-animation 0.6s;
            animation: navbar-animation 0.6s;
            background-color: rgba(0, 0, 0, .9);
        }
        .navbar.fixed-top.navbar-dark .navbar-nav .nav-link.active {
            color: #c0ca33;
        }
        .navbar.fixed-top.navbar-dark .navbar-nav .nav-link::after {
            background-color: #c0ca33;
        }
        .content {
            padding: 120px 0;
        }
        @media screen and (max-width: 768px) {
            .navbar-brand {
                margin-left: 20px;
            }
            .navbar-nav {
                padding: 0 20px;
                background-color: rgba(0, 0, 0, .9);
            }
            .navbar.fixed-top .navbar-nav {
                background: transparent;
            }
        }
        @media screen and (min-width: 767px) {
            .banner {
                padding: 0 150px;
            }
            .banner h1 {
                font-size: 5rem;
            }
            .banner p {
                font-size: 2rem;
            }
            .navbar-dark .navbar-nav .nav-link {
                padding: 23px 15px;
            }
            .navbar-dark .navbar-nav .nav-link::after {
                content: '';
                position: absolute;
                bottom: 15px;
                left: 30%;
                right: 30%;
                height: 1px;
                background-color: #fff;
                -webkit-transform: scaleX(0);
                -ms-transform: scaleX(0);
                transform: scaleX(0);
                transition: transform 0.1s ease-in;
            }
            .navbar-dark .navbar-nav .nav-link:hover::after {
                -webkit-transform: scaleX(1);
                -ms-transform: scaleX(1);
                transform: scaleX(1);
            }
            .dropdown-menu {
                min-width: 200px;
                -webkit-animation: dropdown-animation 0.3s;
                animation: dropdown-animation 0.3s;
                -webkit-transform-origin: top;
                -ms-transform-origin: top;
                transform-origin: top;
            }
        }
        @-webkit-keyframes navbar-animation {
            0% {
                opacity: 0;
                -webkit-transform: translateY(-100%);
                -ms-transform: translateY(-100%);
                transform: translateY(-100%);
            }
            100% {
                opacity: 1;
                -webkit-transform: translateY(0);
                -ms-transform: translateY(0);
                transform: translateY(0);
            }
        }
        @keyframes navbar-animation {
            0% {
                opacity: 0;
                -webkit-transform: translateY(-100%);
                -ms-transform: translateY(-100%);
                transform: translateY(-100%);
            }
            100% {
                opacity: 1;
                -webkit-transform: translateY(0);
                -ms-transform: translateY(0);
                transform: translateY(0);
            }
        }
        @-webkit-keyframes dropdown-animation {
            0% {
                -webkit-transform: scaleY(0);
                -ms-transform: scaleY(0);
                transform: scaleY(0);
            }
            75% {
                -webkit-transform: scaleY(1.1);
                -ms-transform: scaleY(1.1);
                transform: scaleY(1.1);
            }
            100% {
                -webkit-transform: scaleY(1);
                -ms-transform: scaleY(1);
                transform: scaleY(1);
            }
        }
        @keyframes dropdown-animation {
            0% {
                -webkit-transform: scaleY(0);
                -ms-transform: scaleY(0);
                transform: scaleY(0);
            }
            75% {
                -webkit-transform: scaleY(1.1);
                -ms-transform: scaleY(1.1);
                transform: scaleY(1.1);
            }
            100% {
                -webkit-transform: scaleY(1);
                -ms-transform: scaleY(1);
                transform: scaleY(1);
            }
        }

    </style>
    <script>
        jQuery(function($) {
            $(window).on('scroll', function() {
                if ($(this).scrollTop() >= 200) {
                    $('.navbar').addClass('fixed-top');
                } else if ($(this).scrollTop() == 0) {
                    $('.navbar').removeClass('fixed-top');
                }
            });

            function adjustNav() {
                var winWidth = $(window).width(),
                    dropdown = $('.dropdown'),
                    dropdownMenu = $('.dropdown-menu');

                if (winWidth >= 768) {
                    dropdown.on('mouseenter', function() {
                        $(this).addClass('show')
                            .children(dropdownMenu).addClass('show');
                    });

                    dropdown.on('mouseleave', function() {
                        $(this).removeClass('show')
                            .children(dropdownMenu).removeClass('show');
                    });
                } else {
                    dropdown.off('mouseenter mouseleave');
                }
            }

            $(window).on('resize', adjustNav);

            adjustNav();
        });
    </script>
</head>
<body>
<center>
    <header class="header-area overlay">
        <nav class="navbar navbar-expand-md navbar-dark">
            <div class="container">
                <a href="#" class="navbar-brand">BUDGET PLANNER</a>

                <button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#main-nav">
                    <span class="menu-icon-bar"></span>
                    <span class="menu-icon-bar"></span>
                    <span class="menu-icon-bar"></span>
                </button>

                <div id="main-nav" class="collapse navbar-collapse">
                    <ul class="navbar-nav ml-auto">
                        <li><a href="addCategory.html" class="nav-item nav-link active">Add Category</a></li>
                        <li><a href="addTransaction.html" class="nav-item nav-link">Add Transaction</a></li>
                        <li><a href="addTransactionDetail.html" class="nav-item nav-link">Add Transaction Detail</a></li>
                        <li><a href="addType.html" class="nav-item nav-link">Add Type</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>


    <div class="container">
        <h1 class="heading">Create New Type</h1>
        <form:form method="POST"  class="form-group" action="${pageContext.request.contextPath}/saveType.html">
        <table class="table table-striped tab-content" style="border: dot-dash">
            <tr>
                <td><form:label path="id">ID:</form:label></td>
                <td><form:input path="id" class="form-control"  value="${type.id}" readonly="true"/></td>
            </tr>
            <tr>
                <td><form:label path="type">Type:</form:label></td>
                <td><form:input path="type" class="form-control" value="${type.type}"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input class="btn btn-lg" style="background-color: #5bc0de" type="submit" value="SAVE"/></td>
            </tr>
        </table>
        </form:form>
        <br/>
        <c:if test="${!empty types}">
            <table class="table table-striped tab-content" style="border: dot-dash" align="center" border="1">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="table-header title">ID</th>
                        <th scope="col" class="table-header title">Type</th>
                        <th scope="col" class="table-header title">Options</th>
                    </tr>
                </thead>
                <%!
                    int i = 1;
                    String str = "";
                %>
                <c:forEach items="${types}" var="type" varStatus="loop">
                    <%
                        if(i % 2 == 0) {
                            str = "table-primary details-table bg-primary";
                        }
                        else{
                            str = "table-secondary details-table bg-success";
                        }
                    %>
                    <tr>
                        <%--<td>${loop.index + 1}</td>--%>
                        <td scope="row" class="${str}"><c:out value="${type.id}"/></td>
                        <td scope="row" class="${str}"><c:out value="${type.type}"/></td>
                        <td scope="row" class="${str}" align="center"><a href="${pageContext.request.contextPath}/editType.html?typeId=${type.id}">Edit</a> | <a
                                href="${pageContext.request.contextPath}/deleteType.html?typeId=${type.id}">Delete</a></td>
                    </tr>
                    <%
                        i++;
                    %>
                </c:forEach>
            </table>
       <%-- <ul class="pagination">
            <c:forEach begin="0" end="${totalTypePages - 1}" var="i">
                <li class="page-item ${currentTypePage == i ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}">${i + 1}</a>
                </li>
            </c:forEach>
        </ul>--%>
        </c:if>
        <script src="/js/bootstrap.min.js"></script>
        <script src="webjars/bootstrap/5.3.0/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
</center>
</div>
</body>
</html>
