package org.hova.hover.sdk.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.UserSearch;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

public class UserSearchResource implements getRequestExectue {

	private String URI = "/user/search";
	private String URI_THQL = "/user/search/ql";
	SearchResource search_resource;
	private static String CTYPE = "application/json;";
	private ClientGETAsync cGETAsync;

	public interface SearchResource {
		void onSearchUser(Response response);
	}

	public UserSearchResource(SearchResource sr) {
		search_resource = sr;
	}

	public void search(UserSearch usr_search)
			throws UnsupportedEncodingException {
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, checkForNewsAttributes(usr_search), CTYPE);
	}

	public void searchTHQL(UserSearch usr_search)
			throws UnsupportedEncodingException {
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI_THQL, checkForNewsAttributes(usr_search), CTYPE);
	}

	protected String checkForNewsAttributes(UserSearch us)
			throws UnsupportedEncodingException {

		String queryString = "";
		try {
			Gson gson = new Gson();
			JSONObject jsonObj = new JSONObject(gson.toJson(us));
			Iterator<String> keysItr = jsonObj.keys();
			while (keysItr.hasNext()) {
				String key = keysItr.next();
				Object value = jsonObj.get(key);

				if (key.equalsIgnoreCase("page")
						|| key.equalsIgnoreCase("pagination")) {
					int i = (Integer) value;
					if (i != 0)
						if (queryString.equalsIgnoreCase(""))
							queryString = "?" + key + "=" + value;
						else
							queryString = queryString + "&" + key + "=" + value;
				} else {
					if (queryString.equalsIgnoreCase(""))
						queryString = "?" + key + "="
								+ URLEncoder.encode(value.toString(), "UTF-8");
					else
						queryString = queryString + "&" + key + "="
								+ URLEncoder.encode(value.toString(), "UTF-8");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return queryString;
	}

	@Override
	public void doGetExecute(Response response) {
		search_resource.onSearchUser(response);
	}
	
	public void cancelRequest() {
		if (cGETAsync != null) {
			cGETAsync.cancelRequest();
			cGETAsync.cancel(true);
		} else { }
	}

}
