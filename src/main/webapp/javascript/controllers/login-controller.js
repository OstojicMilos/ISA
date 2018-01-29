angular.module('isaProject')
.controller('LoginController', ['User', '$scope', '$rootScope', '$location',
	function(User, $scope, $rootScope, $location){
	
	self = this;
	self.logIn = function(){
		User.logIn(self.credentials).then(function(response){
			if(response.data != ""){
				
				$rootScope.user = {};
				$rootScope.loggedIn = true;
				$rootScope.user.email = response.data.email;
				$rootScope.user.id = response.data.id;
				$rootScope.user.name = response.data.name;
				$rootScope.user.surname = response.data.surname;
				$rootScope.user.city = response.data.city;
				$rootScope.user.phoneNumber = response.data.phoneNumber;
				$rootScope.user.role = response.data.role;
				$location.path("/");
			}
		})
	}
	
	
	
}])