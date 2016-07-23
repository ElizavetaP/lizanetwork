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
<td width="50%"><img src="/static/${image}" width="250"><br />
<font size="-2">Select a new avatar to upload:</font> <br />
    <form action="user" method="Post" enctype="multipart/form-data">
    <input type="file" name="img" accept="image/*" /><br />
    <input type="submit" value="Upload" />
    </form>
    </td>
<td align="right"  valign="top">
<p></p>
<p>Country:</p>
<p>Town:</p>
<p>Birthday:</p>
<p>Education:</p>
<p>Job:</p>
<div id="links">
<a href="information"><font color="steelblue" size="-1">
Edit
</font></a>
</div>
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
<p>
<form action="friendlist" method="Get" >
           <input type="submit" value="Friend List"/>
               </form>
</p>
</body>
</html>