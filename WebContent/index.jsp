<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Spell Gen 3</title>
</head>
<body>

<%


List<String> textFiles = new ArrayList<String>();
ServletContext context = getServletContext();
File dir = new File(context.getRealPath("config"));
for (File file : dir.listFiles()) {
	if (file.getName().endsWith((".sgp"))) {
		textFiles.add(file.getName());
 	}
}
%>
Choose your campaign
<form action="<%=application.getContextPath() %>/LoadCampaign" >

<ol>
<%
String name = "";
for(String u: textFiles) {
	String[] pieces = u.split("\\."); %>
	<li><input type="radio" name="camp" value="<%=u %>"></input> <%=pieces[0] %></li>
<%} %>
</ol>

<input type="submit" value="Submit"></input>
</form>
</body>
</html>