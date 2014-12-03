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
import org.hova.hover.sdk.pojo.User;
import com.google.gson.Gson;


public class UserResource implements PostRequestExectue, getRequestExectue, PutRequestExectue {

	UserResourceMethods user_resource_methods;
	private static String URI = "/user";
	private static String CTYPE= "application/json;";
	
	public interface UserResourceMethods{
		void onCreateUser(Response result);
		void onGetUser(Response result);
		void onUpdateUser(Response result);
	}
	
	public UserResource(UserResourceMethods urm) {
		user_resource_methods=urm;
	}
	
	public void createUser(User user) throws ClientProtocolException, URISyntaxException, IOException{
        ClientPOSTAsync req=new ClientPOSTAsync(this);
        req.execute(URI,checkForNewsAttributes(user),CTYPE);
	}
	
	public void updateUser(User user) throws ClientProtocolException, URISyntaxException, IOException{
        ClientPUTAsync req=new ClientPUTAsync(this);
        req.execute(URI,checkForNewsAttributes(user),CTYPE);
	}
	
	public void getUser(String branchid, String userid, String phase) throws MalformedURLException, IOException{
        ClientGETAsync req=new ClientGETAsync(this);
        req.execute(URI,"?branch_id="+branchid+"&user_id="+userid+"&phase="+phase,CTYPE);
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
	public void doPostExecute(Response result) {
		user_resource_methods.onCreateUser(result);
	}

	@Override
	public void doGetExecute(Response result) {
		user_resource_methods.onGetUser(result);
	}

	@Override
	public void doPutExecute(Response result) {
		user_resource_methods.onUpdateUser(result);
	}
}
