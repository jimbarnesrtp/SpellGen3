<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.sandstorm.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
Enter Details about your class<br></br>
First Choose a class<br></br>
<form action="<%=application.getContextPath() %>/SaveCharacter" >
Character Name<input type="text" name="chaName"></input><br></br>
<select name="chosenClass">
<%Enumeration<String> e1 = request.getSession().getAttributeNames();
String attrName = "";
while(e1.hasMoreElements()) {
	String resourceName = "";
	attrName = e1.nextElement();
	if(attrName.startsWith("classes")) {
		resourceName = attrName.substring(7);
		ArrayList<CasterClass> classes = (ArrayList<CasterClass>)request.getSession().getAttribute(attrName);
		for(CasterClass u: classes) {%>
			<option value="<%=u.getName() %>,<%=resourceName%>"><%=u.getName() %></option>
	<%	}
	}
}%>
</select><br></br>
Enter your stats:<br></br>
Str: <input type="text" name="str"></input><br></br>
Dex: <input type="text" name="dex"></input><br></br>
Con: <input type="text" name="con"></input><br></br>
Wis: <input type="text" name="wis"></input><br></br>
Int: <input type="text" name="int"></input><br></br>
Cha: <input type="text" name="cha"></input><br></br>
Caster Level: <input type="text" name="casterLevel"></input><br></br>
Show Duplicates<input type="radio" name="dup" value="true"></input> Yes <input type="radio" name="dup" value="false"></input> no
<br></br>

<input type="submit" value="Submit"></input>
</form>
</body>
</html>