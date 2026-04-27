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
 * Servlet to handle adding a new student to the hostel database.
 * GET  -> Forwards to the studentadd.jsp form
 * POST -> Processes the form submission and inserts the student
 */
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the add student form
        request.getRequestDispatcher("studentadd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roomNumber = request.getParameter("roomNumber");
        String course = request.getParameter("course");
        String year = request.getParameter("year");
        String address = request.getParameter("address");

        // Create Student object
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setRoomNumber(roomNumber);
        student.setCourse(course);
        student.setYear(year);
        student.setAddress(address);

        try {
            hostelDAO.insertStudent(student);
            // Set success message
            request.setAttribute("message", "Student added successfully!");
            request.setAttribute("messageType", "success");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error adding student: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        // Forward back to the add form with the message
        request.getRequestDispatcher("studentadd.jsp").forward(request, response);
    }
}
