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

	private String branch_id;
	private String thql;
	private String pagination_id;
	private int pagination;
	private int page;

	public String getBranch_id() {
		return this.branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public int getPagination() {
		return this.pagination;
	}

	public void setPagination(int pagination) {
		this.pagination = pagination;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getThql() {
		return this.thql;
	}

	public void setThql(String thql) {
		this.thql = thql;
	}

	public String getPagination_id() {
		return this.pagination_id;
	}

	public void setPagination_id(String pagination_id) {
		this.pagination_id = pagination_id;
	}

}
