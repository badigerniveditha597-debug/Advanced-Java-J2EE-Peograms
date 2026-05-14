<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Form</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 500px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    form { display: flex; flex-direction: column; gap: 10px; }
    label { font-weight: bold; }
    input[type="text"], input[type="date"] { padding: 8px; border: 1px solid #ccc; border-radius: 3px; }
    input[type="submit"] { padding: 10px; background: #007BFF; color: white; border: none; border-radius: 3px; cursor: pointer; font-size: 16px; }
    input[type="submit"]:hover { background: #0056b3; }
    .error { color: red; margin-bottom: 10px; text-align: center; }
    .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007BFF; }
    .hint { font-size: 12px; color: #888; margin-top: -5px; }
</style>
</head>
<body>
<div class="container">
    <%
        String type = request.getParameter("type");
        if (type == null) type = (String) request.getAttribute("criteriaType");
        String title = "Generate Report";
        if ("roomNo".equals(type)) title = "Students by Room Number";
        else if ("dateRange".equals(type)) title = "Students by Date Range";
    %>
    <h2><%= title %></h2>
    <% String error = (String) request.getAttribute("error"); if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>
    <form action="ReportCriteriaServlet" method="get">
        <input type="hidden" name="criteriaType" value="<%= type != null ? type : "" %>">
        <% if ("roomNo".equals(type)) { %>
            <label for="criteriaValue">Enter Room Number:</label>
            <input type="text" id="criteriaValue" name="criteriaValue" required pattern="[A-Za-z]\d{1,4}" title="e.g. A101">
            <div class="hint">Format: A101, B202 etc.</div>
        <% } else if ("dateRange".equals(type)) { %>
            <label for="fromDate">From Date:</label>
            <input type="date" id="fromDate" name="fromDate" required>
            <label for="toDate">To Date:</label>
            <input type="date" id="toDate" name="toDate" required>
        <% } %>
        <input type="submit" value="Search">
    </form>
    <a href="reports.jsp" class="back-link">Back to Reports Menu</a>
</div>
</body>
</html>
