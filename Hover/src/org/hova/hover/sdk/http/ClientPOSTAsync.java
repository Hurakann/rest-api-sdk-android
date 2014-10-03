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

public class ClientPOSTAsync extends AsyncTask<String, String, Response> {

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

	
	PostRequestExectue post_req_execute;
	
	public interface PostRequestExectue {
		void doPostExecute(Response result);
	}
	
	public ClientPOSTAsync(PostRequestExectue pr) {
		// TODO Auto-generated constructor stub
		post_req_execute=pr;
	}
	
	@Override
	protected Response doInBackground(String... params) {
		
		try {
			//params [0] URI
			//params [1] Body
			//params [2] CTYPE
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(endpoint+"/"+api_version+params[0].toString());
			
			Log.d("SDK", "HOST "+httpPost.getURI());
			
			StringEntity entity;
			entity = new StringEntity(params[1], "UTF-8");
			entity.setContentEncoding("UTF-8");
			
			Log.d("SDK", "BODY "+params[1]);
			
			HttpParams httpParameters = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(timeout));
			HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(readtimeout));
			
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", params[2]);
			httpPost.setHeader("Content-type",params[2]+"charset=UTF-8");
			httpPost.addHeader("Ckey",Ckey);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
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

	@Override
    public void onPostExecute(Response result) {
		post_req_execute.doPostExecute(result);
    }
	
}
