package com.HTTP_REQ_RES;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//UserDataServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/processUser")
public class UserDataServlet extends HttpServlet {
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     
     // Get parameters from the request
     String username = request.getParameter("username");
     String email = request.getParameter("email");
     String designation = request.getParameter("designation");
     
     // Perform some basic validation
     String message;
     if (username == null || username.trim().isEmpty()) {
         message = "Username is required";
     } else {
         // Process the data (in real app, you might save to database)
         message = "User data processed successfully";
         
         // Store data in request attributes to be accessed by JSP
         request.setAttribute("username", username);
         request.setAttribute("email", email);
         request.setAttribute("designation", designation);
     }
     request.setAttribute("message", message);
     
     // Forward to result JSP
     RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
     dispatcher.forward(request, response);
 }
 
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     // Redirect GET requests to the input form
     response.sendRedirect("index.jsp");
 }
}

