<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Person</title>
    <style>
       .top {
           margin-top: 20px;
          }
      </style>
</head>
<body>

<h1>
<font color="steelblue"> ${user.getFirstName()} ${user.getLastName()}</font>
</h1>
<table width="100%" align="center"
bgcolor="#e0e0e0" >
<tr >
<td width="50%"><img src="/static/${image}" width="250"></td>
<td align="right"  valign="top">
<p></p>
<p>Country:</p>
<p>Town:</p>
<p>Birthday:</p>
<p>Education:</p>
<p>Job:</p>
</td>
<td align="left" width="30%"  valign="top">
<p></p>
<p>${user.getCountry()}</p>
<p>${user.getTown()}</p>
<p>${user.getBirthday()}</p>
<p>${user.getEducation()}</p>
<p>${user.getJob()}</p>
</td>
</tr>
</table>



<table width="100%" align="center">
<tr>
    <table width="100%">
    <tr>
        <p>
        <c:if  test="${isFriend=='false'}">
           <form action="otheruser" method="Post" >
                   <input type="text" value="add" name = "action" hidden="true" />
                   <input type="text" value="${user.getID()}" name = "id" hidden="true" />
                   <input type="submit" class="button" value="Add to friends"/>
           </form>
        </c:if>
        <c:if test="${isFriend=='true'}">
           <form action="otheruser" method="Post" >
                   <input type="text" value="remove" name = "action" hidden="true" />
                   <input type="text" value="${user.getID()}" name = "id" hidden="true" />
                   <input type="submit" class="button" value="Remove from friends"/>
           </form>
        </c:if>
        </p>
    </tr>
    <tr>

         <form action="user/chat?id=${user.getID()}" method="Get" >
         <input type="submit" class="button" value="Write a message"/>
         </form>

    </tr>

    </table>

    <td align="right"  valign="top">

    </td>

</tr>
</table>




</body>
</html>