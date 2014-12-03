package org.hova.hover.sdk.pojo;

import java.util.ArrayList;

public class Merge {

	/** branch id of the main user */
	private String branch_id;
	/** id of the main user in the merge */
	private String main_user_id;
	/** +1 array with the id of the users to join */
	private ArrayList<String> users_id;
	/** The new main user of the merge group */
	private String new_main_user_id;
	
	
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getMain_user_id() {
		return main_user_id;
	}
	public void setMain_user_id(String main_user_id) {
		this.main_user_id = main_user_id;
	}
	public ArrayList<String> getUsers_id() {
		return users_id;
	}
	public void setUsers_id(ArrayList<String> users_id) {
		this.users_id = users_id;
	}
	public String getNew_main_user_id() {
		return new_main_user_id;
	}
	public void setNew_main_user_id(String new_main_user_id) {
		this.new_main_user_id = new_main_user_id;
	}
	
}

