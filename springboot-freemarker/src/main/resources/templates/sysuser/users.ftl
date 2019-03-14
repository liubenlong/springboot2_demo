<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <meta content="text/html;charset=utf-8"></meta>
        <title>Hello World!</title>
        <#--<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>-->
        <script src="webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>
<body>
<div class="container">
        <table class="table">
            <caption>${sysUser}</caption>
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>aehyok</td>
                    <td>leo</td>
                    <td>@aehyok</td>
                </tr>
                <tr>
                    <td>lynn</td>
                    <td>thl</td>
                    <td>@lynn</td>
                </tr>
                <#list userList as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                </tr>
                   </#list>

            </tbody>
        </table>
    </div>
</body>
</html>