<%@page import ="com.DBHandle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	if (request.getParameter("itemCode") != null)
	{
		DBHandle itemObj = new DBHandle();
		itemObj.connect();
		
		String stsMsg = itemObj.insertComplaint(request.getParameter("name"),
			request.getParameter("email"),
			request.getParameter("contact"),
			request.getParameter("complaint"));
			session.setAttribute("statusMsg", stsMsg);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaints Management</title>
</head>
<body>
	<h1>Complaints Management</h1>
	<form method="post" action="test.jsp">
		Name: <input name="name" type="text"><br>
		Email: <input name="email" type="text"><br>
		Contact: <input name="contact" type="text"><br>
		Complaint: <input name="complaint" type="text"><br>
		<input name="btnSubmit" type="submit" value="Save">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
</body>
</html>