angular.module('isaProject')
.factory('Friendship', function($http){
	return{
		
		addFriendship : function(friendship){
			return $http.post("/friendship", friendship).catch(angular.noop);
		}
	}
})