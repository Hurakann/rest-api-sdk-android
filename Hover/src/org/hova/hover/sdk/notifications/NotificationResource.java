package org.hova.hover.sdk.notifications;

import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.Response;

import com.google.gson.Gson;

public class NotificationResource implements getRequestExectue, PostRequestExectue {

	ResourceNotification resource_notification;
	private String URI = "/user/notifications";
	private static String CTYPE = "application/json;";
	private int method = 0;
	private ClientPOSTAsync cPOSTAsync;
	private ClientGETAsync cGETAsync;

	public interface ResourceNotification {
		void onGetNotificationList(Response response);

		void onCreateNotification(Response response);

		void onGetNotification(Response response);
	}

	public NotificationResource(ResourceNotification rn) {
		resource_notification = rn;
	}

	public void getNotificationList(String branch_id) {
		method = 1;
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, "?branch_id=" + branch_id, CTYPE);
	}

	public void getSpecificNotification(String branch_id, String notification_id) {
		method = 2;
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, "?branch_id=" + branch_id + "&notification_id="
				+ notification_id, CTYPE);
	}

	public void createNotification(Notification notify) {
		Gson gson = new Gson();
		cPOSTAsync = new ClientPOSTAsync(this);
		cPOSTAsync.execute(URI, gson.toJson(notify), CTYPE);
	}

	@Override
	public void doPostExecute(Response response) {
		resource_notification.onCreateNotification(response);
	}

	@Override
	public void doGetExecute(Response response) {

		switch (method) {
		case 1: {
			resource_notification.onGetNotificationList(response);
			break;
		}
		case 2: {
			resource_notification.onGetNotification(response);
			break;
		}
		default:
			break;
		}

	}

	public void cancelRequest() {
		if (cPOSTAsync != null) {
			cPOSTAsync.cancelRequest();
			cPOSTAsync.cancel(true);
		} else if (cGETAsync != null) {
			cGETAsync.cancelRequest();
			cGETAsync.cancel(true);
		} else {
		}
	}
}
