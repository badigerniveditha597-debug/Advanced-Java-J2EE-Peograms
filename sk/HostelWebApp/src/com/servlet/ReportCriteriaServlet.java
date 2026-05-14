package com.servlet;

import com.dao.HostelDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ReportCriteriaServlet extends HttpServlet {
    private HostelDAO hostelDAO;

    public void init() {
        hostelDAO = new HostelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String criteriaType = request.getParameter("criteriaType");
        String criteriaValue = request.getParameter("criteriaValue");

        if (criteriaType == null || criteriaType.trim().isEmpty()) {
            request.setAttribute("error", "Report type is required");
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
            return;
        }

        try {
            List<Student> reportResults = null;

            if ("roomNo".equals(criteriaType)) {
                if (criteriaValue == null || criteriaValue.trim().isEmpty()) {
                    request.setAttribute("error", "Room Number is required");
                    request.setAttribute("criteriaType", criteriaType);
                    request.getRequestDispatcher("report_form.jsp").forward(request, response);
                    return;
                }
                reportResults = hostelDAO.selectStudentsByRoom(criteriaValue.trim().toUpperCase());
                request.setAttribute("criteriaValue", criteriaValue.trim().toUpperCase());

            } else if ("dateRange".equals(criteriaType)) {
                String fromDateStr = request.getParameter("fromDate");
                String toDateStr = request.getParameter("toDate");
                if (fromDateStr == null || fromDateStr.trim().isEmpty() ||
                    toDateStr == null || toDateStr.trim().isEmpty()) {
                    request.setAttribute("error", "Both From Date and To Date are required");
                    request.setAttribute("criteriaType", criteriaType);
                    request.getRequestDispatcher("report_form.jsp").forward(request, response);
                    return;
                }
                Date fromDate = Date.valueOf(fromDateStr.trim());
                Date toDate = Date.valueOf(toDateStr.trim());
                reportResults = hostelDAO.selectStudentsByDateRange(fromDate, toDate);
                request.setAttribute("criteriaValue", fromDateStr + " to " + toDateStr);

            } else if ("pendingFees".equals(criteriaType)) {
                reportResults = hostelDAO.selectStudentsPendingFees();
                request.setAttribute("criteriaValue", "All students with pending fees");

            } else {
                request.setAttribute("error", "Invalid report type");
                request.getRequestDispatcher("report_form.jsp").forward(request, response);
                return;
            }

            request.setAttribute("reportResults", reportResults);
            request.setAttribute("criteriaType", criteriaType);
            request.getRequestDispatcher("report_result.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format");
            request.setAttribute("criteriaType", criteriaType);
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred");
            request.setAttribute("criteriaType", criteriaType);
            request.getRequestDispatcher("report_form.jsp").forward(request, response);
        }
    }
}
