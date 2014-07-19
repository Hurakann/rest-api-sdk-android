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

package org.hova.hover.sdk.pojo;

/**
 * POJO class for user search (query string) structure based on API responses.
 * 
 * @author CarlosAlvarezV
 */
public class UserSearch {
	private String phase;
	private String name;
	private String lastname;
	private String lastname2;
	private String merge;
	private String profile_id;
	private String broot;

	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * @param phase
	 *            the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the lastname2
	 */
	public String getLastname2() {
		return lastname2;
	}

	/**
	 * @param lastname2
	 *            the lastname2 to set
	 */
	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	/**
	 * @return the merge
	 */
	public String getMerge() {
		return merge;
	}

	/**
	 * @param merge
	 *            the merge to set
	 */
	public void setMerge(String merge) {
		this.merge = merge;
	}

	/**
	 * @return the profile_id
	 */
	public String getProfile_id() {
		return profile_id;
	}

	/**
	 * @param profile_id
	 *            the profile_id to set
	 */
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	/**
	 * @return the broot
	 */
	public String getBroot() {
		return broot;
	}

	/**
	 * @param broot
	 *            the broot to set
	 */
	public void setBroot(String broot) {
		this.broot = broot;
	}
}
