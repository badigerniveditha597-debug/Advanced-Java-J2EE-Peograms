package com.servlet;

import com.dao.HostelDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class UpdateStudentServlet extends HttpServlet {
    private HostelDAO hostelDAO;

    public void init() {
        hostelDAO = new HostelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentIDStr = request.getParameter("studentID");
        if (studentIDStr != null && !studentIDStr.trim().isEmpty()) {
            try {
                int studentID = Integer.parseInt(studentIDStr.trim());
                Student existingStudent = hostelDAO.selectStudent(studentID);
                request.setAttribute("student", existingStudent);
                request.getRequestDispatcher("studentupdate.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("DisplayStudentsServlet?error=Error fetching student details");
            }
        } else {
            response.sendRedirect("DisplayStudentsServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentIDStr = request.getParameter("studentID");
        String studentName = request.getParameter("studentName");
        String roomNumber = request.getParameter("roomNumber");
        String admissionDateStr = request.getParameter("admissionDate");
        String feesPaidStr = request.getParameter("feesPaid");
        String pendingFeesStr = request.getParameter("pendingFees");

        if (studentIDStr == null || studentIDStr.trim().isEmpty() ||
            studentName == null || studentName.trim().isEmpty() ||
            roomNumber == null || roomNumber.trim().isEmpty() ||
            admissionDateStr == null || admissionDateStr.trim().isEmpty() ||
            feesPaidStr == null || feesPaidStr.trim().isEmpty() ||
            pendingFeesStr == null || pendingFeesStr.trim().isEmpty()) {
            response.sendRedirect("UpdateStudentServlet?studentID=" + studentIDStr + "&error=All fields are required");
            return;
        }

        studentName = studentName.trim();
        roomNumber = roomNumber.trim().toUpperCase();

        // Validate name: only letters and spaces allowed
        if (!studentName.matches("[a-zA-Z ]+")) {
            response.sendRedirect("UpdateStudentServlet?studentID=" + studentIDStr + "&error=Student Name must contain only letters and spaces");
            return;
        }

        // Validate room number: letter followed by digits (e.g., A101, B202)
        if (!roomNumber.matches("[A-Z]\\d{1,4}")) {
            response.sendRedirect("UpdateStudentServlet?studentID=" + studentIDStr + "&error=Room Number must be a letter followed by digits (e.g., A101, B202)");
            return;
        }

        try {
            int studentID = Integer.parseInt(studentIDStr.trim());
            Date admissionDate = Date.valueOf(admissionDateStr.trim());
            double feesPaid = Double.parseDouble(feesPaidStr.trim());
            double pendingFees = Double.parseDouble(pendingFeesStr.trim());

            if (feesPaid < 0) {
                response.sendRedirect("UpdateStudentServlet?studentID=" + studentID + "&error=Fees Paid cannot be negative");
                return;
            }
            if (pendingFees < 0) {
                response.sendRedirect("UpdateStudentServlet?studentID=" + studentID + "&error=Pending Fees cannot be negative");
                return;
            }

            Student student = new Student(studentID, studentName, roomNumber, admissionDate, feesPaid, pendingFees);
            hostelDAO.updateStudent(student);
            response.sendRedirect("DisplayStudentsServlet");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("UpdateStudentServlet?studentID=" + studentIDStr + "&error=Invalid date or number format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("DisplayStudentsServlet?error=Database error occurred");
        }
    }
}
