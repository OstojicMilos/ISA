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
		},
		
		update : function(user){
			return $http.post("/account/updateData", user).catch(angular.noop);
		},
		
		searchForUser : function(criteria){
			return $http.get("/search/"+criteria);
		},
		
		getReservationsAsOwner : function(userId){
			return $http.get("/owner/reservations/" + userId);
		},
		
		getReservationsAsGuest : function(userId){
			return $http.get("/owner/reservations/" + userId);
		},

		getFastReservations: function(userId) {
			return $http.get("fasttickets/" + userId);
		},

		updateRating : function(resId, ratings) {
			return $http.put("/reservations/" + resId + "/rating", ratings);
		},

		updateFastRating: function(ticketId, ratings) {
			return $http.put("/discountedtickets/" +ticketId + "/rating", ratings)
		}
	
		
	}
})