angular.module("isaProject")

.factory("FanZone", function($http){
	return{
		test: function(){
			return "radi service";
		},

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
		}
	}
});