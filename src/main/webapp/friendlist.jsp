<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="head.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Friendship</title>

</head>
<body>
<c:forEach var="friend" items="${friends}">
            <tr>
                <td>
                <div id="logo">
                <a href="otheruser?id=${friend.getID()}">
                <img src="/static/${photos.get(friend.getID())}" width="50"></a>

                </div>
                </td>
                <td>${friend.getFirstName()} ${friend.getLastName()}</td>
                <td>Remove from friends</td>

            </tr>
        </c:forEach>

</body>
</html>