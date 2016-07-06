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
package org.hova.hover.sdk.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * Utility methods for library
 * 
 * @author CarlosAlvarezV
 */
public class Utility {
	/**
	 * Build a query string using reflection values of a desired class
	 * 
	 * @param map
	 *            the map containing reflection values
	 * @return the query string well-formed
	 * @throws UnsupportedEncodingException 
	 */
	public String buildQueryString(Map<String, String> map) throws UnsupportedEncodingException {

		// query string
		String queryString = "";

		for (Map.Entry<String, String> mapEntry : map.entrySet()) {
			if (mapEntry.getValue() != null) {

				// Get key of the map (name of parameter)
				String name = (String) mapEntry.getKey();

				// Get value of the map (value of parameter)
				String value = (String) mapEntry.getValue();

				queryString = queryString + name + "=" + URLEncoder.encode(value, "UTF-8") + "&";
			}
		}

		// Return without the last char '&'
		return queryString.substring(0, queryString.length() - 1);
	}
	
	public String buildJSONString(String[] keys, String[] values) {
		Map<String, String> map = new HashMap<String, String>();
		for(int index=0; index < keys.length; index++){
			map.put(keys[index], values[index]);
		}
		
		String body = new JSONObject(map).toString();
		
		return body;
	}
}
