package com.servlet;

import com.dao.HostelDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    private HostelDAO hostelDAO;

    public void init() {
        hostelDAO = new HostelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("DisplayStudentsServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentIDStr = request.getParameter("studentID");
        if (studentIDStr != null && !studentIDStr.trim().isEmpty()) {
            try {
                int studentID = Integer.parseInt(studentIDStr.trim());
                hostelDAO.deleteStudent(studentID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("DisplayStudentsServlet");
    }
}
