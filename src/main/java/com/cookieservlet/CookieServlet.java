/*8b. Build a servlet program to  create a cookie to get your name through text 
 * box and press submit button( through HTML)  to display the message by greeting 
 * Welcome back your name ! , you have visited this page n times ( n = number of 
 * your visit )  along with the list of cookies and its setvalues and demonstrate
 *  the expiry of cookie also. 
 * 
 */
package com.cookieservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CookieServlet")

public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("userName");

        int visitCount = 0;

        Cookie[] cookies = request.getCookies();

        // Read existing cookies
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(c.getValue());
                }
                if (name == null && c.getName().equals("userName")) {
                    name = c.getValue();
                }
            }
        }

        visitCount++; // increment visit count

        // Create cookies
        Cookie nameCookie = new Cookie("userName", name);
        Cookie countCookie = new Cookie("visitCount", String.valueOf(visitCount));

        // Set expiry (30 seconds)
        nameCookie.setMaxAge(30);
        countCookie.setMaxAge(30);

        response.addCookie(nameCookie);
        response.addCookie(countCookie);

        // HTML Output
        out.println("<html><body>");

        if (name != null) {
            out.println("<h2 style='color:blue;'>Welcome back " + name + "!</h2>");
            out.println("<h3 style='color:green;'>You have visited this page " + visitCount + " times</h3>");
        } else {
            out.println("<h2>Please enter your name first</h2>");
        }

        // Display all cookies
        out.println("<h3>List of Cookies:</h3>");

        Cookie[] allCookies = request.getCookies();

        if (allCookies != null) {
            for (Cookie c : allCookies) {
                out.println("Name: " + c.getName() + " | Value: " + c.getValue() + "<br>");
            }
        }

        out.println("<p><b>Note:</b> Cookies expire in 30 seconds. Refresh after that to see reset.</p>");

        out.println("</body></html>");
    }
}