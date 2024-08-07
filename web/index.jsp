<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Registered Students</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Iterate over student list and display --%>
                    <%@ page import="java.util.List" %>
                    <%@ page import="models.Student" %>
                    <%@ page import="dao.StudentDAO" %>
                    <%
                        StudentDAO studentDAO = new StudentDAO();
                        List<Student> students = studentDAO.getAllStudents();
                        for (Student student : students) {
                    %>
                    <tr>
                        <td><%= student.getId()%></td>
                        <td><%= student.getFirstName()%></td>
                        <td><%= student.getLastName()%></td>
                        <td><%= student.getEmail()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <a href="register.jsp" class="btn btn-primary">Register Student</a>
        </div>
    </body>
</html>
