package com.upl.upl_survey.Model;

import java.util.Date;

public class UserDetails {

	private Long id;
	private String user_name;
	private String password;
	private Long created_by;
	private Long updated_by;
	private Date created_date;
	private Date updated_date;
	private String email;
	private String phone_no;
	private boolean is_deleted;
	private Long user_master_id;
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Long getUser_master_id() {
		return user_master_id;
	}

	public void setUser_master_id(Long user_master_id) {
		this.user_master_id = user_master_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetails [id=");
		builder.append(id);
		builder.append(", user_name=");
		builder.append(user_name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", created_by=");
		builder.append(created_by);
		builder.append(", updated_by=");
		builder.append(updated_by);
		builder.append(", created_date=");
		builder.append(created_date);
		builder.append(", updated_date=");
		builder.append(updated_date);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone_no=");
		builder.append(phone_no);
		builder.append(", is_deleted=");
		builder.append(is_deleted);
		builder.append(", user_master_id=");
		builder.append(user_master_id);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}