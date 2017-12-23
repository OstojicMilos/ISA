angular.module("isaProject")

.factory("FanZoneAdmin", function($http){
	return{
		test: function(){
			return "radi service";
		},

		update: function(admin){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZoneAdmin/updateData',
				data: admin
			})			
		},

		newProp: function(newProp){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps',
				data: newProp
			})
		},

		getEstablishments: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/establishments'
			})
		}
	}
});