angular.module('isaProject')
.controller('LoginController', ['User', '$scope', '$rootScope', '$location',
	function(User, $scope, $rootScope, $location){
	
	self = this;
	
	self.logIn = function(){
		User.logIn(self.loginInfo).then(function(response){
			if(response.data != ""){
				$rootScope.loggedIn = true;
				$rootScope.user = {};
				$rootScope.user.email = response.data.email;
				$rootScope.user.id = response.data.id;
				$rootScope.user.name = response.data.name;
				$rootScope.user.surname = response.data.surname;
				$rootScope.user.city = response.data.city;
				$rootScope.user.phoneNumber = response.data.phoneNumber;
				$rootScope.user.role = response.data.role;
				$rootScope.user.password = response.data.password;
				$rootScope.user.activated = response.data.activated;
				if(!$rootScope.user.activated && $rootScope.user.role == "FAN_ZONE_ADMIN")
					$location.path("/adminFanZone/izmenaLozinke");
				else
					$location.path("/");				
			}
		})
	}
	
}])