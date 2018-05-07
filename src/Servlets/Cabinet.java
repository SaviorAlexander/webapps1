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
import dao.Order;
import l18n.L18;

/**
 * Servlet implementation class Cabinet
 */
@WebServlet("/Cabinet.jsp")
public class Cabinet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cabinet() {
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
		String cabinetTableCol1 = webname.getLabel("cabinetTableCol1", langpage);//
		String cabinetTableCol2 = webname.getLabel("cabinetTableCol2", langpage);//
		String cabinetTableCol3 = webname.getLabel("cabinetTableCol3", langpage);//
		String cabinetTableCol4 = webname.getLabel("cabinetTableCol4", langpage);//
		String cabinetTableCol5 = webname.getLabel("cabinetTableCol5", langpage);//
		String cabinetTableCol6 = webname.getLabel("cabinetTableCol6", langpage);//
		String cabinetTableCol7 = webname.getLabel("cabinetTableCol7", langpage);//
		String cabinetTableZagolovok = webname.getLabel("cabinetTableZagolovok", langpage);//

		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + "<table border=1>" + "<caption>" + cabinetTableZagolovok + "</caption>" + "<tr>" + "<th>"
				+ cabinetTableCol1 + "</th>" + "<th>" + cabinetTableCol2 + "</th>" + "<th>" + cabinetTableCol3 + "</th>"
				+ "<th>" + cabinetTableCol4 + "</th>" + "<th>" + cabinetTableCol5 + "</th>" + "<th>" + cabinetTableCol6
				+ "</th>" + "<th>" + cabinetTableCol7 + "</th>" + "</tr>";
		Facade facade = new Facade();
		try {
			List<Order> listOrder = facade.getOrderTable();
			for (int j = 0; j < listOrder.size(); j++) {
				Order element = listOrder.get(j);
				texthtml = texthtml + "<tr><td>" + element.getNameCountry() + "</td><td>" + element.getNameCountry()
						+ "</td><td>" + element.getDescription() + "</td><td>" + element.getDateTour() + "</td><td>"
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

		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
