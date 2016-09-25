<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
var="en_button" />
<fmt:message bundle="${loc}" key="local.logout"
var="logout" />

<table width="100%" align="center"
bgcolor="#B3FFA2" >
<tr >
    <td width="50" bgcolor="#FFFFFF">
        <div id="header">
            <!-- логотип -->
            <a href="/user" id="logo"><img src="/static/arbuz.jpg" width="40"></a>
        </div>
    </td>
    <td align="center">
        <p><font size="+2" color=#DC143C>Juicy network</font></p>
    </td>
    <td align="right"  width="10%">
        <form action="language" method="post">
        <input type="hidden" name="local" value="ru" /> <input type="submit"
        value="${ru_button}" />
        </form>

        <form action="language" method="post">
        <input type="hidden" name="local" value="en" /> <input type="submit"
        value="${en_button}" /><br />
        </form>
    </td>
<td align="right" width="10%">

    <!-- дополнительные ссылки сверху -->
    <div id="links">
        <a href="authorization?logout=true">${logout}</a>
    </div>
</td>
</tr>
</table>
