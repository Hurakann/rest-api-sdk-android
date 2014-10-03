/**----------------------------------------------------------------------
 *** Copyright: (c) 2013-2014 Hova Networks S.A.P.I. de C.V.
 *** All rights reserved.
 ***
 *** Redistribution and use in any form, with or without modification,
 *** is strictly prohibited.
 ***
 *** Created by : Carlos Alvarez <alvrzcarlos@gmail.com> [CarlosAlvarezV]
 ***---------------------------------------------------------------------
 **/
package org.hova.hover.sdk.user;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.Response;

/**
 * This class is responsible for using the resource to check the avail of
 * specific data (email, username, etc ...).
 * 
 * @author CarlosAlvarezV
 */
public class UserAvailabilityResource implements getRequestExectue{


	protected static String URI="/user/availability";
	protected static String CTYPE="application/json;";

	ResourceAvailability availability_resource;
	
	public interface ResourceAvailability{
		void onCheckAvailanility(Response result);
	}
	
	public UserAvailabilityResource(ResourceAvailability ar) {
		// TODO Auto-generated constructor stub
		availability_resource=ar;
	}
	
	public void checkAvailability(String identity) throws ClientProtocolException, URISyntaxException, IOException{
		// Build a query string data for [GET]
		String query="?identity="+identity;
		
		ClientGETAsync req=new ClientGETAsync(this);
		req.execute(URI,query,CTYPE);
	}
	
	
	@Override
	public void doGetExecute(Response result) {
		availability_resource.onCheckAvailanility(result);
	}
		
}
