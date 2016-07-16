<!DOCTYPE html>
<%@ include file="headforauth.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <style>
           .errorblock {
             color: red; /* Красный цвет выделения */
           }
           .answerblock {
                           color: blue; /* Красный цвет выделения */
                         }
     </style>
</head>
<body>
    Create account:
    <form action="register" method="Post">
        <input name="newfirstname" type="text" value="firstname" title="firstname" required/>
        <input name="newlastname" type="text" value="lastname" title="lastname" required/>
        <input name="newemail" type="email" value="email" title="email" required/>
        <input name="newpassword" type="password" value="" title="password" required/>

        <input type="submit"/>
        </br>
        <span class="errorblock">${errormessage}</span>
        <span class="answerblock">${answermessage}</span>
    </form>
</body>
</html>