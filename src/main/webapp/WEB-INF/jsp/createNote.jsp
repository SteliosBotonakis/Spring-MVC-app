<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>One note</title>
</head>
<body>
<h1 align="center">Create new note</h1>
<form:form action="/api/notes" method="post" modelAttribute="note">
    <form:label path="name">Note title: </form:label>
    <form:input type="text" path="name"/>
    <br>
    <form:label path="text">Note content: </form:label>
    <form:textarea rows="10" cols="20" path="text"/>
    <br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>