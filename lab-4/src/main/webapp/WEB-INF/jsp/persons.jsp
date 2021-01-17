<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Persons list</h3>
<c:if test="${!empty persons}">
    <table>
        <thead>
        <tr>
            <td>Mobile Phone</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Passport</td>
            <td>Citizenship</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td>${person.mobile}</td>
                <td>${person.name}</td>
                <td>${person.surname}</td>
                <td>${person.passport}</td>
                <td>${person.citizenship.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
