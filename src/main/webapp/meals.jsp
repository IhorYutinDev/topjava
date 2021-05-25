<%--
  Created by IntelliJ IDEA.
  User: www
  Date: 20.05.2021
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2>Meals</h2>

    <table border=1>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal">
            <tr style="color:${meal.excess ? 'red' : 'green'}">
                <td><c:out value="${meal.formattedDateTime}" /></td>
                <td><c:out value="${meal.description}" /></td>
                <td><c:out value="${meal.calories}" /></td>
                <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>


</html>
