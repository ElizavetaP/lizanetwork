<%@ include file="head.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Information</title>
</head>
<body>
<form action="information" method="Post">
<table width="100%" cellpadding="4">
<tr>
     <td align="right" width="40%">First name</td>
     <td><input name="newfirstname" type="text" value="${user.getFirstName()}" maxlength="50" required/></td>
</tr>
<tr>
     <td align="right" >Last name</td>
     <td><input name="newlastname" type="text" value="${user.getLastName()}" maxlength="50" required/></td>
</tr>
<tr>
     <td align="right" >Sex</td>
     <td><input type="radio" name="sex" value="female"> Female<Br>
         <input type="radio" name="sex" value="male"> Male</td>
</tr>
<tr>
     <td align="right" >Country</td>
     <td><input name="newcountry" type="text" value="${user.getCountry()}" maxlength="50" /></td>
</tr>
<tr>
     <td align="right" >Town</td>
     <td><input name="newtown" type="text" value="${user.getTown()}" maxlength="50" /></td>
</tr>
<tr>
     <td align="right" >Birthday</td>
     <td>
     <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
     <script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
     <script>
     webshims.setOptions('forms-ext', {types: 'date'});
     webshims.polyfill('forms forms-ext');
     $.webshims.formcfg = {
     en: {
         dFormat: '-',
         dateSigns: '-',
         patterns: {
             d: "yy-mm-dd"
         }
     }
     };
     </script>
     <input name="newbirthday" type="date" value="${user.getBirthday()}"/></td>
</tr>
<tr>
     <td align="right" >Education</td>
     <td><input name="neweducation" type="text" value="${user.getEducation()}" maxlength="50" /></td>
</tr>
<tr>
     <td align="right" >Job</td>
     <td><input name="newjob" type="text" value="${user.getJob()}" maxlength="50" /></td>
</tr>
<tr>
     <td align="right" >email</td>
     <td><input name="newemail" type="email" value="${user.getEmail()}" maxlength="50"  required/></td>
</tr>
<tr>
     <td></td>
     <td><input type="submit" value="Edit"></td>
    </tr>
</table>
</body>
</html>