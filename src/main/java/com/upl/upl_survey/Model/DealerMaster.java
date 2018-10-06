package com.upl.upl_survey.Model;

public class DealerMaster {

	private int id;
	private String company_name;
	private String company_code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DealerMaster [id=");
		builder.append(id);
		builder.append(", company_name=");
		builder.append(company_name);
		builder.append(", company_code=");
		builder.append(company_code);
		builder.append("]");
		return builder.toString();
	}

}
