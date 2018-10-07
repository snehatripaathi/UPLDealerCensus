package com.upl.upl_survey.Manager;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upl.upl_survey.Dao.UserDao;
import com.upl.upl_survey.Model.FormMaster;
import com.upl.upl_survey.Model.UserDetails;

@RestController
@Produces({ "application/json" })
@RequestMapping("/upl_survey")
public class UPLServices {

	private static final Logger logger = LoggerFactory.getLogger(UPLServices.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	PasswordValidation passEncrp;

	@Autowired
	CallStatusSerivce callStatusSerivce;

	@POST
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public UserDetails getLoginUser(@QueryParam("email") String email, @QueryParam("password") String password) {
		logger.info("Login User ..");
		logger.info("User Id :{} and Password :{} ", email, password);
		String UserPassword = userDao.getUserPassword(email);
//		UserPassword = passEncrp.encrypt(UserPassword);
//		logger.info("UserPassword encrypt :{}", UserPassword);
		UserPassword = passEncrp.decrypt(UserPassword);
		logger.info("UserPassword decrypt:{}", UserPassword);
		if (password.equals(UserPassword)) {
			UserDetails details = userDao.getLoginUser(email);
			logger.info("details :{}", details);
			return details;
		} else {
			logger.info("User or Password not vaild ..");
			return null;
		}
	}

	@POST
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(@QueryParam("user_name") String user_name, @QueryParam("password") String password,
			@QueryParam("created_by") Long created_by, @QueryParam("phone_no") String phone_no,
			@QueryParam("email") String email, @QueryParam("user_master_id") Long user_master_id) {
		Long id = userDao.getUserId("UserDetails");
		id = id + 1;
		logger.info("Add new user");
		logger.info("New User Details id:{" + id + "} password :{" + password + "} created_by:{" + created_by
				+ "} phone_no:{" + phone_no + "} email:{" + email + "} user_master_id=" + user_master_id);
		if (password != null) {
			logger.info("password :", password);
			password = passEncrp.encrypt(password);
			logger.info("encr password :", password);
		}
		Date created_date = new Date();
		Date updated_date = new Date();
		userDao.addUser(id, user_name, password, created_by, created_by, created_date, updated_date, phone_no, email,
				user_master_id);
		userDao.updateSequenceId("UserDetails", id);
	}

	@POST
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
	public void updateUserDetails(@QueryParam("id") Long id, @QueryParam("user_name") String user_name,
			@QueryParam("password") String password, @QueryParam("updated_by") Long updated_by,
			@QueryParam("phone_no") String phone_no, @QueryParam("email") String email,
			@QueryParam("user_master_id") Long user_master_id) {
		logger.info("Update User :{}", id);
		logger.info("password :{}", password);
		if (password != null) {
			password = passEncrp.encrypt(password);
			logger.info("password passEncrp :{}", password);
		}
		Date updated_date = new Date();
		userDao.updateUserDetails(id, user_name, password, updated_by, updated_date, phone_no, email, user_master_id);
	}

	@GET
	@RequestMapping(value = "/deleteUserDetails", method = RequestMethod.GET)
	public void deleteUserDetails(@QueryParam("id") Long id) {
		logger.info("Delete User :{}", id);
		userDao.deleteUserDetails(id);
	}

	@GET
	@RequestMapping(value = "/getAllUserDetails", method = RequestMethod.GET)
	public List<UserDetails> getAllUserDetails() {
		logger.info("Get All User");
		List<UserDetails> userlist = userDao.getAllUserDetails();
		List<UserDetails> list = new ArrayList<UserDetails>();

		for (UserDetails listdata : userlist) {
			UserDetails userData = new UserDetails();
			userData.setId(listdata.getId());
			userData.setPassword(passEncrp.decrypt(listdata.getPassword()));
			userData.setUser_name(listdata.getUser_name());
			userData.setCreated_by(listdata.getCreated_by());
			userData.setUpdated_by(listdata.getUpdated_by());
			userData.setCreated_date(listdata.getCreated_date());
			userData.setUpdated_date(listdata.getUpdated_date());
			userData.setEmail(listdata.getEmail());
			userData.setPhone_no(listdata.getPhone_no());
			userData.setIs_deleted(listdata.isIs_deleted());
			userData.setUser_master_id(listdata.getUser_master_id());
			userData.setType(listdata.getType());
			list.add(userData);
		}
		logger.info("Get All User :{}", list.toString());
		return list;
	}

	@POST
	@RequestMapping(value = "/createForm", method = RequestMethod.POST)
	public void insertFormData(@QueryParam("form_detail") String form_detail, @QueryParam("form_id") String form_id,
			@QueryParam("created_by") Long created_by, @QueryParam("dealer_name") String dealer_name,
							   @QueryParam("state") String state,
							   @QueryParam("callStatus") String callStatus,
							   @QueryParam("district") String district,
							   @QueryParam("subDistrict") String subDistrict ) {
		logger.info("In create form");
		Long formMasterId = userDao.getUserId("FormMaster");
		Long formDetailsId = userDao.getUserId("FormDetails");
		formMasterId = formMasterId + 1;
		Date created_date = new Date();
		Date updated_date = new Date();
		userDao.createForm(formMasterId, form_id, created_by, dealer_name, created_date, updated_date, created_by,state,callStatus,district,subDistrict);

		formDetailsId = formDetailsId + 1;
		Long form_master_id = userDao.getFormId(form_id);

		byte[] formData = form_detail.getBytes();

		userDao.insertFormDetails(formDetailsId, formData, created_date, form_master_id, created_by);

		userDao.updateSequenceId("FormMaster", formMasterId);
		userDao.updateSequenceId("FormDetails", formDetailsId);
		logger.info("form inserted");
	}

	@GET
	@RequestMapping(value = "/deleteForm", method = RequestMethod.GET)
	public void deleteForm(@QueryParam("id") Long id) {
		logger.info("Delete Form :{}", id);
		userDao.deleteForm(id);
	}

	@GET
	@RequestMapping(value = "/updateForm", method = RequestMethod.POST)
	public void updateFormData(@QueryParam("id") Long id, @QueryParam("form_detail") String form_detail,
			@QueryParam("form_id") String form_id, @QueryParam("last_updated_by") Long last_updated_by,
			@QueryParam("dealer_name") String dealer_name, @QueryParam("fromMasterId") Long fromMasterId,
							               @QueryParam("state") String state,
	                                       @QueryParam("callStatus") String callStatus,
										   @QueryParam("district") String district,
										   @QueryParam("subDistrict") String subDistrict) {
		logger.info("update Form");
		logger.info("form_id " + form_id + " form_detail " + form_detail + "last_updated_by" + last_updated_by
				+ "dealer_name" + dealer_name + "fromMasterId" + fromMasterId);
		Date updated_date = new Date();
		Long formDetailsId = userDao.getUserId("FormDetails");
		formDetailsId = formDetailsId + 1;
		byte[] formData = form_detail.getBytes();
		userDao.updateFormMasterData(id, form_id, last_updated_by, updated_date, dealer_name,state,callStatus,district,subDistrict);
//		Long form_master_id = userDao.getFormId(form_id);
		
		userDao.insertFormDetails(formDetailsId, formData, updated_date, fromMasterId, last_updated_by);
		
		userDao.updateIsUpdated(fromMasterId);
		userDao.updateSequenceId("FormDetails", formDetailsId);
	}

	@GET
	@RequestMapping(value = "/getAllForms", method = RequestMethod.GET)
	public List<FormMaster> getAllForms() {
		logger.info("in get all survey forms");
		List<FormMaster> list = userDao.getAllForms();
		List<FormMaster> surveyFormData = new ArrayList<FormMaster>();
		for (FormMaster listData : list) {
			FormMaster formData = new FormMaster();
			formData.setId(listData.getId());
			formData.setForm_id(listData.getForm_id());
			formData.setDealer_name(listData.getDealer_name());
			formData.setCreated_by(listData.getCreated_by());
			formData.setCreated_date(listData.getCreated_date());
			formData.setIs_deleted(listData.isIs_deleted());
			formData.setLanguage_id(listData.getLanguage_id());
			formData.setLast_updated_by(listData.getLast_updated_by());
			String formString = new String(listData.getFormdetails().getForm_detail());
			formData.setFormData(formString);
			formData.setFormdetails(listData.getFormdetails());
			surveyFormData.add(formData);
		}
		logger.info("list" + list.toString());
		return surveyFormData;
	}

	@RequestMapping(value ="/getCallStatusReport", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	public HttpEntity<byte[]> downloadCallStatusReport(HttpServletResponse response) throws IOException {
		byte[] reportData=callStatusSerivce.createCallStatusReport();
		StringBuilder fileName=new StringBuilder("Call_Status_");
		SimpleDateFormat sdfName=new SimpleDateFormat("dd_MMM_YYYY_HH_mm_ss");
		fileName.append(sdfName.format(new Date()));
		fileName.append(".xlsx");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new HttpEntity<byte[]>(reportData, headers);
	}

}