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

import dao.Tours;
import l18n.L18;
import dao.Facade;
import dao.Headallpages;
import dao.Order;

/**
 * Servlet implementation class BuyTour
 */
@WebServlet("/BuyTour.jsp")
public class BuyTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String idCountry;
	private boolean buy;
	// private static final String idfinal;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyTour() {
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

		Headallpages headpage = new Headallpages();
		// langpage="en";
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		L18 webname = new L18();
		String buyTableCol1 = webname.getLabel("buyTableCol1", langpage); // "Name Country";
		String buyTableCol2 = webname.getLabel("buyTableCol2", langpage);// "Name Tour";
		String buyTableCol3 = webname.getLabel("buyTableCol3", langpage);// Description
		String buyTableCol4 = webname.getLabel("buyTableCol4", langpage);// Date Tour
		String buyTableCol5 = webname.getLabel("buyTableCol5", langpage);// Date Fly
		String buyTableCol6 = webname.getLabel("buyTableCol6", langpage);// Price
		String buyTableCaption = webname.getLabel("buyTableCaption", langpage);// Caption
		String buyButton = webname.getLabel("buyButton", langpage);// Caption

		String idtour = request.getParameter("idtour");
		String idInput = request.getQueryString();
		if (idInput.contains("button1=Done")) {
			buy = true;
		} else {
			idCountry = idInput.replace("%20choiceId%20=", "");
			idCountry = idCountry.replace("type", "");
			buy = false;
		}

		if (buy) {
			iduser = null;
			if (iduser != null) {

				Order neworder = new Order();
				neworder.setCol("1");
				neworder.setIdTour(idCountry);
				neworder.setIdUser(iduser);
				Facade facade = new Facade();
				try {
					facade.setOrder(neworder);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				request.getRequestDispatcher("Registration.jsp").forward(request, response);

			}
		}

		String name = request.getParameter("res");
		texthtml = texthtml +

				" </body>\r\n" + "</html>";
		texthtml = texthtml + "<table border=1>" + "<caption>" + buyTableCaption + "</caption>" + "<tr><th>"
				+ buyTableCol1 + "</th>" + "<th>" + buyTableCol2 + "</th>" + "<th>" + buyTableCol3 + "</th>" + "<th>"
				+ buyTableCol4 + "</th>" + "<th>" + buyTableCol5 + "</th>" + "<th>" + buyTableCol6 + "</th>" + "</tr>";
		Facade facade = new Facade();
		try {
			String orderlist = null;
			List<Tours> listTours = facade.getTourTable(null, orderlist, idCountry);
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

		texthtml = texthtml + "<form action=\"BuyTour.jsp\"method=\"get\">"
				+ "<input type=\"submit\" name=\"button1\"value=\"Done\" />" + "</form>";
		PrintWriter out = response.getWriter();
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
