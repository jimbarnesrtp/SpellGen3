package com.sandstorm.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sandstorm.model.CasterClass;
import com.sandstorm.model.Domain;
import com.sandstorm.model.PlayerCharacter;
import com.sandstorm.model.SpellList;
import java.util.ArrayList;
import java.util.Collections;
import com.sandstorm.model.SpellListEntry;

/**
 * Servlet implementation class SaveSpells
 */
@WebServlet("/SaveSpells")
public class SaveSpells extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveSpells() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerCharacter pc = (PlayerCharacter)request.getSession().getAttribute("character");
		CasterClass casterClass = pc.getCasterClass();
		SpellList spellList = (SpellList)request.getSession().getAttribute(casterClass.getName()+"list");
		ArrayList<SpellListEntry> spells = spellList.getSpellList();
		Collections.sort(spells);
		ArrayList<SpellListEntry> memorized = new ArrayList<SpellListEntry>();
		ArrayList<SpellListEntry> spellBook = new ArrayList<SpellListEntry>();
		//next iterate over submitted form getting list and building both spellbook and memorized spells.
		String[] checkedSpellbook = request.getParameterValues("spellbook");
		String[] checkedMemorized = request.getParameterValues("memorized");
		SpellListEntry oldSPE = null;
		boolean checkForAdd = true;
		for(SpellListEntry spe: spells) {
			if(oldSPE == null) {
				checkForAdd = true;
			} else {
				if(oldSPE.getName().equals(spe.getName())) {
					checkForAdd = false;
				} else {
					checkForAdd = true;
				}
			}
			if(checkForAdd) {
				if(!pc.getCasterClass().isKnowAllSpells()) {
					if(isCheckedForSpellbook(spe, checkedSpellbook)) {
						spellBook.add(spe);
					}
				}
				if(isCheckedForSpellbook(spe, checkedMemorized)) {
					memorized.add(spe);
				}
			}
			oldSPE = spe;
		}
		ArrayList<Domain> domains = pc.getDomains();
		String[] checkedDomain = request.getParameterValues("memorizedDomain");
		ArrayList<SpellListEntry> memorizedDomain = new ArrayList<SpellListEntry>();
		ArrayList<SpellListEntry> domainSpells = null;
		for(Domain d: domains) {
			domainSpells = d.getEntries();
			for(SpellListEntry spe: domainSpells) {
				if(isCheckedForSpellbook(spe, checkedDomain)) {
					memorizedDomain.add(spe);
				}
			}
		}
		request.getSession().setAttribute("domainSpells", memorizedDomain);
		request.getSession().setAttribute("spellBook", spellBook);
		request.getSession().setAttribute("memorized", memorized);
		
		RequestDispatcher rq = request.getRequestDispatcher("DisplaySpells.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private boolean isCheckedForSpellbook(SpellListEntry spe, String[] checkedResources) {
		boolean isChecked = false;
		for(String s: checkedResources) {
			if(s.equals(spe.getName()+","+spe.getReference())) {
				isChecked = true;
				break;
			}
		}
		
		return isChecked;
	}

}
