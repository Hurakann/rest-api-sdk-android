package org.hova.hover.sdk.http;

public class ResponseFile {
	// Http status code
	private int httpcode;
	// Response file in bytes
	private byte[] file;
	
	/**
	 * Get the HTTP Status Code of the requested resource
	 * 
	 * @return the httpcode of the requested resource as integer
	 */
	public int getHttpCode() {
		return httpcode;
	}

	/**
	 * Set the HTTP Status Code of the requested resource
	 * 
	 * @param httpcode
	 *            the httpcode to set of the requested resource (only for HTTP
	 *            Clients)
	 */
	public void setHttpCode(int httpcode) {
		this.httpcode = httpcode;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
}
