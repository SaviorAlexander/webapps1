package dao;

public class Tours {

	private String idtour;
	private String name;
	private String description;
	private String idCountry;
	private String nameCountry;
	private String date;
	private String datefly;
	private String price;

	public void setIdtour(String name) {
		this.idtour = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public void setidCountry(String name) {
		this.idCountry = name;
	}

	public void setNameCountry(String name) {
		this.nameCountry = name;
	}

	public void setDate(String name) {
		this.date = name;
	}

	public void setDateFly(String name) {
		this.datefly = name;
	}

	public void setPrice(String name) {
		this.price = name;
	}

	////////

	public String getId() {
		return this.idtour;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

	public String getNameCountry() {
		return this.nameCountry;
	}

	public String getDate() {
		return this.date;
	}

	public String getDateFly() {
		return this.datefly;
	}

	public String getPrice() {
		return this.price;
	}

	public void pullTest(String name, String name1, String name2, String name3, String name4, String name5,
			String name6, String name7) {

		setIdtour(name);
		setName(name1);
		setDescription(name2);
		setidCountry(name3);
		setNameCountry(name4);
		setDate(name5);
		setDateFly(name6);
		setPrice(name7);
	}

}
