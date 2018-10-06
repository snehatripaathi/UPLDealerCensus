upl_survey.controller("userController", [
	'$scope',
	'$location',
	'userService',
	'sourceObject',
	'userListService',
	function($scope, $location, userService, sourceObject, userListService) {
//		console.log("In user controller");
		$scope.data = [];
		$scope.isShow = false;
		
		if($location.path() == '/addUser'){
			$scope.isShow = true;
		}
		
		$scope.data = angular.copy(sourceObject.userData);
		$scope.roles = [{id : "ADMIN", name: "ADMIN"},
						{id : "USER", name: "USER"},
						{id : "SURVEYOR", name: "SURVEYOR"}]
//		$scope.roles = ["ADMIN","USER", "SURVEYOR","SYSADMIN"];
//		console.log($scope.data);
		
		$scope.addUser = function() {
			sourceObject.userData=null;
			$scope.data = null;
			$location.url('/addUser');
		}
		
		$scope.gotoUserList = function(){
			$location.url('/userList');
		}

		$scope.addNewUser = function() {
//			console.log("In add function");

			$scope.created_by = sourceObject.currentUser.id;
//			console.log($scope.data);
			if ($scope.data.type == "ADMIN") {
				$scope.data.type = 2;
			} else if ($scope.data.type == "USER") {
				$scope.data.type = 3;
			} else {
				$scope.data.type = 4;
			}
			$scope.user_master_id = $scope.data.type;
			userService.addUser($scope.data.user_name, $scope.data.password, $scope.created_by, $scope.data.phone_no, $scope.data.email,
				$scope.user_master_id).then(function(response) {
//				console.log(response);
				$location.url('/userList');
			}, function(response) {
				$scope.errors = response.data.errorMessages;
//				console.error('Error while creating User');
			})
		}

		$scope.editUserDetails = function(row) {
//			console.log("in edit user");
//			console.log("row :",row);
			sourceObject.userData = row;
			$scope.data = row
//			sourceObject.userData = $scope.data;
			$location.url('/editUser');
		}

		$scope.updateUserDetails = function() {
//			console.log("In updateUserDetails function");
			
			$scope.updated_by = sourceObject.currentUser.id;
			if ($scope.data.type == "ADMIN") {
				$scope.data.type = 2;
			} else if ($scope.data.type == "USER") {
				$scope.data.type = 3;
			} else {
				$scope.data.type = 4;
			}
			$scope.user_master_id = $scope.data.type;
			userService.updateUserDetails($scope.data.id, $scope.data.user_name , $scope.data.password,
				$scope.updated_by, $scope.data.phone_no, $scope.data.email,
				$scope.user_master_id).then(function(response) {
//				console.log(response);
				$location.url('/userList');
			}, function(response) {
				$scope.errors = response.data.errorMessages;
//				console.error('Error while creating User');
			})
		}

		$scope.deleteUserModel = function(id) {
			$scope.selectedId=id;
		}

		$scope.deleteUserDetails = function() {
//			console.log("In delete function");
			userService.deleteUserDetails($scope.selectedId).then(
				function(response) {
//					console.log(response);
					$scope.userDetails = response.data.items;
					$scope.selectedId = null;
					$('#deleteUserModal').modal('hide');
					$scope.getAllUserDetails();
					$location.url('/userList');
				}, function(response) {
					$scope.errors = response.data.errorMessages;
//					console.error('Error while creating User');
				})
		}
		
		$scope.getAllUserDetails = function() {
//			console.log("In getAllUserDetails function");
			userListService.getAllUserDetails().then(
				function(response) {
//					console.log(response);
					if (response.data != null && response.data != "") {
						$scope.userDetails = response.data;
//						console.log($scope.userDetails);
					}
				}, function(response) {
					$scope.errors = response.data.errorMessages;
//					console.error('Error while creating User');
				})
		}

	} ]);

upl_survey.service('userService', [
	'$http',
	function($http) {

		this.addUser = function addUser(user_name, password, created_by, phone_no,
			email, user_master_id) {
//			console.log("In add user service");
			return $http({
				method : 'POST',
				url : '/DealerCensus/upl_survey/addUser',
				params : {
					user_name : user_name,
					password : password,
					created_by : created_by,
					phone_no : phone_no,
					email : email,
					user_master_id : user_master_id
				}
			});
		}

		this.updateUserDetails = function updateUserDetails(id, user_name, password,
			updated_by, phone_no, email, user_master_id) {
			return $http({
				method : 'POST',
				url : '/DealerCensus/upl_survey/updateUserDetails',
				params : {
					id : id,
					user_name: user_name,
					password : password,
					updated_by : updated_by,
					phone_no : phone_no,
					email : email,
					user_master_id : user_master_id
				}
			});
		}

		this.deleteUserDetails = function deleteUserDetails(id) {
			return $http({
				method : 'GET',
				url : '/DealerCensus/upl_survey/deleteUserDetails',
				params : {
					id : id
				}
			});
		}

	} ]);