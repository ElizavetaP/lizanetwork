<!DOCTYPE html>
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
<font color="steelblue"> ${user.get("FirstName")} ${user.get("LastName")}</font>
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
<p>${user.get("country")}</p>
<p>${user.get("town")}</p>
<p>${user.get("birthday")}</p>
<p>${user.get("education")}</p>
<p>${user.get("job")}</p>
</td>
</tr>

</body>
</html>