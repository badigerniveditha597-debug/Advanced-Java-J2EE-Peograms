<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Results - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card p-4 shadow">
        <% String reportTitle = (String) request.getAttribute("reportTitle"); %>
        <h3 class="text-center mb-4"><%= reportTitle != null ? reportTitle : "Report Results" %></h3>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-danger"><%= message %></div>
        <% } %>
        <% Integer resultCount = (Integer) request.getAttribute("resultCount"); %>
        <% if (resultCount != null) { %><p class="text-muted">Results found: <strong><%= resultCount %></strong></p><% } %>
        <% List<Student> results = (List<Student>) request.getAttribute("reportResults"); %>
        <% if (results != null && !results.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Room</th><th>Course</th><th>Year</th></tr>
                </thead>
                <tbody>
                    <% for (Student s : results) { %>
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
        <% } else { %><p class="text-muted text-center">No results found for the selected criteria.</p><% } %>
        <div class="text-center mt-3">
            <a href="ReportCriteriaServlet" class="btn btn-info text-white me-2">New Report</a>
            <a href="ReportServlet" class="btn btn-primary me-2">Dashboard</a>
            <a href="index.jsp" class="btn btn-secondary">Home</a>
        </div>
    </div>
</div>
</body>
</html>
