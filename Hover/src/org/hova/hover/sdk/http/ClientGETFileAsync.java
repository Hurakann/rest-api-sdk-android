package org.hova.hover.sdk.http;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class ClientGETFileAsync extends AsyncTask<String, String, ResponseFile> {
	
	URI uri;
	DefaultHttpClient httpclient;
	HttpParams httpparams;
	HttpGet httpget;
	
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
	getRequestFileExecute requestCallback;

	// Response object
			ResponseFile responseFile;

	/**
	 * 
	 * Interface to implements callback methods
	 * 
	 */
	public interface getRequestFileExecute {
		void doGetFileExecute(ResponseFile responseFile);
	}
	
	public ClientGETFileAsync(getRequestFileExecute requestCallback) {		
		this.requestCallback = requestCallback;
	}
	
	@Override
	protected ResponseFile doInBackground(String... urls) {
		try {
			uri = new URI(endpoint+"/"+api_version+urls[0]+urls[1]);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		httpclient = new DefaultHttpClient();

		httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, Integer.parseInt(timeout));
		HttpConnectionParams.setSoTimeout(httpparams, Integer.parseInt(readtimeout));
		httpget = new HttpGet(uri);
		httpget.addHeader("Host", uri.getHost());
		httpget.addHeader("Ckey", Ckey);
		HttpResponse httpResponse;
		try {
			
			// INIT RESPONSE OBJECT	
			responseFile= new ResponseFile();
						
			httpResponse = httpclient.execute(httpget);

			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			
			// SET RESPONSE OBJECT	
			responseFile.setHttpCode(statusCode);
						
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = httpResponse.getEntity();
				byte[] bytes = EntityUtils.toByteArray(entity);
				//Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,
				//		bytes.length);
				responseFile.setFile(bytes);
				//return bytes;
			} else {
				responseFile.setFile(new byte[0]);
				//throw new IOException("Download failed, HTTP response code " + statusCode + " - " + statusLine.getReasonPhrase());
			}
			return responseFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    public void onPostExecute(ResponseFile responseFile) {
		requestCallback.doGetFileExecute(responseFile);
    }
	
}
