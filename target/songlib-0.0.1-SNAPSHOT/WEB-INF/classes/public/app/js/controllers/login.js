angular.module('myApp')

.controller('LoginController', function($http, $scope, $state, AuthService, $rootScope) {

	$scope.login = function() {

		var base64Credential = btoa($scope.username + ':' + $scope.password);

		$http.get('user', {
			headers : {

				'Authorization' : 'Basic ' + base64Credential
			}
		}).success(function(res) {
			$scope.password = null;
			if (res.authenticated) {
				$scope.message = '';

				$http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
				AuthService.user = res;
				$rootScope.$broadcast('LoginSuccessful');
				$state.go('home');
			} else {
				$scope.message = 'Authetication Failed !';
			}
		}).error(function(error) {
			$scope.message = 'Authetication Failed !';
		});
	};

	$scope.logout = function() {

		$http.defaults.headers.common['Authorization'] = null;
		$scope.user = null;
		$scope.message = 'Successfully logged out';
		$scope.resource = null;
	};
});
