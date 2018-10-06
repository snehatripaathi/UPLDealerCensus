package com.upl.upl_survey.Model;

import java.util.Arrays;
import java.util.Date;

public class FormDetails {

	private Long id;
	private byte[] form_detail;
	private String formData;
	private boolean is_updated = false;
	private Date date;
	private Long form_master_id;
	private Long updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getForm_detail() {
		return form_detail;
	}

	public void setForm_detail(byte[] form_detail) {
		this.form_detail = form_detail;
	}

	public boolean isIs_updated() {
		return is_updated;
	}

	public void setIs_updated(boolean is_updated) {
		this.is_updated = is_updated;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getForm_master_id() {
		return form_master_id;
	}

	public void setForm_master_id(Long form_master_id) {
		this.form_master_id = form_master_id;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
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
		builder.append("FormDetails [id=");
		builder.append(id);
		builder.append(", form_detail=");
		builder.append(Arrays.toString(form_detail));
		builder.append(", formData=");
		builder.append(formData);
		builder.append(", is_updated=");
		builder.append(is_updated);
		builder.append(", date=");
		builder.append(date);
		builder.append(", form_master_id=");
		builder.append(form_master_id);
		builder.append(", updated_by=");
		builder.append(updated_by);
		builder.append("]");
		return builder.toString();
	}

}
