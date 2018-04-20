angular.module("isaProject")

.factory("FanZoneAdmin", function($http, $rootScope){

	var propForUpdate = {};

	return{
		getPropForUpdate: function(){
			return propForUpdate;
		},

		setPropForUpdate: function(prop){
			propForUpdate = prop;
		},

		update: function(admin){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/update',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: admin
			})			
		},

		changePassword: function(userId, password){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/changePassword/'+userId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: password
			})
		},
		
		newProp: function(newProp){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: newProp
			})
		},

		getEstablishments: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/establishments',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},
		
		getOfficialProps: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		deleteOfficialProp: function(propId){
			return $http({
				method: 'DELETE',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps/'+propId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		updateOfficialProp: function(prop){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps/'+prop.id,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: prop
			})
		},

		getPendingUserAds: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/userAds',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		updateUserAdStatus: function(userAd){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/userAds/' + userAd.id,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: userAd
			})
		}
	}
});