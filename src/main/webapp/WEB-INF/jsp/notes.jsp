<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contact List - Spring Boot Web Application Example</title>
</head>
<body>
<h1 align="center">My Notes</h1>
<br/>
<button type="button" onclick="location.href='/api/createNote'">Create new note</button>
<br>
<table border="1" cellpadding="10">
    <tr>
        <th>id</th><th>Note</th><th>Content</th><th>View</th><th>Delete</th>
    </tr>
    <c:forEach var="note" items="${noteList}">
        <tr id="row_${note.id}">
            <td>${note.id}</td>
            <td>${note.name}</td>
            <td>${note.text}</td>
            <td><button class="btn" type="submit" onclick="redirect(${note.id})">View</button></td>
            <td><button class="btn" type="submit" onclick="deleteNote(${note.id})">Delete</button></td>
        </tr>
    </c:forEach>
</table>

<script type="text/javascript">
    function redirect(id)
    {
        let url = "http://localhost:8080/api/notes/" + id;
        window.location.href= url;
    }
</script>


<script type="text/javascript">
    function deleteNote(id) {
        $.ajax({
            url: '/api/notes/' + id,
            method: 'DELETE',
            success: function () {
                $("#row_" + id).remove();
            },
            error: function (error) {
                alert(error);
            }
        })
    }

</script>
</body>
</html>