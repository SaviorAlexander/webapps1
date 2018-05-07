package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User;
import l18n.L18;
import dao.Facade;
import dao.Headallpages;
import dao.Cryptos;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration.jsp")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
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
		String hash;

		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String email = request.getParameter("e_mail");
		String password = request.getParameter("Password");
		String password2 = request.getParameter("Password2");
		String birthday = request.getParameter("Birthday");

		if (firstname != null & lastname != null) {

			Facade facade = new Facade();
			User userrecord = new User();
			userrecord.setName(firstname);
			userrecord.setFamily(lastname);
			Cryptos crypt = new Cryptos();
			try {
				hash = crypt.byteArrayToHexString(crypt.computeHash(lastname));
				userrecord.setPassword(hash);

			} catch (Exception e) {
				e.printStackTrace();
			}

			userrecord.setIdemail(email);
			userrecord.setBirthday(birthday);

			try {
				facade.setUser(userrecord);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		;

		boolean useradmin = false;
		boolean userlogging = false;
		HttpSession se = request.getSession(true);
		String langpage = (String) se.getAttribute("lang");

		// lang
		L18 webname = new L18();
		// langpage = "en";

		String regeistationÑaption1 = webname.getLabel("regeistationÑaption1", langpage);// First Name
		String regeistationÑaption2 = webname.getLabel("regeistationÑaption2", langpage);// Last Name:
		String regeistationÑaption3 = webname.getLabel("regeistationÑaption3", langpage);// E-mail:
		String regeistationÑaption4 = webname.getLabel("regeistationÑaption4", langpage);// Birthday
		String regeistationÑaption5 = webname.getLabel("regeistationÑaption5", langpage);// Password:
		String regeistationÑaption6 = webname.getLabel("regeistationÑaption6", langpage);// Password2:
		String regeistationButton = webname.getLabel("regeistationButton", langpage);// Password2:

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
		String htmltext = headpage.getheadler(userlogging, useradmin, langpage);

		htmltext = htmltext + "  <form action = \"Registration.jsp\" method = \"GET\">\r\n" + regeistationÑaption1
				+ " <input type = \"text\" name = \"first_name\" required >\r\n" + "         <br />\r\n"
				+ regeistationÑaption2 + "<input type = \"text\" name = \"last_name\" required  />\r\n"
				+ "          <br />\r\n" + regeistationÑaption3
				+ "<input type = \"text\" name = \"e_mail\" required />\r\n" + "          <br />\r\n"
				+ regeistationÑaption4 + "<input type = \"date\" name = \"Birthday\" required  />\r\n"
				+ regeistationÑaption5 + " <input type = \"text\" name = \"Password\" required />\r\n"
				+ regeistationÑaption6 + " <input type = \"text\" name = \"Password2\" required />\r\n"
				+ "         <input type = \"submit\" value = " + regeistationButton + " >\r\n" + "      </form>\r\n";
		htmltext = htmltext + headpage.getEndBody();
		out.println(htmltext);
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
