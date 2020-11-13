<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>One note</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<h1 align="center">Note with id: ${thenote.id}</h1>
<form:form action="/api/notes/${thenote.id}" method="post" modelAttribute="note">
    <form:label path="name">Note title: </form:label>
    <form:input type="text" path="name" value="${thenote.name}"/>
    <br>
    <form:label path="text">Note content: </form:label>
    <form:textarea id="mytextarea" rows="10" cols="20" path="text"/>
    <br>
    <input type="submit" value="Update">
</form:form>
<button type="button" onclick="location.href='/api/notes'">Back</button>
<br/>

<script type="text/javascript">
    document.getElementById("mytextarea").defaultValue = "${thenote.text}";
</script>
</body>
</html>