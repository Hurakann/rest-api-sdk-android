package org.hova.hover.sdk.user.login;

import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.LoginReset;

import com.google.gson.Gson;

public class LoginResource implements getRequestExectue, PostRequestExectue{

	ResourceLogin resource_login;

	private static String URI="/user/login?";

	private static String CTYPE="application/json;";
	
	
	public interface ResourceLogin{
		void onLogin(Response result);
		void onResetLogin(Response result);
	}
	
	public LoginResource(ResourceLogin rl) {
		resource_login=rl;
	}
	
	public void getLogin(String userName, String password){
		String queryString="user="+userName+"&password="+password;
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,queryString,CTYPE);
	}
	
	public void resetLogin(LoginReset logReset){
		ClientPOSTAsync req= new ClientPOSTAsync(this);
		Gson json=new Gson();
		req.execute("/user/login/reset",json.toJson(logReset),CTYPE);
	}
	
	@Override
	public void doGetExecute(Response result) {
		resource_login.onLogin(result);
	}

	@Override
	public void doPostExecute(Response result) {
		resource_login.onResetLogin(result);
	}

}
