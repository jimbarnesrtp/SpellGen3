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
import com.sandstorm.model.StatModifier;

/**
 * Servlet implementation class LoadCampaign
 */
@WebServlet("/LoadCampaign")
public class LoadCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCampaign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String file = request.getParameter("camp");
		File dir = new File(context.getRealPath("config"+File.separator+file));
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance(); 
		InputStream is = new FileInputStream(dir);
		Campaign camp = new Campaign();
		
		try {
			XMLStreamReader xmlr = xmlInputFactory.createXMLStreamReader(is);
			int i = 0;
			String tagName = null;
			
			//int t = 0;
			LinkedHashMap<Integer, StatModifier> modifiers = new LinkedHashMap<Integer, StatModifier>();
			LinkedHashMap<Integer, ArrayList<Integer>> bonusSpells = new LinkedHashMap<Integer, ArrayList<Integer>>();
			ArrayList<Integer> bonus = null;
			StatModifier mod = null;
			while (xmlr.hasNext()) {
				//System.out.println("THis is the loop:"+t++);
			    i = xmlr.next();
			    if(i == XMLStreamReader.START_ELEMENT) {
			    	tagName = xmlr.getLocalName();
			    	if(tagName.equals("MinimumStat")) {
			    		String value = xmlr.getElementText();
			    		camp.setMinStat(Integer.valueOf(value));
			    	}
			    	if(tagName.equals("MaximumSpellLevel")) {
			    		String value = xmlr.getElementText();
			    		camp.setMaxSpellLevel(Integer.valueOf(value));
			    	}
			    	if(tagName.equals("SaveDCExpression")) {
			    		String value = xmlr.getElementText();
			    		camp.setSaveDCExp(value);
			    	}
			    	if(tagName.equals("MaximumStat")) {
			    		String value = xmlr.getElementText();
			    		camp.setMaxStat(Integer.valueOf(value));
			    	}
			    	if(tagName.equals("Modifier")) {
			    		int maxSpellLevel = Integer.parseInt(xmlr.getAttributeValue(1));
			    		int key = Integer.parseInt(xmlr.getAttributeValue(0));
			    		
			    		mod = new StatModifier();
			    		mod.setMaxSpellLevel(maxSpellLevel);
			    		String value = xmlr.getElementText();
			    		mod.setModifier(Integer.parseInt(value));
			    		modifiers.put(key, mod );
			    	}
			    	if(tagName.equals("Slots")) {
			    		bonus = null;
			    		int key = Integer.parseInt(xmlr.getAttributeValue(0));
			    		String value = xmlr.getElementText();
			    		if(!value.equals("")) {
			    			bonus = new ArrayList<Integer>();
			    			String[] splits = value.split(" ");
			    			for(String u: splits) {
			    				bonus.add(Integer.parseInt(u));
			    			}
			    		}
			    		bonusSpells.put(key, bonus);
			    		
			    	}
			    }		    
			    camp.setModifiers(modifiers);
			    camp.setBonusSpells(bonusSpells);
			   
			    
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.getSession().setAttribute("camp", camp);
		 RequestDispatcher rq = request.getRequestDispatcher("resources.jsp");
		 rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
