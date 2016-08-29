<!DOCTYPE html>
<%@ include file="headforauth.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <style>
       .errorblock {
         color: red; /* Красный цвет выделения */
       }
      </style>
</head>
<body>
<p>
    Log in:
</p>
    <form action="authorization" method="Post">
        <input name="logemail" type="email" value="email" title="email" required/>
        <input name="logpassword" type="password" value="" title="password" required/>
        <input type="submit"/>
        </br>
        <span class="errorblock">${errormessage}</span>
    </form>
</body>
</html>