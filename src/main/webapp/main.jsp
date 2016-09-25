<%@ include file="head.jsp" %>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<head>

<fmt:message bundle="${loc}" key="local.firstname"
var="firstname" />
<fmt:message bundle="${loc}" key="local.lastname"
var="lastname" />
<fmt:message bundle="${loc}" key="local.search"
var="search" />

</head>

<body>

<p>
<form action="/?search=${searchname}" method="Get">
                <input name="searchname" type="text" />
                <input type="submit" value="${search}" />
            </form>
<p/>
<table width="300px" align="left" bgcolor="#f1f1f1" border="1" cellpadding="5"
style="border-collapse: collapse">
   <style type="text/css">
       TH {
        background: #b0e0e6; /* Цвет фона */
       }
      </style>  <th/>
                <th>${lastname}</th>
                <th>${firstname}</th>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td>
                    <div id="logo">
                    <a href="otheruser?id=${person.getID()}">
                    <img src="/static/${photos.get(person.getID())}" width="50"></a>
                    </div>
                </td>
                <td>${person.getFirstName()}</td>
                <td>${person.getLastName()}</td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>