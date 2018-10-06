package com.upl.upl_survey.Model;

import java.sql.Timestamp;

public class FormMaster {

	private Long id;
	private String form_id;
	private String dealer_name;
	private boolean is_deleted;
	private Timestamp created_date;
	private Timestamp updated_date;
	private Long created_by;
	private Long last_updated_by;
	private Long language_id;
	private FormDetails formdetails;
	private String formData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public Timestamp getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = updated_date;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(Long last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public Long getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}

	public FormDetails getFormdetails() {
		return formdetails;
	}

	public void setFormdetails(FormDetails formdetails) {
		this.formdetails = formdetails;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormMaster [id=");
		builder.append(id);
		builder.append(", form_id=");
		builder.append(form_id);
		builder.append(", dealer_name=");
		builder.append(dealer_name);
		builder.append(", is_deleted=");
		builder.append(is_deleted);
		builder.append(", created_date=");
		builder.append(created_date);
		builder.append(", updated_date=");
		builder.append(updated_date);
		builder.append(", created_by=");
		builder.append(created_by);
		builder.append(", last_updated_by=");
		builder.append(last_updated_by);
		builder.append(", language_id=");
		builder.append(language_id);
		builder.append(", formdetails=");
		builder.append(formdetails);
		builder.append(", formData=");
		builder.append(formData);
		builder.append("]");
		return builder.toString();
	}

}
