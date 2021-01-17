<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
    <title>Citizenship</title>
</head>
<body>
<h3>Citizenships list</h3>
<c:if test="${!empty citizenships}">
    <table>
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${citizenships}" var="citizenship">
            <tr>
                <td>${citizenship.id}</td>
                <td>${citizenship.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
