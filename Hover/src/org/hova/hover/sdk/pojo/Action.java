package org.hova.hover.sdk.pojo;

public class Action {

	private String executed_by;
	private String linked_to;
	private String action;
	private String referenced_by;
	private String date;
	private String time;
	private Webhook webhook;

	public String getExecuted_by() {
		return executed_by;
	}

	public void setExecuted_by(String executed_by) {
		this.executed_by = executed_by;
	}

	public String getLinked_to() {
		return linked_to;
	}

	public void setLinked_to(String linked_to) {
		this.linked_to = linked_to;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReferenced_by() {
		return referenced_by;
	}

	public void setReferenced_by(String referenced_by) {
		this.referenced_by = referenced_by;
	}

	public Webhook getWebhook() {
		return this.webhook;
	}

	public void setWebhook(Webhook webhook) {
		this.webhook = webhook;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
