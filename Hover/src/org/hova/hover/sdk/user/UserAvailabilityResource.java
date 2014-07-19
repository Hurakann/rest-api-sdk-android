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
import org.hova.hover.sdk.http.ClientGET;
import org.hova.hover.sdk.http.Response;

/**
 * This class is responsible for using the resource to check the avail of
 * specific data (email, username, etc ...).
 * 
 * @author CarlosAlvarezV
 */
public class UserAvailabilityResource {
	/**
	 * The resource of our versioning api.
	 */
	private static String URI = "/user/availability";

	/**
	 * Our data encode as json (in next releases maybe include xml format).
	 */
	private static String CTYPE = "application/json";

	/**
	 * Check if username as email, nfc or other data used for login are
	 * available to use.
	 * 
	 * @param identity
	 *            the string representing the data to found
	 * 
	 * @return a response instance with the http status code
	 * 
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Response checkAvailability(String identity) throws ClientProtocolException, URISyntaxException, IOException{

		// Build a query string data for [GET]
		String queryString = "identity=" + identity;

		// Creates a new http client
		ClientGET client = new ClientGET(queryString, URI, CTYPE);

		// Send request
		Response response = client.request();

		return response;

	}
}
