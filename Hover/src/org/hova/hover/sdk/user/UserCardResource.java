package org.hova.hover.sdk.user;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPUTAsync;
import org.hova.hover.sdk.http.Response;

public class UserCardResource implements ClientPOSTAsync.PostRequestExectue,
		ClientPUTAsync.PutRequestExectue {
	CardResource card_resource;
	private static String type;
	private static String CTYPE = "application/json;";
	private ClientPOSTAsync cPOSTAsync;
	private ClientPUTAsync cPUTAsync;

	public UserCardResource(CardResource cr) {
		this.card_resource = cr;
	}

	public void getToken(String uri, String server, String body) throws ClientProtocolException, URISyntaxException, IOException {
		cPOSTAsync = new ClientPOSTAsync(this);
		type = "token";
		cPOSTAsync.execute(new String[] { uri, body, CTYPE, server });
	}

	public void getCardInfo(String uri, String server, String body) throws ClientProtocolException, URISyntaxException, IOException {
		cPOSTAsync = new ClientPOSTAsync(this);
		type = "getCard";
		cPOSTAsync.execute(new String[] { uri, body, CTYPE, server });
	}

	public void updateCardInfo(String uri, String server, String body) throws ClientProtocolException, URISyntaxException, IOException {
		cPUTAsync = new ClientPUTAsync(this);
		cPUTAsync.execute(new String[] { uri, body, CTYPE, server });
	}

	public void doPostExecute(Response response) {
		if (type.equalsIgnoreCase("token")) {
			this.card_resource.onGetToken(response);
		} else {
			this.card_resource.onGetCard(response);
		}
	}

	public void doPutExecute(Response response) {
		this.card_resource.onUpdateCard(response);
	}

	public static abstract interface CardResource {
		public abstract void onGetToken(Response paramResponse);

		public abstract void onGetCard(Response paramResponse);

		public abstract void onUpdateCard(Response paramResponse);
	}
	public void cancelRequest(){	    
        if(cPOSTAsync!=null){
        	cPOSTAsync.cancelRequest();
        	cPOSTAsync.cancel(true);
        } else if(cPUTAsync!=null){
        	cPUTAsync.cancelRequest();
        	cPUTAsync.cancel(true);
        } else { }
    }
}
