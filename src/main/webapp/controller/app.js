var upl_survey = angular.module("upl_survey", [ 'ngRoute', 'ngResource',
		'ui.select' ])

.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$locationProvider.html5Mode(true).hashPrefix('');
			$routeProvider.

			when('/', {
				templateUrl : '/DealerCensus/view/login/loginForm.html',
				controller : 'loginController'
			}).

			when('/surveyList', {
				templateUrl : '/DealerCensus/view/surveyDetails/surveyList.html',
				controller : 'surveyListController'
			}).

			when('/editSurvey', {
				templateUrl : '/DealerCensus/view/surveyDetails/addSurvey.html',
				controller : 'surveyController'
			}).

			when('/addSurvey', {
				templateUrl : '/DealerCensus/view/surveyDetails/addSurvey.html',
				controller : 'surveyController'
			}).

			when('/userList', {
				templateUrl : '/DealerCensus/view/userDetails/userList.html',
				controller : 'userListController'
			}).

			when('/editUser', {
				templateUrl : '/DealerCensus/view/userDetails/addUser.html',
				controller : 'userController'
			}).

			when('/addUser', {
				templateUrl : '/DealerCensus/view/userDetails/addUser.html',
				controller : 'userController'
			}).


			otherwise({
				redirectTo : '/'
			});

		} ])

.factory('sourceObject',[
	function(){
	  return {
		  currentUser : '',
		  userData :'',
		  formData:''
	  }
	}]);
