package org.hova.hover.sdk.notifications;

public class Notification {

	private String title;
	private String message;
	private String broot;
	private String branch_id;
	private String notification_id;
	private Boolean read;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBroot() {
		return broot;
	}
	public void setBroot(String broot) {
		this.broot = broot;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}

	
	
}
