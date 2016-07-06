/**----------------------------------------------------------------------
 *** Copyright: (c) 2013-2014 Hova Networks S.A.P.I. de C.V.
 *** All rights reserved.
 ***
 *** Redistribution and use in any form, with or without modification,
 *** is strictly prohibited.
 ***
 *** Created by : Eder Gomez Nocelo <eder.nocelo@hovanetworks.com>
 ***---------------------------------------------------------------------
 **/

package org.hova.hover.sdk.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.hova.hover.sdk.http.Response;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Creates a correct GET HTTP request using query string as API needs.
 *
 */
public class ClientGETAsync extends AsyncTask<String, String, Response> {

	// Endpoint
	String endpoint = System.getProperty("http.endpoint");

	// API Version
	String api_version = System.getProperty("http.api.version");

	// HTTP parameters
	String timeout = System.getProperty("http.connection.timeout");
	String readtimeout = System.getProperty("http.connection.readtimeout");

	// HTTP custom headers
	String Ckey = System.getProperty("Ckey");

	// Function to callBack
	getRequestExectue doRec_callback;

	// Response object
	Response response;

	// Http object
	HttpGet httpGet;

	// Http client object
	DefaultHttpClient httpClient;

	/**
	 * 
	 * Interface to implements callback methods
	 * 
	 */
	public interface getRequestExectue {
		void doGetExecute(Response response);
	}

	public ClientGETAsync(getRequestExectue do_rec) {
		doRec_callback = do_rec;
	}

	@Override
	protected Response doInBackground(String... queryString) {

		// params [0] URI
		// params [1] Body
		// params [2] CTYPE

		httpClient = new DefaultHttpClient();

		httpGet = new HttpGet(endpoint + "/" + api_version + queryString[0].toString() + queryString[1].toString());

		Log.d("SDK", "HOST " + httpGet.getURI());

		httpGet.setHeader("Accept", queryString[2].toString());
		httpGet.addHeader("Ckey", Ckey);

		// SET TIME OUT AND READ TIME OUT
		HttpParams httpParameters = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(timeout));
		HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(readtimeout));

		try {
			// INIT RESPONSE OBJECT

			if (!httpGet.isAborted()) {
				// MAKE API CALL
				HttpResponse httpResponse = httpClient.execute(httpGet);

				response = new Response();
				// SET RESPONSE OBJECT
				response.setHttpcode(httpResponse.getStatusLine()
						.getStatusCode());

				// PARSE SERVER RESPONSE TO STRING
				HttpEntity httpentity = httpResponse.getEntity();
				InputStream iStream = httpentity.getContent();

				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
				StringBuilder sBuilder = new StringBuilder();

				String line;

				while ((line = bReader.readLine()) != null)
					sBuilder.append(line + "\n");

				// END CONNECTION
				response.setBody(sBuilder.toString());

				bReader.close();
				iStream.close();
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	// CALL TO CALLBACK METHOD TO INDICATE END OF API REQUEST
	@Override
	public void onPostExecute(Response response) {
		doRec_callback.doGetExecute(response);
	}

	public void cancelRequest() {
		if (httpGet != null) {
			httpGet.abort();
		}
	}

}
