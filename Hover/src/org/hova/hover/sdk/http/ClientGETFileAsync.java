package org.hova.hover.sdk.http;

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
	DefaultHttpClient httpClient;
	HttpParams httpparams;
	HttpGet httpGet;

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
			uri = new URI(endpoint + "/" + api_version + urls[0] + urls[1]);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		httpClient = new DefaultHttpClient();

		httpparams = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, Integer.parseInt(timeout));
		HttpConnectionParams.setSoTimeout(httpparams, Integer.parseInt(readtimeout));
		httpGet = new HttpGet(uri);
		httpGet.addHeader("Host", uri.getHost());
		httpGet.addHeader("Ckey", Ckey);
		HttpResponse httpResponse;
		try {

			// INIT RESPONSE OBJECT

			if (!httpGet.isAborted()) {
				httpResponse = httpClient.execute(httpGet);
				StatusLine statusLine = httpResponse.getStatusLine();
				int statusCode = statusLine.getStatusCode();

				responseFile = new ResponseFile();
				// SET RESPONSE OBJECT
				responseFile.setHttpCode(statusCode);

				if (statusCode == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					byte[] bytes = EntityUtils.toByteArray(entity);
					responseFile.setFile(bytes);
				} else {
					responseFile.setFile(new byte[0]);
				}
			}

			return responseFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseFile;
	}

	@Override
	public void onPostExecute(ResponseFile responseFile) {
		requestCallback.doGetFileExecute(responseFile);
	}

	public void cancelRequest() {
		if (httpGet != null) {
			httpGet.abort();
		}
	}

}
