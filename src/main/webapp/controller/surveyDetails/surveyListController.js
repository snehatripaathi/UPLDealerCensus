upl_survey.controller("surveyListController", [
	'$scope',
	'surveyListService',
	'sourceObject',
	'$location',
	function($scope, surveyListService, sourceObject, $location) {
//		console.log("In survey controller");
		$scope.getAllForms = function() {
//			console.log("In getAllForms function");
			surveyListService.getAllForms()
				.then(function(response) {
//					console.log(response);
					if (response.data != null && response.data != "") {
						$scope.surveyDetails = response.data;
//						console.log($scope.userDetails);
					}
					$location.url('/surveyList');
				}, function(response) {
					$scope.errors = response.data.errorMessages;
				})
		}
		$scope.getAllForms();

	} ]);

upl_survey.service('surveyListService', [
	'$http',
	function($http) {

		this.getAllForms = function getAllForms() {
			return $http({
				method : 'GET',
				url : '/DealerCensus/upl_survey/getAllForms',
			});
		}
	} ]);