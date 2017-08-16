package com.sandstorm.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sandstorm.model.Domain;
import com.sandstorm.model.PlayerCharacter;

/**
 * Servlet implementation class SaveDomain
 */
@WebServlet("/SaveDomain")
public class SaveDomain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveDomain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkedDomain = request.getParameterValues("domain");
		ArrayList<Domain> domains = (ArrayList<Domain>)request.getSession().getAttribute("domains");
		ArrayList<Domain> chosenDomains = new ArrayList<Domain>();
		Domain tempDomain = null;
		int i = 0;
		Collections.sort(domains);
		for(String s: checkedDomain) {
			tempDomain = new Domain();
			tempDomain.setName(s);
			i = Collections.binarySearch(domains, tempDomain);
			if(i>-1) {
				chosenDomains.add(domains.get(i));
			}			
		}
		PlayerCharacter pc = (PlayerCharacter) request.getSession().getAttribute("character");
		pc.setDomains(chosenDomains);
				
		request.getSession().setAttribute("character", pc);
		RequestDispatcher rq = request.getRequestDispatcher("PickSpells.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
