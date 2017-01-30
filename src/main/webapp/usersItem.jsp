<%-- 
    Document   : welcome
    Created on : 29.11.2016, 6:58:58
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="menu.jsp"/>
    <script src="jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="jquery/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/usersItem.js"></script>
    <script type="text/javascript" src="js/basicFunction.js"></script>
    <title>Выставленные на продажу товары</title>
</head>
<body>
<table>
    <thead>
    <tr style="height: 35px">
        <th style="text-align: center;">№</th>
        <th style="text-align: center;">Наименование</th>
        <th style="text-align: center;">Описание</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
</body>
</html>
