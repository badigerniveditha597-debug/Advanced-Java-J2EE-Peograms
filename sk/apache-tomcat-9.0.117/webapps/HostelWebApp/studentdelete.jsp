<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Student - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card p-4 shadow">
        <h3 class="text-center mb-4">Delete Student</h3>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-<%= "success".equals(request.getAttribute("messageType")) ? "success" : "danger" %>"><%= message %></div>
        <% } %>
        <form action="DeleteStudentServlet" method="post" class="mb-4" onsubmit="return confirm('Are you sure?');">
            <div class="row justify-content-center g-3">
                <div class="col-md-4">
                    <input type="number" name="id" class="form-control" placeholder="Enter Student ID" required>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-danger w-100">Delete</button>
                </div>
            </div>
        </form>
        <hr>
        <% List<Student> studentList = (List<Student>) request.getAttribute("studentList"); %>
        <% if (studentList != null && !studentList.isEmpty()) { %>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Room</th><th>Action</th></tr>
            </thead>
            <tbody>
                <% for (Student s : studentList) { %>
                <tr>
                    <td><%= s.getId() %></td><td><%= s.getName() %></td><td><%= s.getEmail() %></td>
                    <td><%= s.getPhone() %></td><td><%= s.getRoomNumber() != null ? s.getRoomNumber() : "-" %></td>
                    <td>
                        <form action="DeleteStudentServlet" method="post" style="display:inline;" onsubmit="return confirm('Delete?');">
                            <input type="hidden" name="id" value="<%= s.getId() %>">
                            <button class="btn btn-sm btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %><p class="text-muted text-center">No students found.</p><% } %>
        <div class="text-center mt-3"><a href="index.jsp" class="btn btn-secondary">Back</a></div>
    </div>
</div>
</body>
</html>
