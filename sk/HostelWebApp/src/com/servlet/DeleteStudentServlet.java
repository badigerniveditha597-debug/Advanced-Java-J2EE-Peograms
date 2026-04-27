package com.servlet;

import com.dao.HostelDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle deleting a student from the hostel database.
 * GET  -> Shows the delete confirmation page with student list
 * POST -> Processes the deletion by student ID
 */
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load all students for the delete page
            List<Student> studentList = hostelDAO.selectAllStudents();
            request.setAttribute("studentList", studentList);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error loading students: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                boolean deleted = hostelDAO.deleteStudent(id);
                if (deleted) {
                    request.setAttribute("message", "Student deleted successfully!");
                    request.setAttribute("messageType", "success");
                } else {
                    request.setAttribute("message", "No student found with ID: " + id);
                    request.setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid student ID format.");
                request.setAttribute("messageType", "error");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error deleting student: " + e.getMessage());
                request.setAttribute("messageType", "error");
            }
        } else {
            request.setAttribute("message", "Please provide a student ID to delete.");
            request.setAttribute("messageType", "error");
        }

        // Reload the student list after deletion
        try {
            List<Student> studentList = hostelDAO.selectAllStudents();
            request.setAttribute("studentList", studentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
    }
}
