<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: www
  Date: 24.05.2021
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h2>Edit meal</h2>

<form method="POST" action='MealServlet' name="frmAddMeal">

    DateTime : <input type="text" name="date"
                      value="<c:out value="${meal.formattedDateTime}" />"/> <br/>

    Description : <input type="text" readonly="readonly" name="description"
                         value="<c:out value="${meal.description}" />"/> <br/>

    Calories : <input type="text" readonly="readonly" name="calories"
                      value="<c:out value="${meal.calories}" />"/> <br/>

    <input type="submit" value="Save" />
    <button onclick="window.history.back()">Cancel</button>
    <a href="/meals">Cancel</a>
</form>

</body>
</html>
