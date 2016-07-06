package org.hova.hover.sdk.pojo;

public class LoginChangePassword {
	private String old_password;
	private String new_password;
	private String user_id;

	public String getOld_password() {
		return this.old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password() {
		return this.new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getUser_id() {
		return this.user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
