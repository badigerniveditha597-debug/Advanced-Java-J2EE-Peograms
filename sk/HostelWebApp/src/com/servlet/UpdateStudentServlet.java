package com.servlet;

import com.dao.HostelDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to handle updating an existing student record.
 * GET  -> Loads student data into the update form
 * POST -> Processes the update and saves changes
 */
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get student ID from request parameter
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Student student = hostelDAO.selectStudentById(id);
                if (student != null) {
                    request.setAttribute("student", student);
                } else {
                    request.setAttribute("message", "Student not found with ID: " + id);
                    request.setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid student ID format.");
                request.setAttribute("messageType", "error");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.setAttribute("messageType", "error");
            }
        }

        request.getRequestDispatcher("studentupdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roomNumber = request.getParameter("roomNumber");
        String course = request.getParameter("course");
        String year = request.getParameter("year");
        String address = request.getParameter("address");

        // Create updated Student object
        Student student = new Student(id, name, email, phone, roomNumber, course, year, address);

        try {
            boolean updated = hostelDAO.updateStudent(student);
            if (updated) {
                request.setAttribute("message", "Student updated successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "No student found with ID: " + id);
                request.setAttribute("messageType", "error");
            }
            request.setAttribute("student", student);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error updating student: " + e.getMessage());
            request.setAttribute("messageType", "error");
            request.setAttribute("student", student);
        }

        request.getRequestDispatcher("studentupdate.jsp").forward(request, response);
    }
}
