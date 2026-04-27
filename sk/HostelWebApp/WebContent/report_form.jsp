<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Criteria - Hostel Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4 shadow">
                <h3 class="text-center mb-4">Generate Report</h3>
                <form action="ReportServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Report Type</label>
                        <select name="reportType" class="form-select" id="reportType" onchange="toggleFilter()" required>
                            <option value="">-- Select Report Type --</option>
                            <option value="all">All Students</option>
                            <option value="course">By Course</option>
                            <option value="year">By Year</option>
                            <option value="room">By Room Number</option>
                        </select>
                    </div>
                    <div class="mb-3" id="filterDiv" style="display:none;">
                        <label class="form-label" id="filterLabel">Filter Value</label>
                        <input type="text" name="filterValue" class="form-control" id="filterValue">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-info text-white btn-lg px-5">Generate</button>
                        <a href="ReportServlet" class="btn btn-secondary btn-lg px-5 ms-2">Back</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
function toggleFilter() {
    var type = document.getElementById('reportType').value;
    var div = document.getElementById('filterDiv');
    var label = document.getElementById('filterLabel');
    if (type === 'all' || type === '') {
        div.style.display = 'none';
    } else {
        div.style.display = 'block';
        if (type === 'course') label.textContent = 'Enter Course Name';
        else if (type === 'year') label.textContent = 'Enter Year (1st, 2nd, 3rd, 4th)';
        else if (type === 'room') label.textContent = 'Enter Room Number';
    }
}
</script>
</body>
</html>
