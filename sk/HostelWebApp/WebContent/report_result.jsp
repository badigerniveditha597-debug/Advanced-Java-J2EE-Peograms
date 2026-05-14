<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Results</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 900px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    .criteria { text-align: center; font-size: 16px; margin-bottom: 20px; color: #555; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    table, th, td { border: 1px solid #ddd; }
    th, td { padding: 12px; text-align: left; }
    th { background-color: #17a2b8; color: white; }
    tr:nth-child(even) { background-color: #f2f2f2; }
    .back-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007BFF; }
    .back-link:hover { text-decoration: underline; }
</style>
</head>
<body>
<div class="container">
    <h2>Report Results</h2>
    <%
        String criteriaType = (String) request.getAttribute("criteriaType");
        String criteriaValue = (String) request.getAttribute("criteriaValue");
        String criteriaText = "";
        if ("roomNo".equals(criteriaType)) criteriaText = "Room Number: " + criteriaValue;
        else if ("dateRange".equals(criteriaType)) criteriaText = "Admission Date: " + criteriaValue;
        else if ("pendingFees".equals(criteriaType)) criteriaText = criteriaValue;
    %>
    <div class="criteria"><strong>Filter:</strong> <%= criteriaText %></div>
    <table>
        <thead>
            <tr>
                <th>Sr. No.</th>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Room Number</th>
                <th>Admission Date</th>
                <th>Fees Paid</th>
                <th>Pending Fees</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Student> reportResults = (List<Student>) request.getAttribute("reportResults");
                if (reportResults != null && !reportResults.isEmpty()) {
                    int srNo = 1;
                    for (Student student : reportResults) {
            %>
                <tr>
                    <td><%= srNo++ %></td>
                    <td><%= student.getStudentID() %></td>
                    <td><%= student.getStudentName() %></td>
                    <td><%= student.getRoomNumber() %></td>
                    <td><%= student.getAdmissionDate() %></td>
                    <td>&#8377; <%= String.format("%.2f", student.getFeesPaid()) %></td>
                    <td>&#8377; <%= String.format("%.2f", student.getPendingFees()) %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="7" style="text-align: center;">No students matched the criteria.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <a href="reports.jsp" class="back-link">Back to Reports Menu</a>
</div>
</body>
</html>
