package org.hova.hover.sdk.retail;

import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.Response;

public class RetailResources implements getRequestExectue {

	ResourcesRetail resources_retail;
	private int method = 0;

	private static String URI = "/retail";
	private static String CTYPE = "application/json;";
	private ClientGETAsync cGETAsync;

	public interface ResourcesRetail {
		void onGetRetailList(Response result);

		void onGetRetail(Response result);
	}

	public RetailResources(ResourcesRetail rr) {
		resources_retail = rr;
	}

	public void getRetailList(String estimote, String status) {
		method = 1;
		cGETAsync = new ClientGETAsync(this);
		String query = "?estimote=" + estimote + "&status=" + status;
		cGETAsync.execute(URI, query, CTYPE);
	}

	public void getRetail(String retail_ad_id) {
		method = 2;
		cGETAsync = new ClientGETAsync(this);
		String query = "?retail_ad_id=" + retail_ad_id;
		cGETAsync.execute(URI, query, CTYPE);
	}

	@Override
	public void doGetExecute(Response result) {
		switch (method) {
		case 1: {
			resources_retail.onGetRetailList(result);
			break;
		}
		case 2: {
			resources_retail.onGetRetail(result);
			break;
		}
		default:
			break;
		}
	}

	public void cancelRequest() {
		if (cGETAsync != null) {
			cGETAsync.cancelRequest();
			cGETAsync.cancel(true);
		} else {
		}
	}

}
