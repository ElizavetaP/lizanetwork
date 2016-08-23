<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

</body>
</html>