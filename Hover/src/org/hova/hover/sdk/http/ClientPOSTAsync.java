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
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Creates a correct POST HTTP request using query string as API needs.
 *
 */
public class ClientPOSTAsync extends AsyncTask<String, String, Response> {

	// Endpoint
	String endpoint = System.getProperty("http.endpoint");

	// API Version
	String api_version = System.getProperty("http.api.version");

	// HTTP parameters
	String timeout = System.getProperty("http.connection.timeout");
	String readtimeout = System.getProperty("http.connection.readtimeout");

	// HTTP custom headers
	String Ckey = System.getProperty("Ckey");

	// Response object
	Response response;

	// Function to callBack
	PostRequestExectue post_req_execute;

	// Http object
	HttpPost httpPost;

	// Http client object
	DefaultHttpClient httpClient;

	/**
	 * 
	 * Interface to implements callback methods
	 * 
	 */
	public interface PostRequestExectue {
		/**
		 * Callback method to indicate end of API request (Async. task).
		 *
		 * @param response
		 *            the response instance containing code and body response
		 * 
		 */
		void doPostExecute(Response response);
	}

	/**
	 * Build a instance of this class and set the default values to execute
	 * request
	 *
	 * @param pr
	 *            is an interface instance to implements callback methods
	 * 
	 */
	public ClientPOSTAsync(PostRequestExectue pr) {
		post_req_execute = pr;
	}

	/**
	 * Execute the GET HTTP request
	 *
	 * @param Array
	 *            String contains all poarameters to execute request
	 *
	 * @return the response instance containing code and body response
	 * @throws UnsupportedEncodingException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Override
	protected Response doInBackground(String... params) {

		try {
			// params [0] URI
			// params [1] Body
			// params [2] CTYPE
			// params [3] HOST

			httpClient = new DefaultHttpClient();

			if (params.length <= 3)
				httpPost = new HttpPost(endpoint + "/" + api_version + params[0].toString());
			else
				httpPost = new HttpPost(params[3] + params[0].toString());

			Log.d("SDK", "HOST " + httpPost.getURI());

			StringEntity entity;
			entity = new StringEntity(params[1], "UTF-8");
			entity.setContentEncoding("UTF-8");

			Log.d("SDK", "BODY " + params[1]);

			HttpParams httpParameters = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(timeout));
			HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(readtimeout));

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", params[2]);
			httpPost.setHeader("Content-type", params[2] + "charset=UTF-8");
			httpPost.addHeader("Ckey", Ckey);

			if (!httpPost.isAborted()) {
				HttpResponse httpResponse = httpClient.execute(httpPost);

				response = new Response();
				response.setHttpcode(httpResponse.getStatusLine().getStatusCode());

				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream iStream = httpEntity.getContent();

				BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream, "UTF-8"), 8);
				StringBuilder sBuilder = new StringBuilder();
				String line = null;

				while ((line = bReader.readLine()) != null)
					sBuilder.append(line + "\n");

				bReader.close();
				iStream.close();

				String json = sBuilder.toString();
				response.setBody(json);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public void onPostExecute(Response response) {
		post_req_execute.doPostExecute(response);
	}

	public void cancelRequest() {
		if (httpPost != null) {
			httpPost.abort();
		}
	}

}
