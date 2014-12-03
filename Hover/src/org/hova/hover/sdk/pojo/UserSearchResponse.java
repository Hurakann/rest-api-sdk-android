package org.hova.hover.sdk.pojo;


public class UserSearchResponse {

	private Object[] users;
	private int total_results;
	private int pages;
	
	
	public int getTotal_results() {
		return total_results;
	}
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public Object[] getUsers() {
		return users;
	}
	public void setUsers(Object[] users) {
		this.users = users;
	}
	
	
}
