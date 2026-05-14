<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.HostelDAO" %>
<%
    HostelDAO dao = new HostelDAO();
    int nextId = dao.getNextStudentID();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 500px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    form { display: flex; flex-direction: column; gap: 10px; }
    label { font-weight: bold; }
    input[type="text"], input[type="number"], input[type="date"] { padding: 8px; border: 1px solid #ccc; border-radius: 3px; }
    input[type="submit"] { padding: 10px; background: #28a745; color: white; border: none; border-radius: 3px; cursor: pointer; font-size: 16px; }
    input[type="submit"]:hover { background: #218838; }
    .error { color: red; margin-bottom: 10px; }
    .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007BFF; }
    .back-link:hover { text-decoration: underline; }
    .hint { font-size: 12px; color: #888; margin-top: -5px; }
</style>
<script>
    function validateForm() {
        var mode = document.querySelector('input[name="idGenMode"]:checked').value;
        if (mode === 'manual') {
            var studentId = document.getElementById("studentId").value.trim();
            if (studentId === '' || isNaN(studentId) || parseInt(studentId) <= 0) {
                alert("Please enter a valid positive Student ID.");
                return false;
            }
        }
        
        var name = document.getElementById("studentName").value.trim();
        var room = document.getElementById("roomNumber").value.trim().toUpperCase();
        
        // Validate name: only letters and spaces
        var namePattern = /^[a-zA-Z ]+$/;
        if (!namePattern.test(name)) {
            alert("Student Name must contain only letters and spaces.");
            return false;
        }
        
        // Validate room number: letter followed by digits (e.g., A101)
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

    function toggleIdMode() {
        var mode = document.querySelector('input[name="idGenMode"]:checked').value;
        var idInput = document.getElementById("studentId");
        if (mode === 'automatic') {
            idInput.readOnly = true;
            idInput.value = '<%= nextId %>';
            idInput.style.backgroundColor = '#e9ecef';
        } else {
            idInput.readOnly = false;
            idInput.value = '';
            idInput.style.backgroundColor = '#fff';
            idInput.focus();
        }
    }
    
    window.onload = function() {
        toggleIdMode();
    };
</script>
</head>
<body>
<div class="container">
    <h2>Add New Student</h2>
    <% 
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <% } %>
    <form action="AddStudentServlet" method="post" onsubmit="return validateForm();">
        <label>Student ID Generation:</label>
        <div style="display: flex; gap: 15px; margin-bottom: 5px;">
            <label style="font-weight: normal;"><input type="radio" name="idGenMode" value="automatic" checked onclick="toggleIdMode()"> Automatic</label>
            <label style="font-weight: normal;"><input type="radio" name="idGenMode" value="manual" onclick="toggleIdMode()"> Manual</label>
        </div>

        <label for="studentId">Student ID:</label>
        <input type="number" id="studentId" name="studentId" value="<%= nextId %>" readonly style="background-color: #e9ecef;" required>

        <label for="studentName" style="margin-top: 10px;">Student Name:</label>
        <input type="text" id="studentName" name="studentName" required pattern="[a-zA-Z ]+" title="Only letters and spaces allowed">
        <div class="hint">Only letters and spaces allowed</div>

        <label for="roomNumber">Room Number:</label>
        <input type="text" id="roomNumber" name="roomNumber" required pattern="[A-Za-z]\d{1,4}" title="Letter followed by digits (e.g., A101)">
        <div class="hint">Format: A101, B202, C3 etc.</div>

        <label for="admissionDate">Admission Date:</label>
        <input type="date" id="admissionDate" name="admissionDate" required>

        <label for="feesPaid">Fees Paid (INR):</label>
        <input type="number" id="feesPaid" name="feesPaid" min="0" max="9999999999" step="0.01" required oninput="if(this.value.replace('.','').length>10) this.value=this.value.slice(0,-1)">

        <label for="pendingFees">Pending Fees (INR):</label>
        <input type="number" id="pendingFees" name="pendingFees" min="0" max="9999999999" step="0.01" required oninput="if(this.value.replace('.','').length>10) this.value=this.value.slice(0,-1)">

        <input type="submit" value="Save Student">
    </form>
    <a href="index.jsp" class="back-link">Back to Home</a>
</div>
</body>
</html>
