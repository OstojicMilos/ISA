angular.module('isaProject')
.factory('Friendship', function($http){
	return{
		
		addFriendship : function(friendship){
			return $http.post("/friendship", friendship).catch(angular.noop);
		},
		
		findPendingRequestsFor : function(user){
			return $http.post("/pending", user).catch(angular.noop);
		},
		
		confirmFriendship : function(friendship){
			return $http.put("/confirmRequest", friendship).catch(angular.noop);
		},
		
		deleteFriendship : function(friendship){
			console.log(friendship);
			return $http.put("/friendship",friendship).catch(angular.noop);
		},
		
		findFriendsFor : function(user){
			return $http.post("/friends", user).catch(angular.noop);
		}
		
	}
})