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
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.18.custom.min.css">
    <title>Выставленные на продажу товары</title>
</head>
<body>
<button id="addItem" onclick="addItem()" style="padding: 10px">Добавить товар</button>
<table>
    <thead>
    <tr style="height: 35px">
        <th style="text-align: center;">№</th>
        <th style="text-align: center;">Наименование</th>
        <th style="text-align: center;">Описание</th>
        <th style="text-align: center;">Стартовая цена</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
<div id="dialogWindow" >
    <table>
        <tr>
            <th>Имя</th>
            <th><input id="purchaseName"></th>
        </tr>
        <tr>
            <th>Описание</th>
            <th><input id="purchaseDescription"></th>
        </tr>
        <tr>
            <th>Стартовая цена</th>
            <th><input id="purchaseStartPrice" type="number"></th>
        </tr>
    </table>
</div>
</body>
</html>
