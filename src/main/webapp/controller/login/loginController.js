upl_survey.controller("loginController", [
	'$scope',
	'$rootScope',
	'$location',
	'loginService',
	'sourceObject',
	function($scope, $rootScope, $location, loginService, sourceObject) {
//		console.log("In login controller");
		$scope.error = null;
		$scope.loginUser = function() {
//			console.log($scope.email, $scope.password);
//			console.log("In loginUser function");
			loginService.loginUser($scope.email, $scope.password).then(
				function(response) {
//					console.log(response);
					if (response.data != null && response.data != "" && response.data.type != undefined) {
						sourceObject.currentUser = response.data;
						localStorage.setItem('currentUser', JSON.stringify(response.data));
						$rootScope.currentUser = JSON.parse(localStorage.getItem('currentUser'));
						if (response.data.type == 'SYSADMIN') {
							$location.url('/userList');
						} else if (response.data.type == 'ADMIN') {
							$location.url('/userList');
						} else {
							$location.url('/surveyList');
						}
//						console.log("source :", sourceObject.currentUser);
					}else{
						$scope.error = "In valid login / password";
					}
//					console.log("source :", sourceObject.currentUser);
				}, function(errResponse) {
					$scope.error = "In valid login / password";
//					console.log(errResponse)
//					console.log('Invalid User');
				})
		}
	} ]);

upl_survey.service('loginService', [ '$http', function($http) {

	this.loginUser = function loginUser(email, password) {
//		console.log(email, password);
		return $http({
			method : 'POST',
			dataType : 'json',
			headers : {
				"Content-Type" : "application/json"
			},
			url : '/DealerCensus/upl_survey/loginUser',
			params : {
				email : email,
				password : password
			}
		});
	}
} ]);