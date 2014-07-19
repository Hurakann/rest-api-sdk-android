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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.content.Context;

/**
 * Configuration for SDK
 * 
 * @author CarlosAlvarezV
 */
public class Config {
	/**
	 * Status of configuration in SDK
	 */

	Context context;

	public Config(Context context) {
		this.context = context;
	}

	public boolean configurationStatus = false;

	/**
	 * Load all SDK configuration into System
	 * 
	 * @param file
	 *            the file path where .properties file is placed
	 * @throws IOException
	 */
	public void load(String file) throws IOException {

		Properties properties = new Properties();

		InputStream stream = context.getAssets().open(file);
		// Log.d(Logs.LOG_CONFIG, stream.toString());

		properties.load(stream);

		Properties sys = System.getProperties();

		// All configuration to System
		sys.setProperty("http.endpoint",
				properties.getProperty("http.endpoint"));
		sys.setProperty("http.api.version",
				properties.getProperty("http.api.version"));
		sys.setProperty("http.connection.timeout",
				properties.getProperty("http.connection.timeout"));
		sys.setProperty("http.connection.readtimeout",
				properties.getProperty("http.connection.readtimeout"));

		// Log [INFO] Prepare http request
		Logger.getLogger("rest-api-sdk-android").log(Level.INFO,
				Logs.LOG_CONFIG, new String[] { properties.toString() });

		this.configurationStatus = true;
	}
}
