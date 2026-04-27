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
 * Servlet to display all students in the hostel.
 * GET -> Fetches all students from the database and forwards to display page.
 */
public class DisplayStudentsServlet extends HttpServlet {
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
            List<Student> studentList = hostelDAO.selectAllStudents();
            request.setAttribute("studentList", studentList);
            request.setAttribute("totalStudents", studentList.size());
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error fetching students: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("studentdisplay.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
