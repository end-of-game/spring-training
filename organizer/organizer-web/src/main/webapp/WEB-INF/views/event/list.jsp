<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Events</title>
</head>
<body>
    <h1>My Events</h1>
    
    <table>
        <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.description}</td>
            <td>${event.beginDateTime}</td>
            <td>${event.endDateTime}</td>
            <td><form action="${event.links['delete']}" method="post"><input type="submit" value="delete"/></form></td>
        </c:forEach>
    </table>
    
    <h2>Create a new event</h2>
    
    <spring:url value="/events" var="createEventUrl"></spring:url>
    <form:form action="${createEventUrl}" method="post" modelAttribute="event">
        <p><spring:bind path="description">Description: <form:input path="description"/><form:errors path="description"/></spring:bind></p>
        <p>Begin: <form:input path="beginDateTime"/><form:errors path="beginDateTime"/></p>
        <p>End: <form:input path="endDateTime"/><form:errors path="endDateTime"/></p>
        <p><input type="submit" value="OK">
    </form:form>
</body>
</html>