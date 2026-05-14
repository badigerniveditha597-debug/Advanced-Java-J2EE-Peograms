<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Students</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
    .container { max-width: 1000px; margin: auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    table, th, td { border: 1px solid #ddd; }
    th, td { padding: 12px; text-align: left; }
    th { background-color: #007BFF; color: white; }
    tr:nth-child(even) { background-color: #f2f2f2; }
    .action-links a { margin-right: 10px; text-decoration: none; color: #007BFF; }
    .action-links a:hover { text-decoration: underline; }
    .action-links .delete-btn { background: #dc3545; border: none; color: white; cursor: pointer; font-size: 14px; padding: 5px 12px; border-radius: 3px; text-decoration: none; }
    .action-links .delete-btn:hover { background: #c82333; }
    .action-links .edit-btn { background: #007BFF; color: white; padding: 5px 12px; border-radius: 3px; text-decoration: none; font-size: 14px; }
    .action-links .edit-btn:hover { background: #0056b3; }
    .back-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007BFF; }
    .back-link:hover { text-decoration: underline; }
    .error { color: red; text-align: center; margin-bottom: 10px; }

    /* Delete Confirmation Modal */
    .modal-overlay {
        display: none;
        position: fixed;
        top: 0; left: 0; width: 100%; height: 100%;
        background: rgba(0,0,0,0.5);
        z-index: 1000;
        justify-content: center;
        align-items: center;
    }
    .modal-overlay.active { display: flex; }
    .modal-box {
        background: white;
        border-radius: 8px;
        padding: 30px;
        max-width: 400px;
        width: 90%;
        text-align: center;
        box-shadow: 0 5px 20px rgba(0,0,0,0.3);
    }
    .modal-box h3 { color: #dc3545; margin: 0 0 15px 0; }
    .modal-box p { color: #333; margin: 0 0 20px 0; }
    .modal-actions { display: flex; gap: 10px; justify-content: center; }
    .modal-actions button {
        padding: 10px 25px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 15px;
        font-weight: bold;
    }
    .btn-confirm-delete { background: #dc3545; color: white; }
    .btn-confirm-delete:hover { background: #c82333; }
    .btn-cancel { background: #6c757d; color: white; }
    .btn-cancel:hover { background: #5a6268; }
</style>
</head>
<body>
<div class="container">
    <h2>All Registered Students</h2>
    <% 
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <% } %>
    
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
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Student> listStudents = (List<Student>) request.getAttribute("listStudents");
                if (listStudents != null && !listStudents.isEmpty()) {
                    int srNo = 1;
                    for (Student student : listStudents) {
            %>
                <tr>
                    <td><%= srNo++ %></td>
                    <td><%= student.getStudentID() %></td>
                    <td><%= student.getStudentName() %></td>
                    <td><%= student.getRoomNumber() %></td>
                    <td><%= student.getAdmissionDate() %></td>
                    <td>&#8377; <%= String.format("%.2f", student.getFeesPaid()) %></td>
                    <td>&#8377; <%= String.format("%.2f", student.getPendingFees()) %></td>
                    <td class="action-links">
                        <a href="UpdateStudentServlet?studentID=<%= student.getStudentID() %>" class="edit-btn">Edit</a>
                        <button type="button" class="delete-btn"
                            data-id="<%= student.getStudentID() %>"
                            data-name="<%= student.getStudentName() %>"
                            onclick="showDeleteModal(this)">Delete</button>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="8" style="text-align: center;">No students found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <a href="index.jsp" class="back-link">Back to Home</a>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal-overlay" id="deleteModal">
    <div class="modal-box">
        <h3>Confirm Delete</h3>
        <p>Are you sure you want to delete student:<br><strong id="deleteStudentInfo"></strong></p>
        <div class="modal-actions">
            <button class="btn-cancel" onclick="hideDeleteModal()">Cancel</button>
            <form id="deleteForm" action="DeleteStudentServlet" method="post" style="display:inline;">
                <input type="hidden" name="studentID" id="deleteStudentID" value="">
                <button type="submit" class="btn-confirm-delete">Delete</button>
            </form>
        </div>
    </div>
</div>

<script>
    function showDeleteModal(btn) {
        var sid = btn.getAttribute('data-id');
        var sname = btn.getAttribute('data-name');
        document.getElementById('deleteStudentID').value = sid;
        document.getElementById('deleteStudentInfo').textContent = sname + ' (ID: ' + sid + ')';
        document.getElementById('deleteModal').classList.add('active');
    }
    function hideDeleteModal() {
        document.getElementById('deleteModal').classList.remove('active');
    }
    // Close modal on overlay click
    document.getElementById('deleteModal').addEventListener('click', function(e) {
        if (e.target === this) hideDeleteModal();
    });
</script>
</body>
</html>
