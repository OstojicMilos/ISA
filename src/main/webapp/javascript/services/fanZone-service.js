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

		reserveNewProp: function(propId){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZone/newProps/'+propId
			})
		}


	}
});