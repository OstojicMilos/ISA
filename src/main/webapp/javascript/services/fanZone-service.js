angular.module("isaProject")

.factory("FanZone", function($http, $rootScope){
	return{
		getOfficialProps: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		createNewUserAd: function(newUserAd){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZone/userAds',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: newUserAd
			})
		},

		getActiveUserAds: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/userAds',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		getUserAd: function(userAdId){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		reserveNewProp: function(propId, email){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZone/newProps/'+propId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: email
			})
		},

		deleteOffer: function(userAdId, offerId){
			return $http({
				method: 'DELETE',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/offers/'+offerId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		placeOffer: function(userAdId, offerId, offeredSum, userId){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/users/'+userId+'/offers/'+offerId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: offeredSum
			})
		},

		acceptOffer: function(userAdId, offerId){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/offers/'+offerId,
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		getNotifications: function(userId){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/users/'+userId+'/notifications',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},
		
		uploadImage: function(file){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/imageUpload',
				data: file,
				transformRequest: angular.identity,
				headers: {
					'Content-Type': undefined
				}
			});
		}
	}
});