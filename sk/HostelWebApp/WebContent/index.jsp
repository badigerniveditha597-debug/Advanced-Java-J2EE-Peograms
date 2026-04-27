<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hostel Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .card { border: none; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.2); }
        .card:hover { transform: translateY(-5px); transition: 0.3s; }
        .btn-custom { border-radius: 10px; padding: 15px 25px; font-size: 1.1rem; font-weight: 600; }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="text-center text-white mb-5">
        <h1 class="display-4 fw-bold">🏠 Hostel Management System</h1>
        <p class="lead">Manage students, rooms, and generate reports</p>
    </div>

    <div class="row g-4 justify-content-center">
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">➕</h1>
                    <h5 class="card-title">Add Student</h5>
                    <p class="card-text text-muted">Register a new student in the hostel</p>
                    <a href="AddStudentServlet" class="btn btn-success btn-custom w-100">Add Student</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">📋</h1>
                    <h5 class="card-title">Display Students</h5>
                    <p class="card-text text-muted">View all registered students</p>
                    <a href="DisplayStudentsServlet" class="btn btn-primary btn-custom w-100">View All</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">✏️</h1>
                    <h5 class="card-title">Update Student</h5>
                    <p class="card-text text-muted">Modify existing student details</p>
                    <a href="UpdateStudentServlet" class="btn btn-warning btn-custom w-100">Update</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">🗑️</h1>
                    <h5 class="card-title">Delete Student</h5>
                    <p class="card-text text-muted">Remove a student from records</p>
                    <a href="DeleteStudentServlet" class="btn btn-danger btn-custom w-100">Delete</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">📊</h1>
                    <h5 class="card-title">Reports</h5>
                    <p class="card-text text-muted">View hostel statistics and reports</p>
                    <a href="ReportServlet" class="btn btn-info btn-custom w-100 text-white">Reports</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-4">
                <div class="card-body">
                    <h1 class="mb-3">🔍</h1>
                    <h5 class="card-title">Search Reports</h5>
                    <p class="card-text text-muted">Filter students by criteria</p>
                    <a href="ReportCriteriaServlet" class="btn btn-secondary btn-custom w-100">Search</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
