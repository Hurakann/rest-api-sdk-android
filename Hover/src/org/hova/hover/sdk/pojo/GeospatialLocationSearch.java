package org.hova.hover.sdk.pojo;

public class GeospatialLocationSearch {

	String lat;
	String lon;
	String d;
	String max;

	public String getLat() {
		return lat;
	}

	/**
	 * Sets the latitude of actual position.
	 *
	 * @param lat
	 *            the value of the latitude
	 */

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	/**
	 * Sets the longitude of actual position.
	 *
	 * @param lon
	 *            the value of the longitude
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getD() {
		return d;
	}

	/**
	 * Sets the distance from actual actual position.
	 *
	 * @param d
	 *            the value of the distance
	 */
	public void setD(String d) {
		this.d = d;
	}

	public String getMax() {
		return max;
	}

	/**
	 * Sets the maximum of results.
	 *
	 * @param max
	 *            the value of the maximum
	 */
	public void setMax(String max) {
		this.max = max;
	}
}
