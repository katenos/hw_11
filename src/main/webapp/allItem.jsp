<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.01.2017
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="menu.jsp"/>
    <script src="jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="jquery/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/allItem.js"></script>
    <script type="text/javascript" src="js/basicFunction.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.18.custom.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Проданные товары</title>
</head>
<body>
<table>
    <thead>
    <tr style="height: 35px">
        <th style="text-align: center;">№</th>
        <th style="text-align: center;">Наименование</th>
        <th style="text-align: center;">Описание</th>
        <th style="text-align: center;">Цена</th>
        <th style="text-align: center;">Кто продает</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
</body>
</html>