<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.sandstorm.model.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Spell Description</title>
</head>
<body>
<%Spell spell = (Spell)request.getAttribute("retrievedSpell"); %>
Name: <%=spell.getName() %><br>
Casting Time: <%=spell.getCastingTime() %><br>
Duration: <%=spell.getDuration() %><br/>
Range: <%=spell.getRange() %><br>
Save: <%=spell.getSave() %><br>
Properties: <%=spell.getProperties() %><br>
School: <%=spell.getSchool() %><br>
Area: <%=spell.getArea() %><br>
Page: <%=spell.getPage() %><br/>
Components: 
<%ArrayList<String> comps = spell.getComponents();
for(String s: comps) {%>
	<%=s %>,
<%} %>
<br>
Descriptors: 
<%
ArrayList<String> descriptors = spell.getDescriptors();
for(String s: descriptors) {%>
	<%=s %>,
<%} %>
<br>
Description: <%=spell.getDescription() %><br>

</body>
</html>