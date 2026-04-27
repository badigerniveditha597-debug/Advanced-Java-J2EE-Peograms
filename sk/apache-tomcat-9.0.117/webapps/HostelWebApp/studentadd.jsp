<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student - Hostel Management</title>
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
                <h3 class="text-center mb-4">➕ Add New Student</h3>

                <% String message = (String) request.getAttribute("message"); %>
                <% String messageType = (String) request.getAttribute("messageType"); %>
                <% if (message != null) { %>
                    <div class="alert alert-<%= "success".equals(messageType) ? "success" : "danger" %> alert-dismissible fade show">
                        <%= message %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                <% } %>

                <form action="AddStudentServlet" method="post">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Full Name *</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Email *</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Phone *</label>
                            <input type="text" name="phone" class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Room Number</label>
                            <input type="text" name="roomNumber" class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Course</label>
                            <input type="text" name="course" class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Year</label>
                            <select name="year" class="form-select">
                                <option value="">Select Year</option>
                                <option value="1st">1st Year</option>
                                <option value="2nd">2nd Year</option>
                                <option value="3rd">3rd Year</option>
                                <option value="4th">4th Year</option>
                            </select>
                        </div>
                        <div class="col-12">
                            <label class="form-label">Address</label>
                            <textarea name="address" class="form-control" rows="3"></textarea>
                        </div>
                        <div class="col-12 text-center mt-4">
                            <button type="submit" class="btn btn-success btn-lg px-5">Add Student</button>
                            <a href="index.jsp" class="btn btn-outline-secondary btn-lg px-5 ms-2">Back</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
