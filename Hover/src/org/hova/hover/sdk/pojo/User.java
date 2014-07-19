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
 * POJO class for user json structure based on API responses.
 * 
 * @author CarlosAlvarezV
 */
public class User {
	private String branch_id;
	private String name;
	private String lastname;
	private String lastname2;
	private String gender;
	private String birthday;
	private String phase;
	private String coloruser;
	private String language;
	private String profile_id;
	private String user_id;

	/**
	 * Branch id for the parent user (owner of the user)
	 * 
	 * @return the branch_id
	 */
	public String getBranch_id() {
		return branch_id;
	}

	/**
	 * Branch id for the parent user (owner of the user)
	 * 
	 * @param branch_id
	 *            the branch_id to set
	 */
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	/**
	 * Name for the user
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name for the user
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Lastname for the user
	 * 
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Lastname for the user
	 * 
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Lastname2 for the user
	 * 
	 * @return the lastname2
	 */
	public String getLastname2() {
		return lastname2;
	}

	/**
	 * Lastname2 for the user
	 * 
	 * @param lastname2
	 *            the lastname2 to set
	 */
	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	/**
	 * Gender of the user
	 * 
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gender of the user
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Birthday of the user (format YYYY-MM-DD)
	 * 
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Birthday of the user (format YYYY-MM-DD)
	 * 
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Phase to keep the user (1, 2, 3 or 4)
	 * 
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * Phase to keep the user (1, 2, 3 or 4)
	 * 
	 * @param phase
	 *            the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * Color for distinct the user (use black please!!)
	 * 
	 * @return the coloruser
	 */
	public String getColoruser() {
		return coloruser;
	}

	/**
	 * Color for distinct the user (use black please!!)
	 * 
	 * @param coloruser
	 *            the coloruser to set
	 */
	public void setColoruser(String coloruser) {
		this.coloruser = coloruser;
	}

	/**
	 * Language short description for the user (es, en, so on....)
	 * 
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Language short description for the user (es, en, so on....)
	 * 
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Profile that could take the user
	 * 
	 * @return the profile_id
	 */
	public String getProfile_id() {
		return profile_id;
	}

	/**
	 * Profile that could take the user
	 * 
	 * @param profile_id
	 *            the profile_id to set
	 */
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	/**
	 * User id of the parent user
	 * 
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * User id of the parent user
	 * 
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
