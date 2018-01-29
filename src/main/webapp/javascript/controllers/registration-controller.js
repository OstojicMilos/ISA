angular.module("isaProject")
.controller('RegistrationController',['User','$scope', '$rootScope', function(User ,$scope, $rootScope){
	
	self = this;
	
	self.registerNewUser = function(){
		User.registerNewUser(self.user).then(function(response){
			
		})
	}
	
}])

.controller('ActivationController',['User','$scope', '$rootScope','$routeParams', function(User ,$scope, $rootScope, $routeParams){
	
	self = this;
	activated = {};
	
	User.activateNewAccount($routeParams.token).then(function(response){
		if(response.data){
			self.activated = true;
		}
		else{
			self.activated = false;
		}
	})
	
	
}]);