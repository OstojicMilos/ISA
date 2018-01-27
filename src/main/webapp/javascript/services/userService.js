angular.module("isaProject")
.factory('User',function($http){
	return {
		
		registerNewUser : function(user){
			return $http.post("/register", user).catch(angular.noop);
		},
		

		activateNewAccount : function(token){
			console.log(token);
			return $http.post("/activate/"+token).catch(angular.noop);
		}
	
		
	}
})