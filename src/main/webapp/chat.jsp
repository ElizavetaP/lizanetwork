<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Devcolibri.com</title>
</head>
<body>
<table>



</table>
<c:forEach var="message" items="${messages}">
            <tr>
                <td>
                <div id="logo">
                <a href="otheruser?id=${message.getSenderID()}">
                <img src="/static/${photos.get(message.getSenderID())}" width="50"></a>
                </div>
                </td>
                <td>${message.getText()}</td>
                <td>${message.getDate()}</td>
            </tr>
</c:forEach>
<tr>
<p>Your message<Br>
<form action="chat?id=${id}" method="Post">
       <textarea name="text" cols="40" rows="3"></textarea></p>
<p><input type="submit" value="Send"/></p>
</form>
</table>


</body>
</html>