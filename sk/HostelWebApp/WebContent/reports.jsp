<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports Menu</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    .menu { display: flex; flex-direction: column; gap: 10px; margin-top: 20px; }
    .menu a { padding: 15px; background: #17a2b8; color: white; text-decoration: none; text-align: center; border-radius: 3px; font-size: 18px; }
    .menu a:hover { background: #138496; }
    .back-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007BFF; }
</style>
</head>
<body>
<div class="container">
    <h2>Generate Reports</h2>
    <div class="menu">
        <a href="report_form.jsp?type=roomNo">Students by Room Number</a>
        <a href="report_form.jsp?type=dateRange">Students by Date Range</a>
        <a href="ReportCriteriaServlet?criteriaType=pendingFees">Students with Pending Fees</a>
    </div>
    <a href="index.jsp" class="back-link">Back to Home</a>
</div>
</body>
</html>
