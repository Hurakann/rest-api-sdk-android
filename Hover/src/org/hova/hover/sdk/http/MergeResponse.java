package org.hova.hover.sdk.http;

public class MergeResponse {
	
	/** id of the user */
	private String user_id;
	/** full user name */
	private String name;
	/** Merge parameter, can be main, merge or free */
	private String merge;
	
	
	
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
	public String getMerge() {
		return merge;
	}
	public void setMerge(String merge) {
		this.merge = merge;
	}
	
}
