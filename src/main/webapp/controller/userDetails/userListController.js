upl_survey.controller("userListController", [
	'$scope',
	'$location',
	'userListService',
	'sourceObject',
	function($scope, $location, userListService, sourceObject) {
//		console.log("In user list controller");

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

		$scope.getAllUserDetails();

	} ]);
upl_survey.service('userListService', [
	'$http',
	function($http) {
		this.getAllUserDetails = function getAllUserDetails() {
			return $http({
				method : 'GET',
				url : '/DealerCensus/upl_survey/getAllUserDetails',
			});
		}
	} ]);