angular.module("isaProject")

.factory("FanZone", function($http){
	return{
		getOfficialProps: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps'
			})
		},

		createNewUserAd: function(newUserAd){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZone/userAds',
				data: newUserAd
			})
		},

		getActiveUserAds: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/userAds'
			})
		},

		getUserAd: function(userAdId){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId
			})
		},

		reserveNewProp: function(propId, email){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZone/newProps/'+propId,
				data: email
			})
		},

		deleteOffer: function(userAdId, offerId){
			return $http({
				method: 'DELETE',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/offers/'+offerId
			})
		},

		placeOffer: function(userAdId, offerId, offeredSum, userId){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/users/'+userId+'/offers/'+offerId,
				data: offeredSum
			})
		},

		acceptOffer: function(userAdId, offerId){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZone/userAds/'+userAdId+'/offers/'+offerId
			})
		},

		getNotifications: function(userId){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZone/users/'+userId+'/notifications'
			})
		}
	}
});