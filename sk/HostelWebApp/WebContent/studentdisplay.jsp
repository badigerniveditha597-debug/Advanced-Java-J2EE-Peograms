<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Students - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card p-4 shadow">
        <h3 class="text-center mb-4">All Hostel Students</h3>
        <% Integer total = (Integer) request.getAttribute("totalStudents"); %>
        <% if (total != null) { %><p class="text-muted">Total Students: <strong><%= total %></strong></p><% } %>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-danger"><%= message %></div>
        <% } %>
        <% List<Student> studentList = (List<Student>) request.getAttribute("studentList"); %>
        <% if (studentList != null && !studentList.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Room</th><th>Course</th><th>Year</th></tr>
                </thead>
                <tbody>
                    <% for (Student s : studentList) { %>
                    <tr>
                        <td><%= s.getId() %></td><td><%= s.getName() %></td><td><%= s.getEmail() %></td>
                        <td><%= s.getPhone() %></td>
                        <td><%= s.getRoomNumber() != null ? s.getRoomNumber() : "-" %></td>
                        <td><%= s.getCourse() != null ? s.getCourse() : "-" %></td>
                        <td><%= s.getYear() != null ? s.getYear() : "-" %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %><p class="text-muted text-center">No students registered yet.</p><% } %>
        <div class="text-center mt-3">
            <a href="AddStudentServlet" class="btn btn-success me-2">Add Student</a>
            <a href="index.jsp" class="btn btn-secondary">Back</a>
        </div>
    </div>
</div>
</body>
</html>
