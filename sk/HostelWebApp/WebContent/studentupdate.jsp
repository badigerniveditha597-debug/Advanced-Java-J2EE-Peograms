<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 500px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    form { display: flex; flex-direction: column; gap: 10px; }
    label { font-weight: bold; }
    input[type="text"], input[type="number"], input[type="date"] { padding: 8px; border: 1px solid #ccc; border-radius: 3px; }
    input[type="submit"] { padding: 10px; background: #007BFF; color: white; border: none; border-radius: 3px; cursor: pointer; font-size: 16px; }
    input[type="submit"]:hover { background: #0056b3; }
    .error { color: red; margin-bottom: 10px; }
    .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007BFF; }
    .back-link:hover { text-decoration: underline; }
    .hint { font-size: 12px; color: #888; margin-top: -5px; }
    .id-display { background: #e9ecef; padding: 8px; border-radius: 3px; color: #495057; font-weight: bold; }
</style>
<script>
    function validateForm() {
        var name = document.getElementById("studentName").value.trim();
        var room = document.getElementById("roomNumber").value.trim().toUpperCase();
        
        var namePattern = /^[a-zA-Z ]+$/;
        if (!namePattern.test(name)) {
            alert("Student Name must contain only letters and spaces.");
            return false;
        }
        
        var roomPattern = /^[A-Za-z]\d{1,4}$/;
        if (!roomPattern.test(room)) {
            alert("Room Number must be a letter followed by digits (e.g., A101, B202).");
            return false;
        }
        
        // Validate fees: max 10 digits
        var feesPaid = document.getElementById("feesPaid").value.replace('.', '');
        var pendingFees = document.getElementById("pendingFees").value.replace('.', '');
        if (feesPaid.length > 10) {
            alert("Fees Paid cannot exceed 10 digits.");
            return false;
        }
        if (pendingFees.length > 10) {
            alert("Pending Fees cannot exceed 10 digits.");
            return false;
        }
        
        return true;
    }
</script>
</head>
<body>
<div class="container">
    <h2>Update Student Details</h2>
    <% 
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <% } %>
    
    <%
        Student student = (Student) request.getAttribute("student");
        if (student != null) {
    %>
    <form action="UpdateStudentServlet" method="post" onsubmit="return validateForm();">
        <input type="hidden" name="studentID" value="<%= student.getStudentID() %>">

        <label>Student ID:</label>
        <div class="id-display"><%= student.getStudentID() %></div>

        <label for="studentName">Student Name:</label>
        <input type="text" id="studentName" name="studentName" value="<%= student.getStudentName() %>" required pattern="[a-zA-Z ]+" title="Only letters and spaces allowed">
        <div class="hint">Only letters and spaces allowed</div>

        <label for="roomNumber">Room Number:</label>
        <input type="text" id="roomNumber" name="roomNumber" value="<%= student.getRoomNumber() %>" required pattern="[A-Za-z]\d{1,4}" title="Letter followed by digits (e.g., A101)">
        <div class="hint">Format: A101, B202, C3 etc.</div>

        <label for="admissionDate">Admission Date:</label>
        <input type="date" id="admissionDate" name="admissionDate" value="<%= student.getAdmissionDate() %>" required>

        <label for="feesPaid">Fees Paid (INR):</label>
        <input type="number" id="feesPaid" name="feesPaid" value="<%= student.getFeesPaid() %>" min="0" max="9999999999" step="0.01" required oninput="if(this.value.replace('.','').length>10) this.value=this.value.slice(0,-1)">

        <label for="pendingFees">Pending Fees (INR):</label>
        <input type="number" id="pendingFees" name="pendingFees" value="<%= student.getPendingFees() %>" min="0" max="9999999999" step="0.01" required oninput="if(this.value.replace('.','').length>10) this.value=this.value.slice(0,-1)">

        <input type="submit" value="Update Student">
    </form>
    <%
        } else {
    %>
        <p class="error">Student details not found.</p>
    <%
        }
    %>
    <a href="DisplayStudentsServlet" class="back-link">Back to Student List</a>
</div>
</body>
</html>
