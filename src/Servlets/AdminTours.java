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
import dao.Tours;
import l18n.L18;
import dao.Headallpages;

/**
 * Servlet implementation class AdminTours
 */
@WebServlet("/AdminTours.jsp")
public class AdminTours extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminTours() {
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
		boolean userlogging = false;
		boolean useradmin = false;
		HttpSession se = request.getSession(true);
		String langpage = (String) se.getAttribute("lang");
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
		// lang
		L18 webname = new L18();
		// langpage = "en";

		String adminToursTableCol1 = webname.getLabel("adminToursTableCol1", langpage);// Name Country
		String adminToursTableCol2 = webname.getLabel("adminToursTableCol2", langpage);// Name Tour
		String adminToursTableCol3 = webname.getLabel("adminToursTableCol3", langpage);// Description
		String adminToursTableCol4 = webname.getLabel("adminToursTableCol4", langpage);// Date Tour
		String adminToursTableCol5 = webname.getLabel("adminToursTableCol5", langpage);// Date Fly
		String adminToursTableCol6 = webname.getLabel("adminToursTableCol6", langpage);// Price
		String adminToursTableCaption = webname.getLabel("adminToursTableCaption", langpage);
		String adminToursCaption1 = webname.getLabel("adminToursCaption1", langpage);
		String adminToursCaption2 = webname.getLabel("adminToursCaption2", langpage);
		String adminToursCaption3 = webname.getLabel("adminToursCaption3", langpage);
		String adminToursCaption4 = webname.getLabel("adminToursCaption4", langpage);
		String adminToursCaption5 = webname.getLabel("adminToursCaption5", langpage);
		String adminToursCaption6 = webname.getLabel("adminToursCaption6", langpage);

		String idcountry = request.getParameter("country");
		String tourname = request.getParameter("tourname");
		String tourdescription = request.getParameter("tourdescription");
		String datetour = request.getParameter("datetour");
		String datefly = request.getParameter("datefly");
		String price = request.getParameter("price");

		Facade facade = new Facade();

		if (idcountry != null & tourname != null) {
			Tours countryrecord = new Tours();
			countryrecord.setName(tourname);
			countryrecord.setidCountry(idcountry);
			countryrecord.setDescription(tourdescription);
			countryrecord.setDate(datetour);
			countryrecord.setDateFly(datefly);
			countryrecord.setPrice(price);

			try {
				facade.setTour(countryrecord);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + "</table>   <form action = \"AdminTours.jsp\" method = \"GET\">\r\n";

		texthtml = texthtml + " <p><select required size=\"7\"  name=\"country\">\r\n\" + \r\n"
				+ "			   <option disabled>Select Country</option>\r\n\";";
		try {
			List<Country> listCountry = facade.getCountry();
			for (int j = 0; j < listCountry.size(); j++) {
				Country element = listCountry.get(j);
				texthtml = texthtml + "<option value=" + element.getIdCountry() + ">" + element.getCountry()
						+ "</option>\r\n";

			}
			texthtml = texthtml + "   </select ></p>\r\n" + "   <p><input type=\"submit\" value=\"Done\"></p>\r\n"
					+ adminToursCaption2 + " <input type = \"text\" name = \"tourname\"  required>\r\n"
					+ adminToursCaption3 + " <input type = \"text\" name = \"tourdescription\" required /> \r\n"
					+ adminToursCaption4 + " <input type = \"date\" name = \"datetour\" required /> \r\n"
					+ adminToursCaption5 + " <input type = \"date\" name = \"datefly\" required /> \r\n"
					+ adminToursCaption6 + "<input type = \"text\" name = \"price\" required/> \r\n" + "  </form>\r\n"
					+ "\r\n" + " </body>\r\n" + "</html>";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		;
		texthtml = texthtml + " <br />\r\n" + " </body>\r\n" + "</html>";
		texthtml = texthtml + "<table border=1>" + "<caption>Table user</caption>" + "<tr><th>" + adminToursTableCol1
				+ "</th>" + "<th>" + adminToursTableCol2 + "</th>" + "<th>" + adminToursTableCol3 + "</th>" + "<th>"
				+ adminToursTableCol4 + "</th>" + "<th>" + adminToursTableCol5 + "</th>" + "<th>" + adminToursTableCol6
				+ "</th>" + "</tr>";
		try {
			String idcountrys = null;
			String idorder = null;
			String idtour = null;

			List<Tours> listTours = facade.getTourTable(idcountrys, idorder, idtour);
			for (int j = 0; j < listTours.size(); j++) {
				Tours element = listTours.get(j);
				texthtml = texthtml + "<tr><td>" + element.getNameCountry() + "</td><td>" + element.getName()
						+ "</td><td>" + element.getDescription() + "</td><td>" + element.getDate() + "</td><td>"
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
		doGet(request, response);
	}

}
