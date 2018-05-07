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

import dao.Facade;
import dao.Headallpages;
import dao.User;
import l18n.L18;

/**
 * Servlet implementation class AdminUser
 */
@WebServlet("/AdminUser.jsp")
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUser() {
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
		String adminUserTableCol1 = webname.getLabel("adminUserTableCol1", langpage);
		String adminUserTableCol2 = webname.getLabel("adminUserTableCol2", langpage);
		String adminUserTableCol3 = webname.getLabel("adminUserTableCol3", langpage);
		String adminUserTableCol4 = webname.getLabel("adminUserTableCol4", langpage);
		String adminUserTableCol5 = webname.getLabel("adminUserTableCol5", langpage);
		String adminUserTableZagolovok = webname.getLabel("adminUserTableZagolovok", langpage);

		Headallpages headpage = new Headallpages();
		String texthtml = headpage.getheadler(userlogging, useradmin, langpage);

		texthtml = texthtml + "<table border=1>" + "<caption>" + adminUserTableZagolovok + "</caption>" + "<tr>"
				+ "<th>" + adminUserTableCol1 + "</th>" + "<th>" + adminUserTableCol2 + "</th>" + "<th>"
				+ adminUserTableCol3 + "</th>" + "<th>" + adminUserTableCol4 + "</th>" + "<th>" + adminUserTableCol5
				+ "</th>" + "</tr>";
		Facade facade = new Facade();

		try {
			String userfortable = null;
			String usermailfortable = null;
			List<User> listOrder = facade.getUserTable(userfortable, usermailfortable);
			for (int j = 0; j < listOrder.size(); j++) {
				User element = listOrder.get(j);
				texthtml = texthtml + "<tr><td>" + element.getName() + "</td><td>" + element.getFamily() + "</td><td>"
						+ element.getEmail() + "</td><td>" + element.getBirthday() + "</td><td>" + element.getRole()
						+ "</td></tr>";

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
