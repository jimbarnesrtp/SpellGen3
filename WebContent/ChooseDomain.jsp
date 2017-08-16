<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.sandstorm.model.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Choose Domains</title>
</head>
<body>
Choose your domains:<br/>

<%ArrayList<Domain> domains = (ArrayList<Domain>)request.getSession().getAttribute("domains");
Collections.sort(domains);
%>
<form action="<%=application.getContextPath() %>/SaveDomain">
<table>
	<tr>
		<th></th>
		<th>Name</th>
		<th>Power</th>
	</tr>

<%Domain oldDomain = null;
boolean isDupe = false;
for(Domain d: domains){ 
	if(oldDomain != null) {
		if(oldDomain.getName().equals(d.getName())) {
			isDupe = true;
		} else {
			isDupe = false;
		}
	}
	
	if(!isDupe) {%>	
		<tr>
			<td><input type="checkbox" name="domain" value="<%=d.getName()%>" /></td>
			<td><%=d.getName() %></td>
			<td><%=d.getPower() %></td>
		</tr>
<%	} 
	oldDomain = d;
}%>
</table>
<input type="submit" value="Submit"></input>
</form>
</body>
</html>