

<%@ page import="javax.servlet.http.Cookie" %>
<html>
<head>
    <title>Cookie Added</title>
</head>
<body>

<%
    String name = request.getParameter("cname");
    String domain = request.getParameter("cdomain");
    int age = Integer.parseInt(request.getParameter("cage"));

    Cookie cookie = new Cookie(name, "SampleValue");

    cookie.setDomain(domain);
    cookie.setMaxAge(age);

    response.addCookie(cookie);
%>

<h2>Cookie Created Successfully!</h2>

<p><b>Name:</b> <%= name %></p>
<p><b>Domain:</b> <%= domain %></p>
<p><b>Max Age:</b> <%= age %> seconds</p>

<br>
<a href="showCookies.jsp">Go to Active Cookie List</a>

</body>
</html>