package org.hova.hover.sdk.user.login;

public class LoginResponse {

	private String user_id;
	private String name;
	private String lastname;
	private String lastname2;
	private String language;
	private Branches[] branches;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname2() {
		return lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Branches[] getBranches() {
		return branches;
	}

	public void setBranches(Branches[] branches) {
		this.branches = branches;
	}

}
