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

</body>
</html>