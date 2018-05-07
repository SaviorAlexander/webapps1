package dao;

public class Country {

	private String countryName;
	private String countryNameRu;
	private String idCountry;

	public void setCountry(String name) {
		this.countryName = name;
	}

	public void setCountryRu(String name) {
		this.countryNameRu = name;
	}

	public void setIdCountry(String name) {
		this.idCountry = name;
	}

	////////
	public String getCountry() {
		return this.countryName;
	}

	public String getCountryRu() {
		return this.countryNameRu;
	}

	public String getIdCountry() {
		return this.idCountry;
	}

}
