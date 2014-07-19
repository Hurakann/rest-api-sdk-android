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

/**
 * The class is responsible for posting and deleting files in the Hover system
 * using the Hover API.
 * 
 * @author CarlosAlvarezV
 */

public class UserFileResource {
	/**
	 * The resource of our versioning api.
	 */
	private static String URI = "/user/file";

	/**
	 * Our data encode as json (in next releases maybe include xml format).
	 */
	private static String CTYPE = "application/json";
}
