<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hostel Management System</title>
<style>
    body { 
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        margin: 0; 
        padding: 40px 20px; 
        min-height: 100vh;
        color: white;
        box-sizing: border-box;
    }
    .header {
        text-align: center;
        margin-bottom: 40px;
    }
    .header h1 {
        font-size: 3em;
        margin: 0 0 10px 0;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
    }
    .header p {
        font-size: 1.2em;
        margin: 0;
        opacity: 0.9;
    }
    .container { 
        max-width: 1000px; 
        margin: 0 auto; 
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
    }
    .card {
        background: white;
        border-radius: 12px;
        padding: 30px 20px;
        text-align: center;
        box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        transition: transform 0.2s, box-shadow 0.2s;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    .card:hover {
        transform: translateY(-5px);
        box-shadow: 0 12px 25px rgba(0,0,0,0.2);
    }
    .icon {
        font-size: 3em;
        margin-bottom: 15px;
    }
    .card h3 {
        color: #333;
        margin: 0 0 10px 0;
        font-size: 1.4em;
    }
    .card p {
        color: #666;
        margin: 0 0 20px 0;
        font-size: 0.95em;
    }
    .btn {
        display: block;
        width: 100%;
        padding: 12px;
        border-radius: 8px;
        color: white;
        text-decoration: none;
        font-weight: bold;
        font-size: 1.1em;
        transition: opacity 0.2s;
        box-sizing: border-box;
    }
    .btn:hover {
        opacity: 0.9;
    }
    .btn-green { background-color: #198754; }
    .btn-blue { background-color: #0d6efd; }
    .btn-yellow { background-color: #ffc107; color: #000; }
    .btn-red { background-color: #dc3545; }
    .btn-cyan { background-color: #0dcaf0; color: #000; }
    .error { color: #ffcccc; text-align: center; margin-bottom: 20px; font-weight: bold; background: rgba(220, 53, 69, 0.8); padding: 10px; border-radius: 5px; max-width: 800px; margin-left: auto; margin-right: auto;}
</style>
</head>
<body>

<div class="header">
    <h1>Hostel Management System</h1>
    <p>Manage students, rooms, and generate reports</p>
</div>

<% 
    String error = request.getParameter("error");
    if (error != null) {
%>
    <div class="error"><%= error %></div>
<% } %>

<div class="container">
    <!-- Card 1 -->
    <div class="card">
        <div>
            <div class="icon">&#10010;</div>
            <h3>Add Student</h3>
            <p>Register a new student in the hostel</p>
        </div>
        <a href="studentadd.jsp" class="btn btn-green">Add Student</a>
    </div>

    <!-- Card 2 -->
    <div class="card">
        <div>
            <div class="icon">&#128203;</div>
            <h3>Display Students</h3>
            <p>View all registered students</p>
        </div>
        <a href="DisplayStudentsServlet" class="btn btn-blue">View All</a>
    </div>

    <!-- Card 3 -->
    <div class="card">
        <div>
            <div class="icon">&#9998;</div>
            <h3>Update Student</h3>
            <p>Modify existing student details</p>
        </div>
        <a href="DisplayStudentsServlet" class="btn btn-yellow">Update</a>
    </div>

    <!-- Card 4 -->
    <div class="card">
        <div>
            <div class="icon">&#128465;</div>
            <h3>Delete Student</h3>
            <p>Remove a student from records</p>
        </div>
        <a href="DisplayStudentsServlet" class="btn btn-red">Delete</a>
    </div>

    <!-- Card 5 -->
    <div class="card">
        <div>
            <div class="icon">&#128202;</div>
            <h3>Reports</h3>
            <p>View hostel statistics and reports</p>
        </div>
        <a href="reports.jsp" class="btn btn-cyan">Reports</a>
    </div>
</div>

</body>
</html>
