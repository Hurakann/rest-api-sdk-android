package org.hova.hover.sdk.user;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.UserSearch;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;


public class UserSearchResource implements getRequestExectue {

	private String URI="/user/search";
	SearchResource search_resource;
	private static String CTYPE= "application/json;";
	
	
	public interface SearchResource{
		void onSearchUser(Response result);
	}
	
	public UserSearchResource(SearchResource sr) {
		search_resource=sr;
	}
	
	public void search(UserSearch usr_search){
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,checkForNewsAttributes(usr_search), CTYPE);
	}
	
	protected String checkForNewsAttributes (UserSearch us){
		
		String queryString="";
		try {
			Gson gson=new Gson();
			JSONObject jsonObj = new JSONObject(gson.toJson(us));
			Iterator<String> keysItr = jsonObj.keys();
	        while(keysItr.hasNext())
	        {
	        	String key = keysItr.next();
	            Object value = jsonObj.get(key);
	          
	            if(key.equalsIgnoreCase("page") || key.equalsIgnoreCase("pagination")){
	            	int i = (Integer)value;
	            	if(i!=0)
	            		 if(queryString.equalsIgnoreCase(""))
	     	            	queryString="?"+key+"="+value;
	     	            else
	     	            	queryString=queryString+"&"+key+"="+value;
	            }else{
	            	if(queryString.equalsIgnoreCase(""))
		            	queryString="?"+key+"="+value;
		            else
		            	queryString=queryString+"&"+key+"="+value;
	            }
	        }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return queryString;
	}
	
	
	@Override
	public void doGetExecute(Response result) {
		search_resource.onSearchUser(result);
	}

}
