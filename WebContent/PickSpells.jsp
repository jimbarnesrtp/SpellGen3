<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.sandstorm.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Pick your memorized and known spells</title>
</head>
<body>
<%PlayerCharacter pc = (PlayerCharacter)request.getSession().getAttribute("character"); %>
Name <%=pc.getName() %><br></br>
<%CasterClass casterClass = pc.getCasterClass();
String casterStat = casterClass.getCastinStat();%>
Your Caster Stat is <%=casterStat %><br></br>
You can cast the following spells per day<br></br>
<%ArrayList<Integer> spellSlots = casterClass.getSpellSlots().get(pc.getCasterLevel());
int casterStatIntValue = 0;
if(casterStat.equalsIgnoreCase("Cha")) {
	casterStatIntValue = pc.getCha();
} else if(casterStat.equalsIgnoreCase("Int")) {
	casterStatIntValue = pc.getIntel();
} else if(casterStat.equalsIgnoreCase("wis")) {
	casterStatIntValue = pc.getWiz();
}
Campaign camp = (Campaign)request.getSession().getAttribute("camp");
ArrayList<Integer> bonusSpells = camp.getBonusSpells().get(casterStatIntValue);
int i = 0;
for(Integer t: spellSlots) {%>
	level <%=i++ %> = <%=t %>
<%} %>
<br>
</br>
You have the following bonus spells per day<br>
</br><%

int f = 0;
if(bonusSpells != null) {
	for(Integer t: bonusSpells) {%>
		level <%=f++ %> = <%=t %>
	<%} 
}%>
<br></br>
<%boolean knowAllSpells = casterClass.isKnowAllSpells(); 
SpellList spellList = (SpellList)request.getSession().getAttribute(casterClass.getName()+"list");
ArrayList<SpellListEntry> spells = spellList.getSpellList();
Collections.sort(spells);
System.out.println("The number of spells is:"+spells.size());
SpellListEntry oldSPE = spells.get(0); %>
<form action="<%=application.getContextPath() %>/SaveSpells">
<table>
	<tr>
		<%if(!knowAllSpells) {%>
			<th>Spellbook</td>
		<%} %>
		<th>Memorized</th>
		<th>Name</th>
		<th>Reference</th>
		<th>Description</th>
	</tr>
	<tr>
		<td colspan="4">Level <%=oldSPE.getLevel() %></td>
	</tr>
<%
boolean first = true;
boolean skipSpellEntry = false;
for(SpellListEntry spe: spells) {
	if(!first) {
		if(oldSPE.getName().equals(spe.getName())) {
			skipSpellEntry = true;
		} else {
			skipSpellEntry = false;
		}
		
	} else {
		first = false;
		
	}
	if(!skipSpellEntry) {
		if(spe.getLevel()< i) {
			if(oldSPE.getLevel() != spe.getLevel()) {%>
				<td colspan="4">Level <%=spe.getLevel() %></td>
			<%} %>
			<tr>
				<%if(!knowAllSpells){ %>
					<td><input type="checkbox" name="spellbook" value="<%=spe.getName()%>,<%=spe.getReference()%>" /></td>
				<%} %>
				<td><input type="checkbox" name="memorized" value="<%=spe.getName()%>,<%=spe.getReference()%>" /></td>
				<td><%=spe.getName() %></td>
				<td><%=spe.getReference() %></td>
				<td><a href="/SpellGen3/GetDescription?name=<%=spe.getName() %>&ref=<%=spe.getReference()%>" target="desc">Description</a></td>
			
			</tr>
	<%		} 
		}%>
	
<% oldSPE = spe;
} %>
</table>
<h1>Choose your domain Spells</h1><br/>
<%ArrayList<Domain> domains = pc.getDomains(); %>
<%for(Domain d: domains){ %>
Domain:<%=d.getName() %><br/>
	<table>
		<tr>
			<th>Memorized</th>
			<th>Name</th>
			<th>Level</th>
			<th>Reference</th>
			<th></th>
		</tr>
		<%ArrayList<SpellListEntry> domainSpells = d.getEntries();
		for(SpellListEntry spe: domainSpells) {%>
			<tr>
				<td><input type="checkbox" name="memorizedDomain" value="<%=spe.getName()%>,<%=spe.getReference()%>" /></td>
				<td><%=spe.getName() %></td>
				<th><%=spe.getLevel() %></th>
				<td><%=spe.getReference() %></td>
				<td><a href="/SpellGen3/GetDescription?name=<%=spe.getName() %>&ref=<%=spe.getReference()%>" target="desc">Description</a></td>
			</tr>
		<%} %>
	</table>
<%} %>
<input type="submit" value="Submit"></input>
</form>
</body>
</html>