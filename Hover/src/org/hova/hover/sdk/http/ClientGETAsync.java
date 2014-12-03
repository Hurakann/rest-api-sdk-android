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
public class ClientGETAsync extends  AsyncTask<String, String, Response> {

	// Endpoint
		String endpoint = System.getProperty("http.endpoint");

	// API Version
		String api_version = System.getProperty("http.api.version");

	// HTTP parameters
		String timeout = System.getProperty("http.connection.timeout");
		String readtimeout = System.getProperty("http.connection.readtimeout");

	// HTTP custom headers
		String Ckey=System.getProperty("Ckey");
		
		
	// Function to callBack
		getRequestExectue doRec_callback;
		
	// Response object
		Response resp;
		
	
	/**
	  * 
	  * Interface to implements callback methods
	  * 
	  */
	public interface getRequestExectue {
		void doGetExecute(Response result);
	}
	
	public ClientGETAsync(getRequestExectue do_rec) {
		// TODO Auto-generated constructor stub
		doRec_callback=do_rec;
	}
	
	@Override
	protected Response doInBackground(String... queryString){
		
		//params [0] URI
		//params [1] Body
		//params [2] CTYPE
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		HttpGet getReq= new  HttpGet(endpoint+"/"+api_version+queryString[0].toString()+queryString[1].toString());
		
		Log.d("SDK", "HOST "+getReq.getURI());
		
		getReq.setHeader("Accept",queryString[2].toString());
		getReq.addHeader("Ckey",Ckey);
	
		// SET TIME OUT AND READ TIME OUT
		HttpParams httpParameters = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(timeout));
		HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(readtimeout));
		
		try {
			// INIT RESPONSE OBJECT	
			resp=new Response();
			
			// MAKE API CALL
			HttpResponse httpResponse = httpClient.execute(getReq);
	
			// SET RESPONSE OBJECT	
			resp.setHttpcode(httpResponse.getStatusLine().getStatusCode());
			
			// PARSE SERVER RESPONSE TO STRING
			HttpEntity httpentity = httpResponse.getEntity();
			InputStream is = httpentity.getContent();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line;

			while ((line = br.readLine()) != null) 
				sb.append(line + "\n");
			
			// END CONNECTION
			resp.setBody(sb.toString());
			
			is.close();
			httpClient.getConnectionManager().shutdown();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return resp;
	}
	
	// CALL TO CALLBACK METHOD TO INDICATE END OF API REQUEST
	@Override
    public void onPostExecute(Response result) {
       doRec_callback.doGetExecute(result);
    }
	
	

}
