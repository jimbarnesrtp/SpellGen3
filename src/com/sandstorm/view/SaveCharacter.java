package com.sandstorm.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sandstorm.model.CasterClass;
import com.sandstorm.model.PlayerCharacter;

/**
 * Servlet implementation class PickSpells
 */
@WebServlet("/SaveCharacter")
public class SaveCharacter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCharacter() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerCharacter character = new PlayerCharacter();
		character.setName(request.getParameter("chaName"));
		character.setStr(Integer.parseInt(request.getParameter("str")));
		character.setDex(Integer.parseInt(request.getParameter("dex")));
		character.setCon(Integer.parseInt(request.getParameter("con")));
		character.setWiz(Integer.parseInt(request.getParameter("wis")));
		character.setIntel(Integer.parseInt(request.getParameter("int")));
		character.setCha(Integer.parseInt(request.getParameter("cha")));
		character.setCasterLevel(Integer.parseInt(request.getParameter("casterLevel")));
		character.setShowDupes(Boolean.parseBoolean(request.getParameter("dup")));
		
		String classString = request.getParameter("chosenClass");
		String[] classSplit = classString.split(",");
		
		ArrayList<CasterClass> classes = (ArrayList<CasterClass>)request.getSession().getAttribute("classes"+classSplit[1]);
		for(CasterClass c: classes) {
			if(c.getName().equals(classSplit[0])) {
				character.setCasterClass(c);
				break;
			}
		}
		request.getSession().setAttribute("character", character);
		String jspName = "";
		if(character.getCasterClass().getName().equals("Cleric")) {
			jspName = "ChooseDomain.jsp";
		} else {
			jspName = "PickSpells.jsp";
		}
		
		RequestDispatcher rq = request.getRequestDispatcher(jspName);
		rq.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
