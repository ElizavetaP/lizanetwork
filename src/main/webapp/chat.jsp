<%@ include file="head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<html>
<head>
    <title>Devcolibri.com</title>
</head>
<body>

<table>
<p>
<table align="center" style="width: 50%; word-wrap: break-word;table-layout: fixed;">
<c:forEach var="messageWithAvatar" items="${messageWithAvatars}">
            <tr>
                <mytag:jstmessage messageWithAvatar="${messageWithAvatar}" />
            </tr>
</c:forEach>
<tr>
    <td/>
    <td colspan="2" >
    <br>
    <p><font color = "grey"> Your message<Br></font><p/>
    <form action="chat" method="Post">
            <input type="text" value="${id}" name = "id" hidden="true" />
           <textarea name="text" cols="40" rows="3"></textarea>
    <p><input type="submit" value="Send"/><p/>
    </td>
    </form>
</tr>
</table>
</p>

<table border="1" cellpadding="5" cellspacing="5">
<tr>
<c:if test="${currentPage != 1}">
        <td><a href="chat?id=${id}&page=${currentPage - 1}">Previous</a></td>
</c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="chat?id=${id}&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

<c:if test="${currentPage lt noOfPages}">
        <td><a href="chat?id=${id}&page=${currentPage + 1}">Next</a></td>
    </c:if>
   </tr>
   </table>

</body>
</html>