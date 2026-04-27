<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card p-4 shadow">
        <h3 class="text-center mb-4">Hostel Reports Dashboard</h3>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-danger"><%= message %></div>
        <% } %>
        <% Integer totalStudents = (Integer) request.getAttribute("totalStudents"); %>
        <div class="row g-4 mb-4">
            <div class="col-md-4">
                <div class="card bg-primary text-white text-center p-3">
                    <h5>Total Students</h5>
                    <h2><%= totalStudents != null ? totalStudents : 0 %></h2>
                </div>
            </div>
        </div>
        <div class="row g-4">
            <div class="col-md-6">
                <h5>Students by Course</h5>
                <% List<String[]> courseStats = (List<String[]>) request.getAttribute("courseStats"); %>
                <% if (courseStats != null && !courseStats.isEmpty()) { %>
                <table class="table table-bordered">
                    <thead class="table-secondary"><tr><th>Course</th><th>Count</th></tr></thead>
                    <tbody>
                        <% for (String[] row : courseStats) { %>
                        <tr><td><%= row[0] %></td><td><%= row[1] %></td></tr>
                        <% } %>
                    </tbody>
                </table>
                <% } else { %><p class="text-muted">No data available.</p><% } %>
            </div>
            <div class="col-md-6">
                <h5>Students by Year</h5>
                <% List<String[]> yearStats = (List<String[]>) request.getAttribute("yearStats"); %>
                <% if (yearStats != null && !yearStats.isEmpty()) { %>
                <table class="table table-bordered">
                    <thead class="table-secondary"><tr><th>Year</th><th>Count</th></tr></thead>
                    <tbody>
                        <% for (String[] row : yearStats) { %>
                        <tr><td><%= row[0] %></td><td><%= row[1] %></td></tr>
                        <% } %>
                    </tbody>
                </table>
                <% } else { %><p class="text-muted">No data available.</p><% } %>
            </div>
        </div>
        <div class="text-center mt-4">
            <a href="ReportCriteriaServlet" class="btn btn-info text-white me-2">Generate Custom Report</a>
            <a href="index.jsp" class="btn btn-secondary">Back</a>
        </div>
    </div>
</div>
</body>
</html>
