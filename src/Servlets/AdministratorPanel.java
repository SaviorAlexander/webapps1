package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Country;
import dao.Facade;
import dao.Headallpages;
import dao.Tours;
import l18n.L18;

/**
 * Servlet implementation class AdministratorPanel
 */
@WebServlet("/AdministratorPanel.jsp")
public class AdministratorPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean useradmin = false;
		boolean userlogging = false;
		HttpSession se = request.getSession(true);

		String langpage = (String) se.getAttribute("lang");
		String iduser = (String) se.getAttribute("idUser");
		String role = (String) se.getAttribute("role");
		String usaeradministrator = (String) se.getAttribute("role");

		if (usaeradministrator != null) {
			if (usaeradministrator.equals("admin")) {
				useradmin = true;
			}
		}

		String userlog = (String) se.getAttribute("idUser");
		if (userlog != null) {
			userlogging = true;
		}

		PrintWriter out = response.getWriter();
		final char dm = (char) 34;
		// lang
		L18 webname = new L18();
		// langpage = "en";
		String adminPanelZagolovok = webname.getLabel("adminPanelZagolovok", langpage);
		String adminPanelCountry = webname.getLabel("adminPanelCountry", langpage);
		String adminPanelTours = webname.getLabel("adminPanelTours", langpage);
		String adminPanelUser = webname.getLabel("adminPanelUser", langpage);
		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + adminPanelZagolovok;
		texthtml = texthtml + "<a href = " + dm + "CountryEdit.jsp" + dm + " >" + adminPanelCountry + " </a>"
				+ "<a href = " + dm + "AdminTours.jsp" + dm + " >" + adminPanelTours + "</a>" + "<a href = " + dm
				+ "AdminUser.jsp" + dm + " >" + adminPanelUser + "  </a>" + "<a href = " + dm + "AdminOrder.jsp";

		texthtml = texthtml + headpage.getEndBody();

		out.println(texthtml);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
