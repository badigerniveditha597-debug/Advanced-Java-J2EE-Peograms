<%@ page session="true" %>
<html>
<body>

<%
String name = request.getParameter("uname");
String timeStr = request.getParameter("time");

int time = Integer.parseInt(timeStr);

// Store name in session
session.setAttribute("user", name);

// Set session expiry dynamically
session.setMaxInactiveInterval(time);
%>

<h2>Hello <%= name %>!</h2>

<p>Session started successfully.</p>
<p>Session expiry time: <%= time %> seconds</p>

<p>
Click below link within <b><%= time %></b> seconds to check session:
</p>

<a href="second.jsp">Check Session</a>

</body>
</html>