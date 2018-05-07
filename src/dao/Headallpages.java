package dao;

import l18n.L18;

public class Headallpages {
	public String getheadler(boolean roleadmin, boolean loggeduser, String langpage) {
		final char dm = (char) 34;

		L18 webname = new L18();

		String indexhref1 = webname.getLabel("indexhref1", langpage);
		String indexhref2 = webname.getLabel("indexhref2", langpage);
		String indexhref3 = webname.getLabel("indexhref3", langpage);
		String indexhref4 = webname.getLabel("indexhref4", langpage);
		String indexhref5 = webname.getLabel("indexhref5", langpage);
		String indexSubmitText1 = webname.getLabel("indexSubmitText1", langpage);
		String indexSubmitText2 = webname.getLabel("indexSubmitText2", langpage);
		String indexButton = webname.getLabel("indexButton", langpage);

		String headler = "<!DOCTYPE HTML>\r\n" + "<html>\r\n" + " <head>\r\n" + "  <meta charset=\"utf-8\">\r\n"
				+ "  <title>Тег STYLE</title>\r\n" + "  <style type=\"text/css\">\r\n" + "\r\n" + " body {"
				+ "   background: #c7b39b /\"Sky.jpg\"; " + /* Цвет фона и путь к файлу */

				"  }" +

				" {\r\n" + "border: 1px solid black;\r\n" + "height: 40px;\r\n" + "background:grey ;\r\n" + "\r\n"
				+ "}\r\n" + ".child\r\n" + "{\r\n" + "height: 40px;\r\n" + "display: inline-block;\r\n"
				+ "background: ;\r\n" + "width: 400px;\r\n" + "}\r\n" + "\r\n" + ".child1\r\n" + "{\r\n"
				+ "height: 40px;\r\n" + "display: inline-block;\r\n" + "background: red;\r\n" + "width: 200px\r\n"
				+ ";\r\n" + "}\r\n" + ".child2\r\n" + "{\r\n" + "height: 40px;\r\n" + "display: inline-block;\r\n"
				+ "background:;\r\n" + "width: 200px\r\n" + ";\r\n" + "}\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "  </style>\r\n" + " </head> \r\n" + " <body>\r\n" + "\r\n" + "   \r\n" + "\r\n"
				+ "<div class=\"parent\">\r\n" + "<div class=\"child\">\r\n" + "<a href =\"index.jsp\">" + indexhref1
				+ " </a>\r\n" + "<a href =\"ListTour.jsp\" >" + indexhref2 + " </a>\r\n"
				+ "<a href =\"Cabinet.jsp\" >Cabinet </a>\r\n"
				+ "<a href =\"AdministratorPanel.jsp\" >Administrator Panel </a><a href =\"Contact.jsp\" >Contact </a>\r\n"
				+ "\r\n" + "\r\n" + "\r\n" + "\r\n" + "</div>\r\n" + "<div class=\"child\">\r\n"
				+ "<form class=\"layer2\" action = \"index.jsp\"method = \"GET\">Login <input type = \"text\" name = \"first_name\"required>\r\n"
				+ "<br />Password <input type = \"text\" name = \"last_name\"required /> <input type = \"submit\" value = Submit /> </form>\r\n"
				+ "</div>\r\n" + "<div class=\"child2\">\r\n" + "\r\n" + "\r\n"
				+ "<a href=\"index.jsp? lang=en\" title=\"English\"><img src=\"en.png\" height=25 style=\"padding:0px 2px;border:1px solid black\"></a>\r\n"
				+ "<a href=\"index.jsp? lang=ru\" title=\"Rusian\"><img src=\"ru.png\" height=25 style=\"padding:0px 2px;border:1px solid white\"></a>\r\n"
				+ "\r\n" + "\r\n" + "</div>\r\n" + "</div>\r\n" + "<hr\r\n" + "/hr>";

		return headler;

	}

	public String getEndBody() {
		String endpage = "</body>\r\n" + "</html>";
		return endpage;
	}

}
