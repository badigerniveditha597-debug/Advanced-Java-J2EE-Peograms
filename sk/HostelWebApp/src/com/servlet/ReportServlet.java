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

public class ReportServlet extends HttpServlet {
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
            int totalStudents = hostelDAO.getTotalStudentCount();
            List<String[]> courseStats = hostelDAO.getCountByCourse();
            List<String[]> yearStats = hostelDAO.getCountByYear();
            request.setAttribute("totalStudents", totalStudents);
            request.setAttribute("courseStats", courseStats);
            request.setAttribute("yearStats", yearStats);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error loading report data: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("reportType");
        String filterValue = request.getParameter("filterValue");
        try {
            List<Student> results = null;
            String reportTitle = "";
            switch (reportType != null ? reportType : "") {
                case "course":
                    results = hostelDAO.getStudentsByCourse(filterValue);
                    reportTitle = "Students in Course: " + filterValue;
                    break;
                case "year":
                    results = hostelDAO.getStudentsByYear(filterValue);
                    reportTitle = "Students in Year: " + filterValue;
                    break;
                case "room":
                    results = hostelDAO.getStudentsByRoom(filterValue);
                    reportTitle = "Students in Room: " + filterValue;
                    break;
                case "all":
                    results = hostelDAO.selectAllStudents();
                    reportTitle = "All Students Report";
                    break;
                default:
                    request.setAttribute("message", "Please select a valid report type.");
                    request.setAttribute("messageType", "error");
                    doGet(request, response);
                    return;
            }
            request.setAttribute("reportResults", results);
            request.setAttribute("reportTitle", reportTitle);
            request.setAttribute("resultCount", results != null ? results.size() : 0);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error generating report: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("report_result.jsp").forward(request, response);
    }
}
