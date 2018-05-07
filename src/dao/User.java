package dao;

import java.util.Date;

public class User {

	private String iduser;
	private String name;
	private String family;
	private String birthday;
	private String email;
	private String password;
	private String role;

	public void setIdUser(String name) {
		this.iduser = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFamily(String name) {
		this.family = name;
	}

	public void setBirthday(String name) {
		this.birthday = name;
	}

	public void setIdemail(String name) {
		this.email = name;
	}

	public void setPassword(String name) {
		this.password = name;
	}

	public void setRole(String name) {
		this.role = name;
	}
	////////

	public String getId() {
		return this.iduser;
	}

	public String getName() {
		return this.name;
	}

	public String getFamily() {
		return this.family;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getRole() {
		return this.role;
	}

	public void pullTest(String name, String name1, String name2, String name3, String name4, String name5,
			String name6) {

		setIdUser(name);
		setName(name1);
		setFamily(name2);
		setBirthday(name3);
		setIdemail(name4);
		setPassword(name5);
		setRole(name6);
	}

}
