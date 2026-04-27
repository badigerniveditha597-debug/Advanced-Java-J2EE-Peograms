package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to show the report criteria/filter form.
 */
public class ReportCriteriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("type");
        if (reportType != null) {
            request.setAttribute("selectedType", reportType);
        }
        request.getRequestDispatcher("report_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the criteria to ReportServlet for processing
        request.getRequestDispatcher("ReportServlet").forward(request, response);
    }
}
