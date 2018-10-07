upl_survey.controller("surveyController", [
	'$scope',
	'surveyService',
	'sourceObject',
	'$location',
	'surveyListService',
	function($scope, surveyService,sourceObject, $location, surveyListService) {
		
		//var data=[];	
		$scope.isShow = false;
		
		$scope.data = angular.copy(sourceObject.formData);
		
		if($location.path() == '/addSurvey'){
			$scope.isShow = true;
			$scope.data = null;
		}
		
		
		$scope.calls = [
			{id : "a. Successful", name: "a. Successful"},
			{id : "b. Partial Refusal", name: "b. Partial Refusal"},
			{id : "c. Refusal", name: "c. Refusal"},
			{id : "d. Mid-course termination", name: "d. Mid-course termination"}];
		
		$scope.setups = [
			{id : "a. Proprietorship", name: "a. Proprietorship"},
			{id : "b. Partnership", name: "b. Partnership"},
			{id : "c. Private Limited Company", name: "c. Private Limited Company"},
			{id : "d. Public Limited Company", name: "d. Public Limited Company"},
			{id : "e. Co-operative", name: "e. Co-operative"}];
		
		$scope.designations = [
			{id : "a. Partner", name: "a. Partner"},
			{id : "b. Proprietor", name: "b. Proprietor"},
			{id : "c. Manager", name: "c. Manager"},
			{id : "d. Director", name: "d. Director"},
			{id : "e. Others", name: "e. Others"}];
		
		$scope.educations = [
			{id : "a. Illiterate", name: "a. Illiterate"},
			{id : "b. Literate, but no formal schooling", name: "b. Literate, but no formal schooling"},
			{id : "c. Up to 4th Standard", name: "c. Up to 4th Standard"},
			{id : "d. 5th to 9th Standard", name: "d. 5th to 9th Standard"},
			{id : "e. SSC/HSC", name: "e. SSC/HSC"},
			{id : "f. Some college but not graduate", name: "f. Some college but not graduate"},
			{id : "g. Graduate/Post Graduate - General", name: "g. Graduate/Post Graduate - General"},
			{id : "h. Graduate/Post Graduate - Professional", name: "h. Graduate/Post Graduate - Professional"}];
		
//		console.log("In survey controller");
		
		$scope.gotoUserList= function() {
			$location.url('/surveyList');
		}

		$scope.editSurveyDetails = function(row) {
//			console.log("in edit user");
//			console.log("row :",row);
			sourceObject.formData = jQuery.parseJSON(row.formData);
//			console.log(sourceObject.formData);
			sourceObject.formData['fromMasterId'] = row.id;
			sourceObject.formData['id'] = row.formdetails.id;
//			console.log(row.formdetails.id);
			$location.url('/editSurvey');
		}

		$scope.updateForm = function(data) {
//			console.log("In update function");
//			console.log(data);
//			console.log("In create function"+data.toString());
			var form_detail = angular.toJson(data);
//			console.log(form_detail);
			$scope.last_updated_by = sourceObject.currentUser.id;
			surveyService.updateForm(data.id, form_detail,
					data.B1, $scope.last_updated_by, data.A3, data.fromMasterId,data.State5,data.A1,
                                                                                				data.district6,
                                                                                				data.subdistrict7).then(
				function(response) {
//					console.log(response);
					$scope.userDetails = response.data.items;
					$location.url('/surveyList');
				}, function(response) {
					$scope.errors = response.data.errorMessages;
				})
		}

		$scope.deleteSurveyModel = function(id) {
			$scope.selectedId=id;
		}

		$scope.deleteSurveyDetails = function() {
//			console.log("In function");
			surveyService.deleteForm($scope.selectedId).then(function(response) {
				$scope.selectedId = null;
				$('#deleteSurveyModal').modal('hide');
				$scope.getAllForms();
				//$location.url('/surveyList');
			}, function(response) {
				$scope.errors = response.data.errorMessages;
			})
		}
		

		$scope.addSurvey = function() {
			//$scope.data = null;
			sourceObject.formData=null;
			//$scope.data = null;
			$location.url('/addSurvey');
		}

		$scope.createNewSurvey = function(data) {
//			console.log("data",data);
//			console.log("In create function",data.toString());
			var form_detail = angular.toJson(data);
//			console.log("from details before to string", form_detail);
			$scope.created_by = sourceObject.currentUser.id;
			//form_detail = data.toString();
//			console.log("from details :"+form_detail);
			surveyService.createForm(form_detail, data.B1,
				$scope.created_by, data.A3,data.State5,data.A1,
				data.district6,
				data.subdistrict7).then(function(response) {
//				console.log(response);
				$location.url('/surveyList');
			}, function(response) {
				$scope.errors = response.data.errorMessages;
			})
		}
		
		$scope.getAllForms = function() {
//			console.log("In getAllForms function");
			surveyListService.getAllForms()
				.then(function(response) {
//					console.log(response);
					$scope.surveyDetails = response.data;
					//$location.url('/surveyList');
				}, function(response) {
					$scope.errors = response.data.errorMessages;
				})
		}

	} ]);

upl_survey.service('surveyService', [
	'$http',
	function($http) {

		this.updateForm = function updateForm(id, form_detail, form_id,
			last_updated_by, dealer_name, fromMasterId,state,callStatus,district,subDistrict) {
			return $http({
				method : 'POST',
				url : '/DealerCensus/upl_survey/updateForm',
				params : {
					id : id,
					form_detail : form_detail,
					form_id : form_id,
					last_updated_by : last_updated_by,
					dealer_name : dealer_name,
					fromMasterId : fromMasterId,
					state : state,
                    callStatus : callStatus,
                    district : district,
                    subDistrict : subDistrict
				}
			});
		}

		this.deleteForm = function deleteForm(id) {
			return $http({
				method : 'GET',
				url : '/DealerCensus/upl_survey/deleteForm',
				params : {
					id : id
				}
			});
		}

		this.createForm = function createForm(form_detail, form_id,
			created_by,dealer_name, state,callStatus,district,subDistrict){
						return $http({
				method : 'POST',
				url : '/DealerCensus/upl_survey/createForm',
				params : {
					form_detail : form_detail,
					form_id : form_id,
					created_by : created_by,
					dealer_name : dealer_name,
					state : state,
                    callStatus : callStatus,
                    district : district,
                    subDistrict : subDistrict
				}
			});
		}


	} ]);