<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body { background: #f0f2f5; }
        .form-card { border: none; border-radius: 15px; box-shadow: 0 5px 20px rgba(0,0,0,0.1); }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card form-card p-4">
                <h3 class="text-center mb-4">✏️ Update Student</h3>

                <% String message = (String) request.getAttribute("message"); %>
                <% String messageType = (String) request.getAttribute("messageType"); %>
                <% if (message != null) { %>
                    <div class="alert alert-<%= "success".equals(messageType) ? "success" : "danger" %> alert-dismissible fade show">
                        <%= message %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                <% } %>

                <% Student student = (Student) request.getAttribute("student"); %>

                <% if (student == null) { %>
                <!-- Search form to find student by ID -->
                <form action="UpdateStudentServlet" method="get" class="text-center">
                    <div class="row justify-content-center g-3">
                        <div class="col-md-6">
                            <label class="form-label">Enter Student ID</label>
                            <input type="number" name="id" class="form-control" required>
                        </div>
                        <div class="col-12 mt-3">
                            <button type="submit" class="btn btn-primary btn-lg px-5">Find Student</button>
                            <a href="index.jsp" class="btn btn-outline-secondary btn-lg px-5 ms-2">Back</a>
                        </div>
                    </div>
                </form>
                <% } else { %>
                <!-- Update form with student data -->
                <form action="UpdateStudentServlet" method="post">
                    <input type="hidden" name="id" value="<%= student.getId() %>">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Student ID</label>
                            <input type="text" class="form-control" value="<%= student.getId() %>" disabled>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Full Name *</label>
                            <input type="text" name="name" class="form-control" value="<%= student.getName() != null ? student.getName() : "" %>" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Email *</label>
                            <input type="email" name="email" class="form-control" value="<%= student.getEmail() != null ? student.getEmail() : "" %>" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Phone *</label>
                            <input type="text" name="phone" class="form-control" value="<%= student.getPhone() != null ? student.getPhone() : "" %>" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Room Number</label>
                            <input type="text" name="roomNumber" class="form-control" value="<%= student.getRoomNumber() != null ? student.getRoomNumber() : "" %>">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Course</label>
                            <input type="text" name="course" class="form-control" value="<%= student.getCourse() != null ? student.getCourse() : "" %>">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Year</label>
                            <select name="year" class="form-select">
                                <option value="">Select Year</option>
                                <option value="1st" <%= "1st".equals(student.getYear()) ? "selected" : "" %>>1st Year</option>
                                <option value="2nd" <%= "2nd".equals(student.getYear()) ? "selected" : "" %>>2nd Year</option>
                                <option value="3rd" <%= "3rd".equals(student.getYear()) ? "selected" : "" %>>3rd Year</option>
                                <option value="4th" <%= "4th".equals(student.getYear()) ? "selected" : "" %>>4th Year</option>
                            </select>
                        </div>
                        <div class="col-12">
                            <label class="form-label">Address</label>
                            <textarea name="address" class="form-control" rows="3"><%= student.getAddress() != null ? student.getAddress() : "" %></textarea>
                        </div>
                        <div class="col-12 text-center mt-4">
                            <button type="submit" class="btn btn-warning btn-lg px-5">Update Student</button>
                            <a href="index.jsp" class="btn btn-outline-secondary btn-lg px-5 ms-2">Back</a>
                        </div>
                    </div>
                </form>
                <% } %>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
