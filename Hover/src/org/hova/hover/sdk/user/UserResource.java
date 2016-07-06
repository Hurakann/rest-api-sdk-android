package org.hova.hover.sdk.user;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.ClientPUTAsync;
import org.hova.hover.sdk.http.ClientPUTAsync.PutRequestExectue;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.Action;
import org.hova.hover.sdk.pojo.User;
import com.google.gson.Gson;


public class UserResource implements PostRequestExectue, getRequestExectue, PutRequestExectue {

	UserResourceMethods user_resource_methods;
	private static String URI = "/user";
	private static String CTYPE= "application/json;";
	private static String type;	
	private ClientPOSTAsync cPOSTAsync;
	private ClientPUTAsync cPUTAsync;
	private ClientGETAsync cGETAsync;
	
	public interface UserResourceMethods{
		void onCreateUser(Response response);
		void onGetUser(Response response);
		void onUpdateUser(Response response);
		void onActionUser(Response response);
		void onSendPhoneVerification(Response response);
		void onMetricUser(Response response);
	}
	
	public UserResource(UserResourceMethods urm) {
		user_resource_methods=urm;
	}
	
	public void sendPhoneVerification(String uri, String server, String body) throws ClientProtocolException, URISyntaxException, IOException{
		cPOSTAsync = new ClientPOSTAsync(this);
	    type = "sendVerification";
	    cPOSTAsync.execute(new String[] { uri, body, CTYPE, server });
	}
	 
	public void actionUser(Action action) throws ClientProtocolException, URISyntaxException, IOException{
		cPOSTAsync = new ClientPOSTAsync(this);
        Gson gs=new Gson();
        type="action";
        cPOSTAsync.execute("/user/action",gs.toJson(action),CTYPE);
	}
	public void onMetricUser(Action action) throws ClientProtocolException, URISyntaxException, IOException{
		cPOSTAsync = new ClientPOSTAsync(this);
        Gson gs=new Gson();
        type="metric";
        cPOSTAsync.execute("/metric",gs.toJson(action),CTYPE);
	}
	public void createUser(User user) throws ClientProtocolException, URISyntaxException, IOException{
		type="create";
		cPOSTAsync = new ClientPOSTAsync(this);
		cPOSTAsync.execute(URI,checkForNewsAttributes(user),CTYPE);
	}
	
	public void updateUser(User user) throws ClientProtocolException, URISyntaxException, IOException{
		cPUTAsync = new ClientPUTAsync(this);
		cPUTAsync.execute(URI,checkForNewsAttributes(user),CTYPE);
	}
	
	public void getUser(String branchid, String userid, String phase) throws MalformedURLException, IOException{
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI,"?branch_id="+branchid+"&user_id="+userid+"&phase="+phase,CTYPE);
	}
	
	protected String checkForNewsAttributes (User user){		
		ArrayList<String> attributes=new ArrayList<String>();
		for(Field field : user.getClass().getDeclaredFields()) 
		       attributes.add(field.getName());
		 
		for(String att: attributes)
			for(Field field : User.class.getDeclaredFields())
				if(att.equalsIgnoreCase(field.getName()))
		    		   attributes.remove(att);
	
		Gson gs=new Gson();
		String json=gs.toJson(user);
		
		for(String att: attributes)
			json=json.replace("\""+att+"\"", "\""+"_"+att+"\"");
		
		return json;
	}
	
	@Override
	public void doPostExecute(Response response) {
		if(type.equalsIgnoreCase("metric")){
			user_resource_methods.onMetricUser(response);
		}else if(type.equalsIgnoreCase("action")){
			user_resource_methods.onActionUser(response);
		}else if (type.equalsIgnoreCase("create")){
			user_resource_methods.onCreateUser(response);
		}else{
			user_resource_methods.onSendPhoneVerification(response);
		}
	}

	@Override
	public void doGetExecute(Response response) {
		user_resource_methods.onGetUser(response);
	}

	@Override
	public void doPutExecute(Response response) {
		user_resource_methods.onUpdateUser(response);
	}
	
	public void cancelRequest(){	    
        if(cPOSTAsync!=null){
        	cPOSTAsync.cancelRequest();
        	cPOSTAsync.cancel(true);
        } else if(cPUTAsync!=null){
        	cPUTAsync.cancelRequest();
        	cPUTAsync.cancel(true);
        } else if(cGETAsync!=null){
        	cGETAsync.cancelRequest();
        	cGETAsync.cancel(true);
        } else { }
    }
}
