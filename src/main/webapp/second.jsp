<%@ page session="true" %>
<html>
<body>

<h2>Session Status</h2>

<%
String name = (String) session.getAttribute("user");

if (name == null) {
%>
    <h3 style="color:red;">Session expired!</h3>
<%
} else {
%>
    <h3>Hello <%= name %></h3>
    <p>Session is still active.</p>
<%
}
%>

</body>
</html>