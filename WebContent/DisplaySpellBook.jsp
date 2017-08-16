<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.sandstorm.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Known Spells</title>
</head>
<body>
<%ArrayList<SpellListEntry> memorized = (ArrayList<SpellListEntry>)request.getSession().getAttribute("spellbook"); 
PlayerCharacter pc = (PlayerCharacter)request.getSession().getAttribute("character");
CasterClass casterClass = pc.getCasterClass();

Collections.sort(memorized);
Campaign camp = (Campaign) request.getSession().getAttribute("camp");%>
<table border=1>
	<tr>
		<th>SpellName</th>
		<th>Level</th>
		<th>Type</th>
		<th>Reference</th>
		<th>Comp</th>
		<th>Cast</th>
		<th>Duration</th>
		<th>Range</th>
		<th>Area</th>
		<th>School</th>
		<th>Save</th>
		<th>SR</th>
	</tr>
	<tr>
		<td colspan="12"bgcolor="#66CC33">Level 0</td>
	</tr>
<%int i =1;
boolean first = true;
SpellListEntry oldSPE = memorized.get(0);
Spell cSpell = null;
ArrayList<Spell> spells = null;
String bgColor = "#cccccc";
int casterStatIntValue = 0;
String duration = "";
String spellSave = "";

String casterStat = casterClass.getCastinStat();
if(casterStat.equalsIgnoreCase("Cha")) {
	casterStatIntValue = pc.getCha();
} else if(casterStat.equalsIgnoreCase("Int")) {
	casterStatIntValue = pc.getIntel();
} else if(casterStat.equalsIgnoreCase("wis")) {
	casterStatIntValue = pc.getWiz();
}
for(SpellListEntry spe: memorized) {
	if(!first) {
		if(oldSPE.getLevel() != spe.getLevel()) {%>
		<tr>
		<td colspan="12" bgcolor="#66CC33">Level <%=spe.getLevel() %></td>
		</tr>
	<%} 
	} else {
		first = false;
	}
	System.out.println("THis is the spell being searched for:"+spe.getName()+" and this is the ref:"+spe.getReference());
	spells = (ArrayList<Spell>) request.getSession().getAttribute("spells"+spe.getReference());
	Collections.sort(spells);
	Spell tempSpell = new Spell();
	tempSpell.setName(spe.getName());
	int spellIndex = Collections.binarySearch(spells, tempSpell);
	cSpell = spells.get(spellIndex);
	
	int save = 10+spe.getLevel()+camp.getModifiers().get(casterStatIntValue).getModifier();
	
	%>

		<tr bgcolor="<%=bgColor %>">
			<td><%=spe.getName() %></td>
			<td><%=spe.getLevel() %></td>
			<td><%=casterClass.getAbbrev() %></td>
			<td><%=spe.getReference()%>:<%=cSpell.getPage() %></td>
			<td><%=cSpell.getComponents() %></td>
			<td><%=cSpell.getCastingTime() %></td>
			<% duration = cSpell.getDuration().replace("CasterLevel &",pc.getCasterLevel()+"");
			if(duration.contains("CasterLevel")) {
				duration = duration.replace("CasterLevel", pc.getCasterLevel()+"");
			}
			duration = duration.replace("=","");
			duration = duration.replace("\"","");
			duration = duration.replace("&","");
			%>
			<td><%=duration %></td>
			<td><%=cSpell.getRange() %></td>
			<td><%=cSpell.getArea() %></td>
			<td><%=cSpell.getSchool() %></td>
			<%spellSave =  cSpell.getSave().replace("& SaveDC &",save+"");
			spellSave = spellSave.replace("=","");
			spellSave = spellSave.replace("\"","");%>
			<td><%=spellSave %></td>
			
			
			<td><%if(cSpell.isSR()){%>Yes<%} else{%>no<%} %></td>
		</tr>
		<tr bgcolor="<%=bgColor %>">
			<td colspan="12"><%=cSpell.getDescription() %></td>
		</tr>

<%if(bgColor.equals("#cccccc")) {
	bgColor = "#FFFFFF";
} else {
	bgColor = "#cccccc";
}
oldSPE = spe; 
}%>
</body>
</html>