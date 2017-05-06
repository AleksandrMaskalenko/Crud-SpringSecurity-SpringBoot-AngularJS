angular.module('myApp')

.controller('RegisterController', function($http, $scope, AuthService) {
	$scope.submit = function() {
		$http.post('register', $scope.user).success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.register.$setPristine();
			$scope.message = "Registration successfull !";
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
});
