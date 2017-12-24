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
		}
	}
});