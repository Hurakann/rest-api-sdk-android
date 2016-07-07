package org.hova.hover.sdk.geospatial;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.GeospatialLocationSearch;
import org.json.JSONObject;

import com.google.gson.Gson;

public class GeospatialSearchResource implements getRequestExectue {
	private String URI = "/geospatial";
	LocationSearchResource search_resource;
	private static String CTYPE = "application/json;";
	private ClientGETAsync cGETAsync;
	
	public interface LocationSearchResource {
		void onSearchLocation(Response response);
	}

	/**
	 * This method search locations near of a given point (latitude, longitude) using a
	 * specific distance.
	 *
	 * @param geospatial_location_search
	 *            An Geospatial Location Search object
	 */
	public void search(GeospatialLocationSearch geospatial_location_search)throws UnsupportedEncodingException {
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, checkForNewsAttributes(geospatial_location_search), CTYPE);
	}

	public GeospatialSearchResource(LocationSearchResource search_resource) {
		this.search_resource = search_resource;
	}
	
	protected String checkForNewsAttributes (GeospatialLocationSearch geospatial_location_search) throws UnsupportedEncodingException{
		
		String queryString="";
		try {
			Gson gson=new Gson();
			JSONObject jsonObj = new JSONObject(gson.toJson(geospatial_location_search));
			Iterator<?> keysItr = jsonObj.keys();
	        while(keysItr.hasNext())
	        {
	        	String key = (String) keysItr.next();
	            Object value = jsonObj.get(key);
	          
	            if(key.equalsIgnoreCase("page") || key.equalsIgnoreCase("pagination")){
	            	int i = (Integer)value;
	            	if(i!=0)
	            		 if(queryString.equalsIgnoreCase(""))
	     	            	queryString="?"+key+"="+value;
	     	            else
	     	            	queryString=queryString+"&"+key+"="+value;
	            }else{
	            	if(queryString.equalsIgnoreCase(""))
		            	queryString="?"+key+"="+ URLEncoder.encode(value.toString(),"UTF-8");
		            else
		            	queryString=queryString+"&"+key+"="+ URLEncoder.encode(value.toString(),"UTF-8");
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return queryString;
	}

	@Override
	public void doGetExecute(Response response) {
		search_resource.onSearchLocation(response);
	}
	
	public void cancelRequest(){	    
        if(cGETAsync!=null){
        	cGETAsync.cancelRequest();
        	cGETAsync.cancel(true);
        } else { }
    }

}
