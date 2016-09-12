<%@ include file="head.jsp" %>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
var="en_button" />
<fmt:message bundle="${loc}" key="local.firstname"
var="firstname" />
<fmt:message bundle="${loc}" key="local.lastname"
var="lastname" />

</head>

<body>

<form action="/" method="post">
<input type="hidden" name="local" value="ru" /> <input type="submit"
value="${ru_button}" /><br />
</form>

<form action="/" method="post">
<input type="hidden" name="local" value="en" /> <input type="submit"
value="${en_button}" /><br />
</form>

<p>
<form action="/?search=${searchname}" method="Get">
                <input name="searchname" type="text" />
                <input type="submit" value="search"/>
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
                <c:choose>
                <c:when  test="${userID == person.getID()}">
                  <div id="logo">
                  <a href="user">
                  <img src="/static/${photos.get(person.getID())}" width="50"></a>
                  </div>
                </c:when>

                <c:otherwise>
                    <div id="logo">
                    <a href="otheruser?id=${person.getID()}">
                    <img src="/static/${photos.get(person.getID())}" width="50"></a>
                    </div>
                </c:otherwise>
                </c:choose>
                </td>
                <td>${person.getFirstName()}</td>
                <td>${person.getLastName()}</td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>