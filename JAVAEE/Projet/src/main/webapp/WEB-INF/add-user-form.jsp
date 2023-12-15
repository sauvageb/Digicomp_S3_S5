<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>

<div class="container">

    <form method="post" action="${pageContext.request.contextPath}/add-user">
        <label for="username">Username :</label>
        <input id="username" name="usernameParam" type="text">
        <label for="firstName">Firstname :</label>
        <input id="firstName" name="firstNameParam" type="text">
        <label for="lastName">LastName :</label>
        <input id="lastName" name="lastNameParam" type="text">
        <button type="submit">Add</button>
    </form>

</div>

</body>
</html>
