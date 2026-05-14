package com.servlet;

import com.dao.HostelDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class AddStudentServlet extends HttpServlet {
    private HostelDAO hostelDAO;

    public void init() {
        hostelDAO = new HostelDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idGenMode = request.getParameter("idGenMode");
        String studentIdStr = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        String roomNumber = request.getParameter("roomNumber");
        String admissionDateStr = request.getParameter("admissionDate");
        String feesPaidStr = request.getParameter("feesPaid");
        String pendingFeesStr = request.getParameter("pendingFees");

        // Validate all fields are present
        if (studentName == null || studentName.trim().isEmpty() ||
            roomNumber == null || roomNumber.trim().isEmpty() ||
            admissionDateStr == null || admissionDateStr.trim().isEmpty() ||
            feesPaidStr == null || feesPaidStr.trim().isEmpty() ||
            pendingFeesStr == null || pendingFeesStr.trim().isEmpty()) {
            response.sendRedirect("studentadd.jsp?error=All fields are required");
            return;
        }

        studentName = studentName.trim();
        roomNumber = roomNumber.trim().toUpperCase();

        // Validate name: only letters and spaces allowed
        if (!studentName.matches("[a-zA-Z ]+")) {
            response.sendRedirect("studentadd.jsp?error=Student Name must contain only letters and spaces");
            return;
        }

        // Validate room number: letter followed by digits (e.g., A101, B202)
        if (!roomNumber.matches("[A-Z]\\d{1,4}")) {
            response.sendRedirect("studentadd.jsp?error=Room Number must be a letter followed by digits (e.g., A101, B202)");
            return;
        }

        try {
            Date admissionDate = Date.valueOf(admissionDateStr.trim());
            double feesPaid = Double.parseDouble(feesPaidStr.trim());
            double pendingFees = Double.parseDouble(pendingFeesStr.trim());

            if (feesPaid < 0) {
                response.sendRedirect("studentadd.jsp?error=Fees Paid cannot be negative");
                return;
            }
            if (pendingFees < 0) {
                response.sendRedirect("studentadd.jsp?error=Pending Fees cannot be negative");
                return;
            }

            int studentId = 0;
            if ("manual".equals(idGenMode)) {
                if (studentIdStr == null || studentIdStr.trim().isEmpty()) {
                    response.sendRedirect("studentadd.jsp?error=Student ID is required for manual generation");
                    return;
                }
                studentId = Integer.parseInt(studentIdStr.trim());
                if (studentId <= 0) {
                    response.sendRedirect("studentadd.jsp?error=Student ID must be a positive integer");
                    return;
                }
            }

            Student newStudent = new Student(studentId, studentName, roomNumber, admissionDate, feesPaid, pendingFees);
            
            if ("manual".equals(idGenMode)) {
                hostelDAO.insertStudentWithID(newStudent);
            } else {
                hostelDAO.insertStudent(newStudent);
            }
            response.sendRedirect("DisplayStudentsServlet");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("studentadd.jsp?error=Invalid date or number format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("studentadd.jsp?error=Database error occurred");
        }
    }
}
