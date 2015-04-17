package org.hova.hover.sdk.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

public class ClientPUTAsync extends AsyncTask<String, String, Response> {

	// Endpoint
	String endpoint = System.getProperty("http.endpoint");

	// API Version
	String api_version = System.getProperty("http.api.version");

	// HTTP parameters
	String timeout = System.getProperty("http.connection.timeout");
	String readtimeout = System.getProperty("http.connection.readtimeout");

	// HTTP custom headers
	String Ckey=System.getProperty("Ckey");
		
	// Response object
	Response resp;
		
	// Function to callBack
	PutRequestExectue put_req_execute;
	
	 /**
	  * 
	  * Interface to implements callback methods
	  * 
    */
	public interface PutRequestExectue {
		void doPutExecute(Response result);
	}
	
	
	/**
     * Build a instance of this class and set the default values to execute
     * request
     *
     * @param pr is an interface instance to implements callback methods
     * 
     */
	public ClientPUTAsync(PutRequestExectue putreq){
		put_req_execute=putreq;
	}
	
	/**
     * Execute the PUT HTTP request
     *
     * @param Array String contains all poarameters to execute request
     *
     * @return the response instance containing code and body response
     * @throws UnsupportedEncodingException
     * @throws ClientProtocolException
     * @throws IOException
     */
	@Override
	protected Response doInBackground(String... params) {
		try {
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPut;
		
			if(params.length<=3)
				httpPut = new HttpPut(endpoint+"/"+api_version+params[0].toString());
			else
				httpPut = new HttpPut(params[3]+params[0].toString());
			
			Log.d("SDK", "HOST "+httpPut.getURI());
		
			StringEntity entity;
		
			entity = new StringEntity(params[1], "UTF-8");
			entity.setContentEncoding("UTF-8");
			
			HttpParams httpParameters = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(timeout));
			HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(readtimeout));
			
			httpPut.setEntity(entity);
			httpPut.setHeader("Accept", params[2]);
			httpPut.setHeader("Content-type",params[2]+"charset=UTF-8");
			httpPut.addHeader("Ckey",Ckey);
			
			
			HttpResponse httpResponse = httpClient.execute(httpPut);
			
			resp=new Response();
			resp.setHttpcode(httpResponse.getStatusLine().getStatusCode());
			
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream is = httpEntity.getContent();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) 
				sb.append(line + "\n");
		
			is.close();
			String json = sb.toString();
			
			resp.setBody(json);
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resp;
	}

	
	/**
     * Callback method to indicate end of API request (Async. task).
     *
     * @param result the response instance containing code and body response
     * 
     */
	@Override
    public void onPostExecute(Response result) {
		put_req_execute.doPutExecute(result);
    }
	
	
}
