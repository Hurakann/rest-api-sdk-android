package org.hova.hover.sdk.notifications;

import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.Response;

import com.google.gson.Gson;


public class NotificationResource implements getRequestExectue, PostRequestExectue {
	
	ResourceNotification resource_notification;
	private String URI="/user/notifications";
	private static String CTYPE= "application/json;";
	private int method=0;
	
	public interface ResourceNotification{
		void onGetNotificationList(Response result);
		void onCreateNotification(Response result);
		void onGetNotification(Response result);
	}
	
	public NotificationResource(ResourceNotification rn) {
		resource_notification=rn;
	}
	
	public void getNotificationList(String branch_id){
		method=1;
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,"?branch_id="+branch_id,CTYPE);
	}
	
	public void getSpecificNotification(String branch_id, String notification_id){
		method=2;
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,"?branch_id="+branch_id+"&notification_id="+notification_id,CTYPE);
	}
	
	public void createNotification(Notification notify){
		Gson gson=new Gson();
		ClientPOSTAsync req=new ClientPOSTAsync(this);
		req.execute(URI,gson.toJson(notify),CTYPE);
	}

	@Override
	public void doPostExecute(Response result) {
		// TODO Auto-generated method stub
		resource_notification.onCreateNotification(result);
	}

	@Override
	public void doGetExecute(Response result) {
		// TODO Auto-generated method stub
		
		switch (method) {
		case 1:{
			resource_notification.onGetNotificationList(result);
			break;
		}
		case 2:{
			resource_notification.onGetNotification(result);
			break;
		}
		default:
			break;
		}
		
	}
	
	
}
