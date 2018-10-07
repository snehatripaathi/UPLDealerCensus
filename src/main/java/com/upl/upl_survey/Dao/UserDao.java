package com.upl.upl_survey.Dao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.upl.upl_survey.Model.CallStatusData;
import org.apache.ibatis.annotations.Param;

import com.upl.upl_survey.Model.FormMaster;
import com.upl.upl_survey.Model.UserDetails;

public interface UserDao {
	UserDetails getLoginUser(@Param("email") String email);

	String getUserPassword(@Param("email") String email);

	void addUser(@Param("id") Long id, @Param("user_name") String user_name, @Param("password") String password,
			@Param("created_by") Long created_by, @Param("updated_by") Long updated_by,
			@Param("created_date") Date created_date, @Param("updated_date") Date updated_date,
			@Param("phone_no") String phone_no, @Param("email") String email,
			@Param("user_master_id") Long user_master_id);

	void updateUserDetails(@Param("id") Long id, @Param("user_name") String user_name,
			@Param("password") String password, @Param("updated_by") Long updated_by,
			@Param("updated_date") Date updated_date, @Param("phone_no") String phone_No, @Param("email") String email,
			@Param("user_master_id") Long user_master_id);

	void deleteUserDetails(@Param("id") Long id);

	List<UserDetails> getAllUserDetails();

	void createForm(@Param("id") Long id, @Param("form_id") String form_id, @Param("created_by") Long created_by,
			@Param("dealer_name") String dealer_name, @Param("created_date") Date created_date,
			@Param("updated_date") Date updated_date , @Param("last_updated_by") Long last_updated_by,
					@Param("state") String state,
					@Param("call_status") String callStatus,
					@Param("district") String district,
					@Param("sub_district") String subDistrict);

	void insertFormDetails(@Param("id") Long id, @Param("form_detail") byte[] form_detail, @Param("date") Date date,
			@Param("form_master_id") Long form_master_id, @Param("updated_by") Long updated_by);

	Long getFormId(@Param("form_id") String form_id);

	void deleteForm(@Param("id") Long id);

	void updateFormMasterData(@Param("id") Long id, @Param("form_id") String form_id,
			@Param("last_updated_by") Long last_updated_by, @Param("updated_date") Date updated_date, @Param("dealer_name") String dealer_name,
							  @Param("state") String state,
							  @Param("call_status") String callStatus,
							  @Param("district") String district,
							  @Param("sub_district") String subDistrict);

	List<FormMaster> getAllForms();

	Long getUserId(@Param("name") String name);

	void updateSequenceId(@Param("name") String name, @Param("seq_id") Long seq_id);

	void updateIsUpdated(@Param("form_master_id") Long form_master_id);
	List<CallStatusData> getCallStatusData();
}
