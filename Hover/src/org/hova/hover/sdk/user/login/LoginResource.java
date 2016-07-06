package org.hova.hover.sdk.user.login;

import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.ClientPUTAsync;
import org.hova.hover.sdk.http.ClientPUTAsync.PutRequestExectue;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.LoginChangePassword;
import org.hova.hover.sdk.pojo.LoginReset;

import com.google.gson.Gson;

public class LoginResource implements getRequestExectue, PostRequestExectue, PutRequestExectue {

	ResourceLogin resource_login;

	private static String URI = "/user/login?";

	private static String CTYPE = "application/json;";
	private ClientPOSTAsync cPOSTAsync;
	private ClientPUTAsync cPUTAsync;
	private ClientGETAsync cGETAsync;

	public interface ResourceLogin {
		void onLogin(Response response);

		void onResetLogin(Response response);

		void onChangePassword(Response paramResponse);
	}

	public LoginResource(ResourceLogin rl) {
		resource_login = rl;
	}

	public void getLogin(String userName, String password) {
		String queryString = "user=" + userName + "&password=" + password;
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, queryString, CTYPE);
	}

	public void resetLogin(LoginReset logReset) {
		cPOSTAsync = new ClientPOSTAsync(this);
		Gson json = new Gson();
		cPOSTAsync.execute("/user/login/reset", json.toJson(logReset), CTYPE);
	}

	public void changePasswordLogin(LoginChangePassword logChangePass) {
		cPUTAsync = new ClientPUTAsync(this);
		Gson json = new Gson();
		cPUTAsync.execute(new String[] { "/user/login/change_password", json.toJson(logChangePass), CTYPE });
	}

	@Override
	public void doGetExecute(Response response) {
		resource_login.onLogin(response);
	}

	@Override
	public void doPostExecute(Response response) {
		resource_login.onResetLogin(response);
	}

	@Override
	public void doPutExecute(Response response) {
		resource_login.onChangePassword(response);
	}

	public void cancelRequest() {
		if (cPOSTAsync != null) {
			cPOSTAsync.cancelRequest();
			cPOSTAsync.cancel(true);
		} else if (cPUTAsync != null) {
			cPUTAsync.cancelRequest();
			cPUTAsync.cancel(true);
		} else if (cGETAsync != null) {
			cGETAsync.cancelRequest();
			cGETAsync.cancel(true);
		} else {
		}
	}

}
