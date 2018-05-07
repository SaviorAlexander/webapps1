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

import dao.Facade;
import dao.Headallpages;
import l18n.L18;
import dao.Country;

/**
 * Servlet implementation class CountryEdit
 */
@WebServlet("/CountryEdit.jsp")
public class CountryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountryEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		final char dm = (char) 34;
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
		// lang
		L18 webname = new L18();

		// langpage = "en";

		String countryEditTableZagolovok = webname.getLabel("countryEditTableZagolovok", langpage);
		String countryEditTableCol1 = webname.getLabel("countryEditTableCol1", langpage);
		String countryEditButton = webname.getLabel("countryEditButton", langpage);

		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		String countryname = request.getParameter("country");
		String countrynamerus = request.getParameter("countryrus");

		Facade facade = new Facade();
		Country countryrecord = new Country();
		if (countryname != null) {
			countryrecord.setCountry(countryname);
			countryrecord.setCountryRu(countrynamerus);
			try {
				facade.setCountry(countryrecord);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		texthtml = texthtml + "<table border=1>" + "<caption>" + countryEditTableZagolovok + "</caption>" + "<tr><th>"
				+ countryEditTableCol1 + "</th>" + "</tr>";
		try {
			List<Country> listOrder = facade.getCountry();
			for (int j = 0; j < listOrder.size(); j++) {
				Country element = listOrder.get(j);
				texthtml = texthtml + "<tr><td>" + element.getCountry() + "</td></tr>";

			}
			texthtml = texthtml + "</table>   <form action = \"CountryEdit.jsp\" method = \"GET\">\r\n"
					+ " Country Name: <input type = \"text\" name = \"country\" required >\r\n" + " <br />\r\n"
					+ "<input type = \"submit\" value = " + countryEditButton + "></form>";
			texthtml = texthtml + headpage.getEndBody();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		;
		out.println(texthtml);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
