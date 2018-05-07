package Servlets;

import dao.Country;
import dao.Facade;
import dao.Tours;
import l18n.L18;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.Headallpages;
import java.util.List;

/**
 * Servlet implementation class ListTour
 */
@WebServlet("/ListTour.jsp")
public class ListTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String idcountry;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListTour() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		final char dm = (char) 34;

		boolean useradmin = false;
		boolean userlogging = false;
		HttpSession se = request.getSession(true);

		String langpage = (String) se.getAttribute("lang");
		String iduser = (String) se.getAttribute("idUser");
		String role = (String) se.getAttribute("role");

		String orderList = null;
		String idCountry1 = request.getQueryString();
		System.out.println("1-" + idCountry1);

		if (idCountry1 != null) {
			if (idCountry1.contains("country=")) {
				System.out.println("2-" + idCountry1);

				idcountry = idCountry1.substring(8, idCountry1.length());
				System.out.println("3-" + idCountry1);
			} else {

				if (idCountry1.equals("button1=All+tours")) {
					idcountry = null;
				}
				;
				if (idCountry1.equals("button2=sort+by+price")) {
					orderList = "price";
				}
				;
				if (idCountry1.equals("button3=sort+by+name+tour")) {
					orderList = "name";
				}
				;
				if (idCountry1.equals("button4=sort+by+date+fly")) {
					orderList = "DateFly";
				}
				;

			}
		}
		;

		L18 webname = new L18();
		// langpage = "en";

		String listTourTableCaption = webname.getLabel("listTourTableCaption", langpage);
		String listTourTableCol1 = webname.getLabel("listTourTableCol1", langpage);
		String listTourTableCol2 = webname.getLabel("listTourTableCol2", langpage);
		String listTourTableCol3 = webname.getLabel("listTourTableCol3", langpage);
		String listTourTableCol4 = webname.getLabel("listTourTableCol4", langpage);
		String listTourTableCol5 = webname.getLabel("listTourTableCol5", langpage);
		String listTourTableCol6 = webname.getLabel("listTourTableCol6", langpage);
		String listTourButton = webname.getLabel("listTourButton", langpage);
		String listTourSelectCountry = webname.getLabel("listTourSelectCountry", langpage);

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

		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + "  <form action=\"ListTour.jsp\" method=\"get\">\r\n"
				+ "   <p><select size=\"7\"  name=\"country\">\r\n" + "    <option disabled>" + listTourSelectCountry
				+ "</option>\r\n";
		Facade facade = new Facade();
		try {
			List<Country> listCountry = facade.getCountry();
			for (int j = 0; j < listCountry.size(); j++) {
				Country element = listCountry.get(j);
				texthtml = texthtml + "<option value=" + element.getIdCountry() + ">" + element.getCountry()
						+ "</option>\r\n";
			}
			texthtml = texthtml + "   </select></p>\r\n" + "   <p><input type=\"submit\" value=" + listTourButton
					+ "></p>\r\n" + "  </form>\r\n" + "\r\n" + " </body>\r\n" + "</html>";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		;
		texthtml = texthtml + "<form action=\"ListTour.jsp\"method=\"get\">"
				+ "<input type=\"submit\" name=\"button1\"value=\"All tours\" />"
				+ "<input type=\"submit\" name=\"button2\"value=\"sort by price\" />"
				+ "<input type=\"submit\" name=\"button3\" value=\"sort by name tour\" />"
				+ "<input type=\"submit\" name=\"button4\" value=\"sort by date fly\" />" + "</form>";

		;
		texthtml = texthtml + "<table border=1>" + "<caption>" + listTourTableCaption + "</caption>" + "<tr><th>"
				+ listTourTableCol1 + "</th>" + "<th>" + listTourTableCol2 + "</th>" + "<th>" + listTourTableCol3
				+ "</th>" + "<th>" + listTourTableCol4 + "</th>" + "<th>" + listTourTableCol5 + "</th>" + "<th>"
				+ listTourTableCol6 + "</th>" + "</tr>";
		try {
			List<Tours> listTours = facade.getTourTable(idcountry, orderList, null);

			for (int j = 0; j < listTours.size(); j++) {

				Tours element = listTours.get(j);
				String hreftext = "<a href=" + dm + "BuyTour.jsp" + "? choiceId =" + element.getId() + dm + "text" + dm
						+ "  >" + element.getName();
				texthtml = texthtml + "<tr><td>" + element.getNameCountry() + "</td><td>" + hreftext + "</td><td>"
						+ element.getDescription() + "</td><td>" + element.getDate() + "</td><td>"
						+ element.getDateFly() + "</td><td>" + element.getPrice() + "</tr>";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		;

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
		idcountry = (String) request.getAttribute("country");
		doGet(request, response);

	}

}
