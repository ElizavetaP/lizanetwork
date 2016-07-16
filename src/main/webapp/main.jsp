<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Devcolibri.com</title>
</head>
<body>
<p>
<form action="/?search=${searchname}" method="Get">
                <input name="searchname" type="text" />
                <input type="submit" value="search"/>
            </form>
<p/>
<table>
    <style type="text/css">
       TABLE {
        width: 300px; /* Ширина таблицы */
        border-collapse: collapse; /* Убираем двойные линии между ячейками */
        background-color: #f1f1f1;
       }
       TD, TH {
        padding: 3px; /* Поля вокруг содержимого таблицы */
        border: 1px solid black; /* Параметры рамки */
       }
       TH {
        background: #b0e0e6; /* Цвет фона */
       }
      </style>
                <th/>
                <th>Firstname</th>
                <th>Lastname</th>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td><img src="/static/${photos.get(person.getID())}" width="50">
                <td>${person.getFirstName()}</td>
                <td>${person.getLastName()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>