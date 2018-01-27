angular.module("isaProject")
.factory('User',function($http){
	return {
		
		registerNewUser : function(user){
			return $http.post("/register", user).catch(angular.noop);
		},
		

		activateNewAccount : function(token){
			return $http.post("/activate/"+token).catch(angular.noop);
		},
		
		logIn : function(credentials){
			return $http.post("/login", credentials).catch(angular.noop);
		}
	
		
	}
})