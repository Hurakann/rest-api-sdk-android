package org.hova.hover.sdk.confirm;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
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
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.ConfirmRegister;

public class ConfirmRegisterResource
  extends AsyncTask<String, String, Response>
{
  private static String CTYPE = "application/json;";
  String timeout = System.getProperty("http.connection.timeout");
  String readtimeout = System.getProperty("http.connection.readtimeout");
  String Ckey = System.getProperty("Ckey");
  Response resp;
  ResourceConfirmRegister resource_confirm_register;
  
  public ConfirmRegisterResource(ResourceConfirmRegister resource_confirm_register)
  {
    this.resource_confirm_register = resource_confirm_register;
  }
  
  protected Response doInBackground(String... params)
  {
    try
    {
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost(params[3] + params[0].toString());
      
      Log.d("SDK", "HOST " + httpPost.getURI());
      
      StringEntity entity = new StringEntity(params[1], "UTF-8");
      entity.setContentEncoding("UTF-8");
      
      Log.d("SDK", "BODY " + params[1]);
      
      HttpParams httpParameters = httpClient.getParams();
      HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(this.timeout));
      HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(this.readtimeout));
      
      httpPost.setEntity(entity);
      httpPost.setHeader("Accept", params[2]);
      httpPost.setHeader("Content-type", params[2] + "charset=UTF-8");
      httpPost.addHeader("Ckey", this.Ckey);
      
      HttpResponse httpResponse = httpClient.execute(httpPost);
      
      this.resp = new Response();
      this.resp.setHttpcode(httpResponse.getStatusLine().getStatusCode());
      
      HttpEntity httpEntity = httpResponse.getEntity();
      InputStream is = httpEntity.getContent();
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
      is.close();
      String json = sb.toString();
      
      this.resp.setBody(json);
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (ClientProtocolException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return this.resp;
  }
  
  public void sendConfirmRegister(ConfirmRegister cr, String server, String uri)
  {
    Gson gson = new Gson();
    String json = gson.toJson(cr);
    execute(new String[] { uri, json, CTYPE, server });
  }
  
  public void onPostExecute(Response result)
  {
    this.resource_confirm_register.doSendConfirm(result);
  }
  
  public static abstract interface ResourceConfirmRegister
  {
    public abstract void doSendConfirm(Response paramResponse);
  }
}
