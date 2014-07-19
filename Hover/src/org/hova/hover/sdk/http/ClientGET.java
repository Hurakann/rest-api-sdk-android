/**----------------------------------------------------------------------
 *** Copyright: (c) 2013-2014 Hova Networks S.A.P.I. de C.V.
 *** All rights reserved.
 ***
 *** Redistribution and use in any form, with or without modification,
 *** is strictly prohibited.
 ***
 *** Created by : Carlos Alvarez <alvrzcarlos@gmail.com> [CarlosAlvarezV]
 ***---------------------------------------------------------------------
 **/
package org.hova.hover.sdk.http;

/**
 * Creates a correct GET HTTP request using query string as API needs.
 *
 * @author CarlosAlvarezV
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.hova.hover.sdk.common.Logs;

public class ClientGET {
	// Endpoint
	String Endpoint = System.getProperty("http.endpoint");

	// API Version
	String API_Version = System.getProperty("http.api.version");

	// HTTP parameters
	String timeout = System.getProperty("http.connection.timeout");
	String readtimeout = System.getProperty("http.connection.readtimeout");

	// body request
	public String body;

	// uri
	public String uri;

	// Content-Type, xml or json
	public String ctype;

	/**
	 * Build a instance of this class and set the default values to execute
	 * request
	 * 
	 * @param body
	 *            the body of the request (in this case is a query string)
	 * @param uri
	 *            the uri of the request. In RESTful is known as the resource
	 * @param ctype
	 *            the content-type for the body request
	 */
	public ClientGET(String body, String uri, String ctype) {
		this.body = body;
		this.uri = uri;
		this.ctype = ctype;
	}

	/**
	 * Execute the GET HTTP request building previously with the attributes
	 * passed into this class constructor.
	 * 
	 * @return the response instance containing code and body response
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public Response request() throws URISyntaxException,
			ClientProtocolException, IOException {

		URI uri_ = new URI(Endpoint + "/" + API_Version + this.uri + "/?"
				+ this.body);
		HttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();

		// Set timeout
		HttpConnectionParams.setConnectionTimeout(httpparams,
				Integer.valueOf(timeout));
		HttpConnectionParams.setSoTimeout(httpparams,
				Integer.valueOf(readtimeout));

		HttpGet httpget = new HttpGet(uri_);

		httpget.setHeader("Accept", ctype);
		httpget.addHeader("Host", uri_.getHost());

		HttpResponse httpresponse = httpclient.execute(httpget);

		// Log [INFO] Prepare http request
		Logger.getLogger("rest-api-sdk-java").log(Level.INFO, Logs.LOG_REQUEST,
				new String[] { this.uri, this.body, this.ctype });

		// Now generate a Response instance to store results from server
		Response response = new Response();

		response.setHttpcode(httpresponse.getStatusLine().getStatusCode());

		HttpEntity httpentity = httpresponse.getEntity();
		InputStream is = httpentity.getContent();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line;

		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}

		response.setBody(sb.toString());

		// Close
		is.close();

		// Loggin our response!
		Logger.getLogger("rest-api-sdk-java").log(
				Level.INFO,
				Logs.LOG_RESPONSE,
				new String[] { String.valueOf(response.getHttpcode()),
						response.getBody(), "[Class<?>T]" });

		httpclient.getConnectionManager().shutdown();

		return response;
	}

}
