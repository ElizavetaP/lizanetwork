<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="head.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Friendship</title>

</head>
<body>
<h2> <font color="steelblue">
Friend list</font></h2>
<p>
<table width="50%" cellpadding="4">
<c:forEach var="friend" items="${friends}">
            <tr>
                <td>
                <div id="logo">
                <a href="otheruser?id=${friend.getID()}">
                <img src="/static/${photos.get(friend.getID())}" width="50"></a>

                </div>
                </td>
                <td align="left">${friend.getFirstName()} ${friend.getLastName()}</td>
                <td>
                <form action="friendlist" method="Post" >
                    <input type="text" value="${friend.getID()}" name = "id" hidden="true" />
                    <input type="submit" class="button" value="Remove from friends"/>
                </form>
                </td>

            </tr>
        </c:forEach>
</table>
</p>
</body>
</html>