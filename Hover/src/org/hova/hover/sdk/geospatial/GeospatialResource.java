package org.hova.hover.sdk.geospatial;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.ClientPUTAsync.PutRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPUTAsync;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.GeospatialLocation;

import com.google.gson.Gson;

public class GeospatialResource implements PostRequestExectue, getRequestExectue, PutRequestExectue {

	GeospatialResourceMethods geospatial_resource_methods;
	private static String URI = "/geospatial";
	private static String CTYPE = "application/json;";	
	private ClientPOSTAsync cPOSTAsync;
	private ClientPUTAsync cPUTAsync;
	
	public interface GeospatialResourceMethods {
		void onCreateLocation(Response response);
		void onUpdateLocation(Response response);
	}

	public GeospatialResource(GeospatialResourceMethods geospatial_resource_methods) {
		this.geospatial_resource_methods = geospatial_resource_methods;
	}

	/**
	 * This method index a single location (latitude, longitude) associated to a
	 * previously object created to use in Geospatial searches.
	 *
	 * @param location
	 *            An Geospatial Location object
	 */
	public void createLocation(GeospatialLocation location) throws ClientProtocolException, URISyntaxException, IOException {
		cPOSTAsync = new ClientPOSTAsync(this);
		cPOSTAsync.execute(URI, checkForNewsAttributes(location), CTYPE);
	}

	/**
	 * This method update an indexed location (latitude, longitude) associated
	 * to a previously object created to use in Geospatial searches.
	 *
	 * @param location
	 *            An Geospatial Location object
	 */
	public void updateLocation(GeospatialLocation location) throws ClientProtocolException, URISyntaxException, IOException {
		cPUTAsync = new ClientPUTAsync(this);
		cPUTAsync.execute(URI, checkForNewsAttributes(location), CTYPE);
	}

	protected String checkForNewsAttributes(GeospatialLocation location) {
		ArrayList<String> attributes = new ArrayList<String>();
		for (Field field : location.getClass().getDeclaredFields())
			attributes.add(field.getName());

		for (String att : attributes)
			for (Field field : GeospatialLocation.class.getDeclaredFields())
				if (att.equalsIgnoreCase(field.getName()))
					attributes.remove(att);

		Gson gs = new Gson();
		String json = gs.toJson(location);

		for (String att : attributes)
			json = json.replace("\"" + att + "\"", "\"" + "_" + att + "\"");

		return json;
	}

	@Override
	public void doPutExecute(Response response) {
		geospatial_resource_methods.onUpdateLocation(response);
	}

	@Override
	public void doGetExecute(Response response) {	}

	@Override
	public void doPostExecute(Response response) {
		geospatial_resource_methods.onCreateLocation(response);
	}
	
	public void cancelRequest(){	    
        if(cPOSTAsync!=null){
        	cPOSTAsync.cancelRequest();
        	cPOSTAsync.cancel(true);
        } else if(cPUTAsync!=null){
        	cPUTAsync.cancelRequest();
        	cPUTAsync.cancel(true);
        } else { }
    }
}
