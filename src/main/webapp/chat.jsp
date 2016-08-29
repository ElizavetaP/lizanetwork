<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Devcolibri.com</title>
</head>
<body>

<table>

<p>
<table align="center" style="width: 50%; word-wrap: break-word;table-layout: fixed;">
<c:forEach var="message" items="${messages}">
            <tr>
                <td width="70" valign="top">
                <div id="logo">
                <a href="otheruser?id=${message.getSenderID()}">
                <img src="/static/${photos.get(message.getSenderID())}" width="50"></a>
                </div>
                </td>
                <td align="left">
                ${message.getText()}</td>
                <td width=15%><font size="-1" color="grey">${message.getDate()}</font></td>
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

<mytag:bodyjspset num="${beanmessages.getSize()}">

${beanmessages.getElement().getText()}

</mytag:bodyjspset>

</body>
</html>