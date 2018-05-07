package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.User;
import dao.Country;
import dao.Tours;
import dao.Order;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Facade {

	static Logger logger;

	public Connection getConnect() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/MyTours";
		String username = "root";
		String password = "1111";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public void setOrder(Order orderrecord) throws SQLException {
		Connection connection = getConnect();
		try {
			String textquery = "INSERT INTO `mytours`.`order` ( `idTour`, `coltour`, `iduser`)" + "VALUES (?,?,?);";
			PreparedStatement st = connection.prepareStatement(textquery);
			st.setString(1, orderrecord.getIdTour());
			st.setString(2, orderrecord.getCol());
			st.setString(3, orderrecord.getIdUser());
			st.execute();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void setTour(Tours newtour) throws SQLException {

		Connection connection = getConnect();
		try {
			String textquery = "INSERT INTO `mytours`.`tour` (`name`, `Description`, `idCountry`, `date`, `datefly`, `price`)"
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement st = connection.prepareStatement(textquery);
			st.setString(1, newtour.getName());
			st.setString(2, newtour.getDescription());
			st.setInt(3, Integer.valueOf(newtour.getIdCountry()));
			st.setString(4, newtour.getDate());
			st.setString(5, newtour.getDateFly());
			st.setString(6, newtour.getPrice());
			st.execute();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

	}

	public void setCountry(Country newcountry) throws SQLException {

		Connection connection = getConnect();
		try {
			String textquery = "INSERT INTO `mytours`.`country` (`CountryName`, `CountryNameRu`)" + "VALUES (?,?);";
			PreparedStatement st = connection.prepareStatement(textquery);
			st.setString(1, newcountry.getCountry());
			st.setString(2, newcountry.getCountryRu());

			st.execute();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

	}

	public void setUser(User newuser) throws SQLException {
		Connection connection = getConnect();
		try {

			String textquery = "INSERT INTO `mytours`.`user` (`name`, `family`, `email`, `birthday` , `password`, `role`)"
					+ " VALUES (?,?,?,?,?,?);";
			PreparedStatement st = connection.prepareStatement(textquery);
			st.setString(1, newuser.getName());
			st.setString(2, newuser.getFamily());
			st.setString(3, newuser.getEmail());
			st.setString(4, newuser.getBirthday());
			st.setString(5, newuser.getPassword());
			st.setString(6, "user");

			st.execute();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect two two the database!", e);

		}

	}

	////// getter/////////

	public List<User> getUserTable(String first, String last) throws SQLException {
		List<User> userlist = new ArrayList<>();
		Connection connection = getConnect();

		try {

			String textquery;
			if (first != null & last != null) {
				textquery = "SELECT * FROM mytours.user where email='" + first + "' and password ='" + last + "'";
			} else {

				textquery = " SELECT * FROM mytours.user";
			}

			PreparedStatement st = connection.prepareStatement(textquery);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setFamily(rs.getString("family"));
				user.setIdemail(rs.getString("email"));
				user.setBirthday(rs.getString("birthday"));
				user.setRole(rs.getString("role"));
				user.setIdUser(rs.getString("iduser"));
				userlist.add(user);
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect two the database!", e);
		}

		return userlist;
	}

	public List<Tours> getTourTable(String idcountry, String orderlist, String idTour) throws SQLException {

		List<Tours> tourslist = new ArrayList<>();

		Connection connection = getConnect();
		try {
			String textquery = "select tour.name, tour.Description, tour.idCountry, tour.idtour, tour.date, tour.datefly, tour.price, CountryName from tour\r\n"
					+ "left join country on ( tour.idCountry = country.idCountry)";
			String textquerydop = "";
			String textqueryotbor = "";
			if (orderlist != null) {
				if (orderlist.equals("price")) {
					textqueryotbor = "order by tour.price";
				}
				if (orderlist.equals("name")) {
					textqueryotbor = "order by tour.name";
				}
				if (orderlist.equals("DateFly")) {
					textqueryotbor = "order by tour.datefly";
				}

			}

			if (idcountry != null) {
				textquerydop = " where tour.idCountry = '" + idcountry + "'" + textqueryotbor + ";";
			} else {

				if (idTour != null) {
					textquerydop = " where tour.idtour = '" + idTour + "'" + textqueryotbor + ";";

				} else {
					textquerydop = textqueryotbor + ";";
				}
			}

			textquery = textquery + textquerydop;

			PreparedStatement st = connection.prepareStatement(textquery);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Tours tour = new Tours();
				tour.setName(rs.getString("name"));
				tour.setDescription(rs.getString("Description"));
				tour.setidCountry(rs.getString("idCountry"));
				tour.setDate(rs.getString("date"));
				tour.setDateFly(rs.getString("datefly"));
				tour.setPrice(rs.getString("price"));
				tour.setIdtour(rs.getString("idtour"));
				tour.setNameCountry(rs.getString("CountryName"));
				tourslist.add(tour);

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return tourslist;
	}

	public List<Order> getOrderTable() throws SQLException {

		List<Order> orderlist = new ArrayList<>();

		Connection connection = getConnect();

		try {
			PreparedStatement st = connection.prepareStatement(
					"SELECT orders.idTour, orders.iduser, user.name as nameuser, user.family , tour.name,\r\n"
							+ "tour.Description, tour.date , tour.datefly , orders.coltour,tour.price, country.CountryName FROM mytours.`order` orders\r\n"
							+ "left join user on ( orders.iduser = user.iduser)\r\n"
							+ "left join tour on ( orders.idtour = tour.idtour)\r\n"
							+ "left join country on ( tour.idCountry = country.idCountry)\r\n" + ";");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Order order = new Order();

				order.setNameTour(rs.getString("name"));
				order.setNameCountry(rs.getString("CountryName"));
				order.setDescription(rs.getString("Description"));
				order.setDateTour(rs.getString("date"));
				order.setDateFly(rs.getString("datefly"));
				order.setPrice(rs.getString("price"));
				order.setCol(rs.getString("coltour"));
				order.setFamilyUser(rs.getString("family"));
				order.setNameUser(rs.getString("nameuser"));

				orderlist.add(order);

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return orderlist;
	}

	public List<Country> getCountry() throws SQLException {

		List<Country> CountryList = new ArrayList<>();

		Connection connection = getConnect();
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM country;");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setCountry(rs.getString("CountryName"));
				country.setCountryRu(rs.getString("CountryNameRu"));
				country.setIdCountry(rs.getString("idcountry"));
				CountryList.add(country);

			}
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		// logger.log(Level.FINE,"data read " );
		return CountryList;
	}
}