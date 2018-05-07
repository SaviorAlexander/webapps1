package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import dao.Order;
import l18n.L18;

/**
 * Servlet implementation class AdminOrder
 */
@WebServlet("/AdminOrder.jsp")
public class AdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOrder() {
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

		L18 webname = new L18();
		// langpage = "en";

		String adminOrderTableCol1 = webname.getLabel("adminOrderTableCol1", langpage);// Name";
		String adminOrderTableCol2 = webname.getLabel("adminOrderTableCol2", langpage);// Family";
		String adminOrderTableCol3 = webname.getLabel("adminOrderTableCol3", langpage);// Name Tour";
		String adminOrderTableCol4 = webname.getLabel("adminOrderTableCol4", langpage);// Descriptint";
		String adminOrderTableCol5 = webname.getLabel("adminOrderTableCol5", langpage);// Country";
		String adminOrderTableCol6 = webname.getLabel("adminOrderTableCol6", langpage);// Date Tour";
		String adminOrderTableCol7 = webname.getLabel("adminOrderTableCol7", langpage);// Date Fly";
		String adminOrderTableCol8 = webname.getLabel("adminOrderTableCol8", langpage);// Price";
		String adminOrderTableCol9 = webname.getLabel("adminOrderTableCol9", langpage);// Col";
		String adminOrderTableZagolovok = webname.getLabel("adminOrderTableZagolovok", langpage);// Table order";

		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + "<table border=1>" + "<caption>" + adminOrderTableZagolovok + "</caption>" + "<tr>"
				+ "<th>" + adminOrderTableCol1 + "</th>" + "<th>" + adminOrderTableCol2 + "</th>" + "<th>"
				+ adminOrderTableCol3 + "</th>" + "<th>" + adminOrderTableCol4 + "</th>" + "<th>" + adminOrderTableCol5
				+ "</th>" + "<th>" + adminOrderTableCol6 + "</th>" + "<th>" + adminOrderTableCol7 + "</th>" + "<th>"
				+ adminOrderTableCol8 + "</th>" + "<th>" + adminOrderTableCol9 + "</th>" + "</tr>";
		Facade facade = new Facade();
		try {
			List<Order> listOrder = facade.getOrderTable();
			for (int j = 0; j < listOrder.size(); j++) {
				Order element = listOrder.get(j);
				texthtml = texthtml + "<tr>" + "<td>" + element.getNameUser() + "</td><td>" + element.getFamilyUser()
						+ "</td><td>" + element.getNameCountry() + "</td><td>" + element.getNameCountry() + "</td><td>"
						+ element.getDescription() + "</td><td>" + element.getDateTour() + "</td><td>"
						+ element.getDateFly() + "</td> <td>" + element.getPrice() + "</td> <td>  " + element.getCol()
						+ "</td></tr>";
			}

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
