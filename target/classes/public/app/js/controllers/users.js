angular.module('myApp')

.controller('UsersController', function($http, $scope, AuthService) {
	var edit = false;
	$scope.buttonText = 'Create';
	var init = function() {
		$http.get('api/users').success(function(res) {
			$scope.users = res;
			
			$scope.userForm.$setPristine();
			$scope.message='';
			$scope.user = null;
			$scope.buttonText = 'Create';
			
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.initEdit = function(user) {
		edit = true;
		$scope.user = user;
		$scope.message='';
		$scope.buttonText = 'Update';
	};
	$scope.initAddUser = function() {
		edit = false;
		$scope.user = null;
		$scope.userForm.$setPristine();
		$scope.message='';
		$scope.buttonText = 'Create';
	};
	$scope.deleteUser = function(user) {
		$http.delete('api/users/'+user.id).success(function(res) {
			$scope.deleteMessage ="Success!";
			init();
		}).error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	var editUser = function(){
		$http.put('api/users', $scope.user).success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.message = "Editing Success";
			init();
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	var addUser = function(){
		$http.post('api/users', $scope.user).success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.message = "User Created";
			init();
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.submit = function() {
		if(edit){
			editUser();
		}else{
			addUser();	
		}
	};
	init();

});
