<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.sandstorm.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Memorized Spells</title>
</head>
<body>
<%PlayerCharacter pc = (PlayerCharacter)request.getSession().getAttribute("character"); %>
Name <%=pc.getName() %><br/>
<%CasterClass casterClass = pc.getCasterClass();
String casterStat = casterClass.getCastinStat();%>
Your Caster Stat is <%=casterStat %><br/>
You can cast the following spells per day<br/>
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
int x = 0;
for(Integer t: spellSlots) {
	if(bonusSpells.size()>x) {%>
		level <%=x %> = <%=t+bonusSpells.get(x++)%>
	<%} else {%>
		level <%=x++ %> = <%=t %>
	<%} %>
<%} %>
<br/>
<%ArrayList<SpellListEntry> memorized = (ArrayList<SpellListEntry>)request.getSession().getAttribute("memorized"); 
Collections.sort(memorized);

if(!casterClass.isKnowAllSpells()) {%>
	<a href="DisplaySpellBook.jsp" target="_spellBook">View Known Spells</a>
<%} %>
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
String duration = "";
String spellSave = "";
String description = "";
String area = "";
String range = "";

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
	//System.out.println("THis is the spell being searched for:"+spe.getName()+" and this is the ref:"+spe.getReference());
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
			<%range = cSpell.getRange();
			if(range.contains("Close")) {
				range = range.concat("(50')");
			} else if(range.contains("Medium")) {
				range = range.concat("(210')");
			} else if(range.contains("Long")) {
				range = range.concat("(840')");
			}
			
			%>
			<td><%=range %></td>
			<%area =  cSpell.getArea().replace("CasterLevel &",pc.getCasterLevel()+"");
			if(area.contains("CasterLevel")) {
				area = area.replace("CasterLevel", pc.getCasterLevel()+"");
			}
			area = area.replace("=","");
			area = area.replace("\"","");
			area = area.replace("&","");
			%>
			<td><%=area %></td>
			<td><%=cSpell.getSchool() %></td>
			<%spellSave =  cSpell.getSave().replace("& SaveDC &",save+"");
			spellSave = spellSave.replace("=","");
			spellSave = spellSave.replace("\"","");%>
			<td><%=spellSave %></td>
			
			
			<td><%if(cSpell.isSR()){%>Yes<%} else{%>no<%} %></td>
		</tr>
		<tr bgcolor="<%=bgColor %>">
			<%description = cSpell.getDescription();
			description = description.replace("CasterLevel &",pc.getCasterLevel()+"");
			if(description.contains("CasterLevel")) {
				description = description.replace("CasterLevel", pc.getCasterLevel()+"");
			}
			description = description.replace("=","");
			description = description.replace("\"","");
			description = description.replace("&","");
			
			%>
			<td colspan="12"><%=description %></td>
		</tr>

	<%if(bgColor.equals("#cccccc")) {
		bgColor = "#FFFFFF";
	} else {
		bgColor = "#cccccc";
	}
oldSPE = spe; 
}
bgColor = "#cccccc";%>
</table>

<%ArrayList<SpellListEntry> domainSpells = (ArrayList<SpellListEntry>)request.getSession().getAttribute("domainSpells");
if(domainSpells != null && (domainSpells.size() > 0)) {
	Collections.sort(domainSpells);%>
	<hr/>
	Domain Spells:<br/>
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
	
	<%for(SpellListEntry spe: domainSpells) {
		spells = (ArrayList<Spell>) request.getSession().getAttribute("spells"+spe.getReference());
		Collections.sort(spells);
		Spell tempSpell = new Spell();
		tempSpell.setName(spe.getName());
		int spellIndex = Collections.binarySearch(spells, tempSpell);
		cSpell = spells.get(spellIndex);
		
		int save = 10+spe.getLevel()+camp.getModifiers().get(casterStatIntValue).getModifier();%>
		
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
				<%range = cSpell.getRange();
				if(range.contains("Close")) {
				 	range = range.concat("(50')");
				} else if(range.contains("Medium")) {
					range = range.concat("(210')");
				} else if(range.contains("Long")) {
					range = range.concat("(840')");
				}
				
				%>
				<td><%=range %></td>
				<%area =  cSpell.getArea().replace("CasterLevel &",pc.getCasterLevel()+"");
				if(area.contains("CasterLevel")) {
					area = area.replace("CasterLevel", pc.getCasterLevel()+"");
				}
				area = area.replace("=","");
				area = area.replace("\"","");
				area = area.replace("&","");
				%>
				<td><%=area %></td>
				<td><%=cSpell.getSchool() %></td>
				<%spellSave =  cSpell.getSave().replace("& SaveDC &",save+"");
				spellSave = spellSave.replace("=","");
				spellSave = spellSave.replace("\"","");%>
				<td><%=spellSave %></td>
				
				
				<td><%if(cSpell.isSR()){%>Yes<%} else{%>no<%} %></td>
			</tr>
			<tr bgcolor="<%=bgColor %>">
			<%description = cSpell.getDescription();
			description = description.replace("CasterLevel &",pc.getCasterLevel()+"");
			if(description.contains("CasterLevel")) {
				description = description.replace("CasterLevel", pc.getCasterLevel()+"");
			}
			description = description.replace("=","");
			description = description.replace("\"","");
			description = description.replace("&","");
			
			%>
			<td colspan="12"><%=description %></td>
		</tr>
		<%if(bgColor.equals("#cccccc")) {
			bgColor = "#FFFFFF";
		} else {
			bgColor = "#cccccc";
		}
	} %>
	</table>
<%} %>

<%ArrayList<Domain> domains = pc.getDomains();
if(domains != null && (domains.size() > 0)) {%>
<hr/>
	Domain Powers:<br/>
	
	<%for(Domain d: domains){%>
		<%=d.getName() %>: Power: <%=d.getPower() %><br/>
	<%} %>
<%} %>
</body>
</html>