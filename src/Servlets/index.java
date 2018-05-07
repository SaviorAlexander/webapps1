package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.Cryptos;
import dao.Headallpages;
import dao.User;
import dao.Facade;

import javax.crypto.spec.SecretKeySpec;

/**
 * Servlet implementation class index
 */
@WebServlet("/index.jsp")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name;

	public index() {
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
		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String idlang = request.getQueryString();
		String hash = null;
		boolean useradmin = false;
		boolean userlogging = false;

		HttpSession se = request.getSession(true);

		String langpage = (String) se.getAttribute("lang");
		if (langpage != null & idlang != null) {
		} else {
			se.setAttribute("lang", "en");
		}

		if (idlang != null) {
			if (idlang.equals("%20lang=ru")) {
				se.setAttribute("lang", "ru");
			} else {
				se.setAttribute("lang", "en");
			}
		}

		boolean validateEmail = true;
		if (firstname != null & lastname != null) {
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(firstname);
			if (m.matches()) {
				validateEmail = true;
				;
			} else {
				validateEmail = false;
				;
			}
		}

		if (validateEmail & (firstname != null & lastname != null)) {
			Cryptos crypt = new Cryptos();
			try {
				hash = crypt.byteArrayToHexString(crypt.computeHash(lastname));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//////

		String idUser;
		String role;
		Facade facade = new Facade();
		try {
			List<User> listOrder = facade.getUserTable(firstname, hash);
			for (int j = 0; j < listOrder.size(); j++) {
				User element = listOrder.get(j);
				idUser = element.getId();
				role = element.getRole();
				se.setAttribute("idUser", idUser);
				se.setAttribute("role", role);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		;

		///////
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

		htmltext = htmltext + headpage.getEndBody();
		out.println(htmltext);
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
