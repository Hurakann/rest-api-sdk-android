package org.hova.hover.sdk.user.login;

import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.Response;

public class LoginResource implements getRequestExectue{

	ResourceLogin resource_login;

	private static String URI="/user/login?";

	private static String CTYPE="application/json;";
	
	
	public interface ResourceLogin{
		void onLogin(Response result);
	}
	
	public LoginResource(ResourceLogin rl) {
		resource_login=rl;
	}
	
	public void getLogin(String userName, String password){
		String queryString="user="+userName+"&password="+password;
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,queryString,CTYPE);
	}
	
	@Override
	public void doGetExecute(Response result) {
		// TODO Auto-generated method stub
		resource_login.onLogin(result);
	}

}
