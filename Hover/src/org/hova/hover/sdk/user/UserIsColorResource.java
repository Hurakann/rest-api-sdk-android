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
 * The class is responsible for using the resource is color to check if user
 * is registered with that color and return the branch id
 *
 * @author CarlosAlvarezV
 */

public class UserIsColorResource {
	/**
     * The resource of our versioning api.
     */
    private static String URI = "/user/is_color";
    
    /**
     * Our data encode as json (in next releases maybe include xml format).
     */
    private static String CTYPE = "application/json";
    
    /**
     * Check if user registered with provided email has any color assigned.
     * 
     * @param email the email of the user to be check
     * @param color the color to be check
     * 
     * @return a response instance with the http status code and the body decoded
     *          as json and in a class translation
     *          
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException
     *  
     */
    public Response checkIsColor(String email, String color) throws ClientProtocolException, URISyntaxException, IOException  {
        
        String queryString = "email=" + email + "&color=" + color;
        
        // Creates a new http client
        ClientGET client = new ClientGET(queryString, URI, CTYPE);

        // Send request
        Response response = client.request();
        
        return response;
    }
}
