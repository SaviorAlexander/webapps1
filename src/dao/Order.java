package dao;

public class Order {

	private String idorder;
	private String idcountry;
	private String iduser;
	private String idtour;
	private String namecountry;
	private String nametour;
	private String nameuser;
	private String familyuser;
	private String col;
	private String description;
	private String datetour;
	private String datefly;
	private String price;

	public void setIdTour(String name) {
		this.idtour = name;
	}

	public void setIdOrder(String name) {
		this.idorder = name;
	}

	public void setIdCountry(String name) {
		this.idcountry = name;
	}

	public void setIdUser(String name) {
		this.iduser = name;
	}

	public void setNameCountry(String name) {
		this.namecountry = name;
	}

	public void setNameUser(String name) {
		this.nameuser = name;
	}

	public void setNameTour(String name) {
		this.nametour = name;
	}

	public void setFamilyUser(String name) {
		this.familyuser = name;
	}

	public void setCol(String name) {
		this.col = name;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public void setDateTour(String name) {
		this.datetour = name;
	}

	public void setDateFly(String name) {
		this.datefly = name;
	}

	public void setPrice(String name) {
		this.price = name;
	}

	////////

	public String getIdTour() {
		return this.idtour;
	}

	public String getDescription() {
		return this.description;
	}

	public String getId() {
		return this.idorder;
	}

	public String getIdCountry() {
		return this.idcountry;
	}

	public String getIdUser() {
		return this.iduser;
	}

	public String getNameCountry() {
		return this.namecountry;
	}

	public String getNameUser() {
		return this.nameuser;
	}

	public String getNameTour() {
		return this.nametour;
	}

	public String getFamilyUser() {
		return this.familyuser;
	}

	public String getCol() {
		return this.col;
	}

	public String getDateTour() {
		return this.datetour;
	}

	public String getPrice() {
		return this.price;
	}

	public String getDateFly() {
		return this.datefly;
	}

}