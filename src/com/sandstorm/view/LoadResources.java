package com.sandstorm.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.sandstorm.model.Campaign;
import com.sandstorm.model.CasterClass;
import com.sandstorm.model.Domain;
import com.sandstorm.model.Spell;
import com.sandstorm.model.SpellList;
import com.sandstorm.model.SpellListEntry;
import com.sandstorm.model.StatModifier;

/**
 * Servlet implementation class LoadResources
 */
@WebServlet("/LoadResources")
public class LoadResources extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadResources() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String[] checkedResources = request.getParameterValues("resources");
		ArrayList<Spell> spells = null;
		ArrayList<SpellListEntry> spellListEntries = null;
		ArrayList<CasterClass> classes = null;
		ArrayList<Domain> domains = new ArrayList<Domain>();
		
		for(String u: checkedResources) {
			System.out.println("This is the passed in file:"+u);
			File dir = new File(context.getRealPath("config"+File.separator+u));
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance(); 
			InputStream is = new FileInputStream(dir);
			boolean isSpell = false;
			boolean isSpellList = false;
			Spell spell = null;
			ArrayList<String> descriptors = null;
			ArrayList<String> components = null;
			SpellList spellList = null;
			SpellListEntry spellListEntry = null;
			String reference = "";
			classes = new ArrayList<CasterClass>();
			spells = new ArrayList<Spell>();
			boolean isCasterClass = false;
			Domain domain = null;
			boolean isDomain = false;
			ArrayList<SpellListEntry> domainEntries = null;
			
			CasterClass newClass = null;
			LinkedHashMap<Integer, ArrayList<Integer>> spellSlots = null;
			boolean inSpellPyramid = false;
			boolean inKnownSpells = false;
			ArrayList<String> knownSpells = null;
			ArrayList<Integer> slots = null;
			int spellSlotIndex = 0;
			
			try {
				XMLStreamReader xmlr = xmlInputFactory.createXMLStreamReader(is);
				int i = 0;
				String tagName = null;	
				int count = 0;

				while (xmlr.hasNext()) {
					 i = xmlr.next();
				    if(i == XMLStreamReader.START_ELEMENT) {
				    	tagName = xmlr.getLocalName();
				    	if(tagName.equals("Reference")) {
				    		String name = xmlr.getAttributeValue(3);
				    		reference = name;
				    	}
				    	if(tagName.equals("Spell")) {
				    		isSpell = true;
				    		spell = new Spell();
				    		count=0;
				    		descriptors = new ArrayList<String>();
				    		components = new ArrayList<String>();
				    	}
				    	if(tagName.equals("Name")){
				    		if(isSpell) {
				    			spell.setName(xmlr.getElementText());
				    		}
				    		if(isCasterClass) {
				    			newClass.setName(xmlr.getElementText());
				    		}
				    		
				    	}
				    	if(tagName.equals("School")) {
				    		if(isSpell) {
				    			if(count==1) {
				    				spell.setSchool(xmlr.getElementText());
				    			}
				    			count++;
				    		}
				    	}
				    	if(tagName.equals("Descriptor")) {
				    		if(isSpell) {
				    			descriptors.add(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("CastingTime")) {
				    		if(isSpell) {
				    			spell.setCastingTime(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Range")) {
				    		if(isSpell) {
				    			spell.setRange(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Area")) {
				    		if(isSpell) {
				    			spell.setArea(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Duration")) {
				    		if(isSpell) {
				    			spell.setDuration(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Component")) {
				    		if(isSpell) {
				    			components.add(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Save")) {
				    		if(isSpell) {
				    			spell.setSave(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("SaveDCMod")) {
				    		if(isSpell) {
				    			spell.setSaveDCMod(Integer.parseInt(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("SR")) {
				    		if(isSpell) {
				    			spell.setSR(Boolean.parseBoolean(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("Description")) {
				    		if(isSpell) {
				    			spell.setDescription(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Page")) {
				    		if(isSpell) {
				    			spell.setPage(Integer.parseInt(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("SpellList")) {
				    		String name = xmlr.getAttributeValue(0);
				    		if(name != null) {
					    		isSpellList = true;
					    		
					    		spellList = (SpellList)request.getSession().getAttribute(name+"list");
					    		if(spellList == null) {
					    			spellList = new SpellList();
					    			spellList.setName(name);
						    		spellList.setInherits(xmlr.getAttributeValue(1));
						    		spellListEntries = new ArrayList<SpellListEntry>();	
					    		} else {
					    			spellListEntries = spellList.getSpellList();
					    		}
					    		 		
				    		}
				    	}
				    	if(tagName.equals("SpellEntry")) {
				    		if(isSpellList) {
				    			spellListEntry = new SpellListEntry();
				    			spellListEntry.setName(xmlr.getAttributeValue(0));
				    			spellListEntry.setLevel(Integer.parseInt(xmlr.getAttributeValue(1)));
				    			spellListEntry.setReference(xmlr.getAttributeValue(2));
				    			spellListEntries.add(spellListEntry);
				    		}
				    		if(isDomain) {
				    			spellListEntry = new SpellListEntry();
				    			spellListEntry.setName(xmlr.getAttributeValue(0));
				    			spellListEntry.setLevel(Integer.parseInt(xmlr.getAttributeValue(1)));
				    			spellListEntry.setReference(xmlr.getAttributeValue(2));
				    			domainEntries.add(spellListEntry);
				    		}
				    	}   
				    	if(tagName.equals("CasterClass"))  {
				    		isCasterClass = true;			    		
				    		newClass = new CasterClass();
				    	}
				    	if(tagName.equals("CasterType")) {
				    		if(isCasterClass) {
				    			newClass.setCasterType(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("Abbrev")) {
				    		if(isCasterClass) {
				    			newClass.setAbbrev(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("HasCantrips")) {
				    		if(isCasterClass) {
				    			newClass.setHasCantrips(Boolean.parseBoolean(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("MaxSpellLevel")) {
				    		if(isCasterClass) {
				    			newClass.setMaxSpellLevel(Integer.parseInt(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("MaxCasterLevel")) {
				    		if(isCasterClass) {
				    			newClass.setMaxCasterLevel(Integer.parseInt(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("CastingStat")) {
				    		if(isCasterClass) {
				    			newClass.setCastinStat(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("BonusSpellStat")) {
				    		if(isCasterClass) {
				    			newClass.setBonusSpellStat(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("SaveDCStat")) {
				    		if(isCasterClass) {
				    			newClass.setSaveDCStat(xmlr.getElementText());
				    		}
				    	}
				    	if(tagName.equals("KnowsAllSpells")) {
				    		if(isCasterClass) {
				    			newClass.setKnowAllSpells(Boolean.parseBoolean(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("KnowsAllCantrips")) {
				    		if(isCasterClass) {
				    			newClass.setKnowAllCantrips(Boolean.parseBoolean(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("SpellsKnown")) {
				    		if(isCasterClass) {
				    			newClass.setSpellsKnown(Boolean.parseBoolean(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("CasterLevelMultiplier")) {
				    		if(isCasterClass) {
				    			newClass.setCasterLevelMultiplier(Double.parseDouble(xmlr.getElementText()));
				    		}
				    	}
				    	if(tagName.equals("SpellPyramid")) {
				    		
				    		if(isCasterClass) {
				    			inSpellPyramid = true;
				    			spellSlots = new LinkedHashMap<Integer, ArrayList<Integer>>();
				    			spellSlotIndex = 1;
				    			
				    		}
				    	}
				    	if(tagName.equals("KnownPyramid")) {
				    		if(isCasterClass) {
				    			if(newClass.isKnowAllSpells()) {
				    				inKnownSpells = false;
				    			} else {
				    				inKnownSpells = true;
				    				spellSlots = new LinkedHashMap<Integer, ArrayList<Integer>>();
					    			spellSlotIndex = 1;
				    			}
				    			
				    			
				    		}
				    	}
				    	if(tagName.equals("Slots")) {
				    		if(isCasterClass) {
				    			if(inSpellPyramid || inKnownSpells) {
					    			String value = xmlr.getElementText();
					    			if(!value.equals("")) {
					    				slots = new ArrayList<Integer>();
						    			String[] splits = value.split(" ");
						    			for(String t: splits) {
						    				slots.add(Integer.parseInt(t));
						    			}   
						    			spellSlots.put(spellSlotIndex++, slots);
					    			} else {
					    				spellSlots.put(spellSlotIndex++, null);
					    			}
				    				 							 	
					    				
				    			}
				    			
				    		}
				    	}
				    	if(tagName.equals("Domain")) {
				    		isDomain = true;
				    		domain = new Domain();
				    		domain.setAbbrev(xmlr.getAttributeValue(1));
				    		domain.setName(xmlr.getAttributeValue(0));
				    		domainEntries = new ArrayList<SpellListEntry>();
				    	}
				    	if(tagName.equals("Power")) {
				    		if(isDomain) {
				    			domain.setPower(xmlr.getElementText());
				    		}
				    	}
//				    	
				    }
				    
				    
				    if(i == XMLStreamReader.END_ELEMENT) {
				    	tagName = xmlr.getLocalName();
				    	if(tagName.equals("Spell")) {
				    		isSpell = false;
				    		spells.add(spell);
				    	}				    	
				    	if(tagName.equals("Descriptors")) {
				    		if(isSpell) {
				    			spell.setDescriptors(descriptors);
				    		}
				    	}
				    	if(tagName.equals("Components")) {
				    		if(isSpell) {
				    			spell.setComponents(components);
				    		}
				    	}
				    	if(tagName.equals("SpellList")) {
				    		if(isSpellList) {
				    			isSpellList = false;
				    			spellList.setSpellList(spellListEntries);
				    			request.getSession().setAttribute(spellList.getName()+"list", spellList);
				    		}
				    	}
				    	if(tagName.equals("CasterClass"))  {
				    		isCasterClass = false;
				    		classes.add(newClass);
				    	}
				    	if(tagName.equals("SpellPyramid")) {
				    		if(isCasterClass) {
				    			inSpellPyramid = false;
				    			newClass.setSpellSlots(spellSlots);
				    		}
				    	}
				    	if(tagName.equals("KnownPyramid")) {
				    		if(isCasterClass) {
				    			inKnownSpells = false;
				    			newClass.setKnownSpellSlots(spellSlots);
				    		}
				    	}
				    	if(tagName.equals("Domain")) {
				    		isDomain = false;
				    		domain.setEntries(domainEntries);
				    		domains.add(domain);
				    	}
				    } 
				}
				System.out.println("This is the reference="+reference);
				request.getSession().setAttribute("spells"+reference, spells);
				
				request.getSession().setAttribute("classes"+reference, classes);
				
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		request.getSession().setAttribute("domains", domains);
		
		RequestDispatcher rq = request.getRequestDispatcher("EnterCharacter.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
