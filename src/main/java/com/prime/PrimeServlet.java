/*6c. Build a servlet program to check the given number is prime number
 *  or not using HTML with step by step procedure.
 * 
 */
package com.prime;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/PrimeServlet")
public class PrimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String numStr = request.getParameter("num");

        out.println("<html><body>");
        out.println("<h2>Result</h2>");

        // 🔴 FIX: Check null or empty
        if (numStr == null || numStr.isEmpty()) {
            out.println("<h3 style='color:red;'>Please enter a number!</h3>");
        } else {

            try {
                int num = Integer.parseInt(numStr);

                boolean isPrime = true;

                if (num <= 1) {
                    isPrime = false;
                } else {
                    for (int i = 2; i <= num / 2; i++) {
                        if (num % i == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                }

                if (isPrime) {
                    out.println("<h3 style='color:green;'>" + num + " is a Prime Number</h3>");
                } else {
                    out.println("<h3 style='color:red;'>" + num + " is NOT a Prime Number</h3>");
                }

            } catch (NumberFormatException e) {
                out.println("<h3 style='color:red;'>Invalid input! Enter numbers only.</h3>");
            }
        }

        out.println("<br><a href='index.html'>Check Another Number</a>");
        out.println("</body></html>");
    }
}