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
package org.hova.hover.sdk.user.login;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.common.Utility;
import org.hova.hover.sdk.http.ClientGET;
import org.hova.hover.sdk.http.ClientPOST;
import org.hova.hover.sdk.http.Response;

/**
 * The class is responsible for trying login of the user in the Hover system
 * using the Hover API.
 * 
 * @author CarlosAlvarezV
 */
public class UserLoginResource {
	// the resource of our versioning api
	private static String URI = "/v1/user/login";

	// Our data encode as json (in next releases maybe include xml format)
	private static String CTYPE = "application/json";

	/**
	 * Try user login, if it's successfully get the basic user info using the
	 * Hover API.
	 * 
	 * @param user
	 *            the username for the login, it could be NFC id, username or
	 *            email.
	 * @param password
	 *            the password for the login, it could be password string or
	 *            fingerprint tag.
	 * @return a response instance with the http status code and the body
	 *         decoded as json and in a class translation
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * 
	 */
	public Response loginUser(String user, String password)
			throws ClientProtocolException, URISyntaxException, IOException {

		// Build a query string data for [GET]
		String queryString = "user=" + user + "&password=" + password;

		// Creates a new http client
		ClientGET client = new ClientGET(queryString, URI, CTYPE);

		// Send request
		Response response = client.request();

		// Translates map to class
		// Gson gson = new Gson();
		// UserLogin user = gson.fromJson(response.getBody(), UserLogin.class);

		// response.setBodyT(user);

		return response;

	}

	/**
	 * Send a request for to send an e-mail with a recovery code to the email
	 * specified
	 * 
	 * @param email
	 *            the e-mail used for to create the account
	 * 
	 * @return a response instance with the http status code and send an email
	 *         with a recovery code
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * 
	 */

	public Response restorePassword(String email)
			throws ClientProtocolException, URISyntaxException, IOException {

		// Build a query string data for [GET]
		String queryString = "email=" + email;

		// Creates a new http client
		ClientGET client = new ClientGET(queryString, URI + "/restore", CTYPE);

		// Send request
		Response response = client.request();

		return response;

	}

	/**
	 * Send a request to change the password with the utilization of a recovery
	 * code
	 * 
	 * @param email
	 *            the e-mail of the account
	 * @param new_password
	 *            the new password that will be used for login
	 * @param code
	 *            the recovery code received in the e-mail on having restored
	 *            the password
	 * 
	 * @return a response instance with the http status code
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalStateException
	 * 
	 */
	public Response resetPassword(String email, String new_password, String code)
			throws IllegalStateException, IOException, URISyntaxException {

		Utility util = new Utility();

		// Build json string
		String body = util.buildJSONString(new String[] { "email",
				"new_password", "code" }, new String[] { email, new_password,
				code });

		// Creates a new http client
		ClientPOST client = new ClientPOST(body, URI + "/reset", CTYPE);

		// Send request
		Response response = client.request();

		return response;

	}

	/**
	 * Send a request to change the password with the utilization of the used ID
	 * of the user account
	 * 
	 * @param user_id
	 *            the user ID of the account which will be changed the password
	 * @param old_password
	 *            the actual/active password
	 * @param new_password
	 *            the new password that will be used for login
	 * 
	 * @return a response instance with the http status code
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalStateException
	 * 
	 */
	public Response changePassword(String user_id, String old_password,
			String new_password) throws IllegalStateException, IOException,
			URISyntaxException {

		Utility util = new Utility();

		// Build json string
		String body = util.buildJSONString(new String[] { "user_id",
				"old_password", "new_password" }, new String[] { user_id,
				old_password, new_password });

		// Creates a new http client
		ClientPOST client = new ClientPOST(body, URI + "/change_password",
				CTYPE);

		// Send request
		Response response = client.request();

		return response;

	}

}
