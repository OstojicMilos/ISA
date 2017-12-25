angular.module("isaProject")

.factory("FanZoneAdmin", function($http){

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
		},
		
		getOfficialProps: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps'
			})
		},

		deleteOfficialProp: function(propId){
			return $http({
				method: 'DELETE',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps/'+propId
			})
		},

		updateOfficialProp: function(prop){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/officialProps/'+prop.id,
				data: prop
			})
		},

		getPendingUserAds: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/fanZoneAdmin/userAds'
			})
		},

		updateUserAdStatus: function(userAd){
			return $http({
				method: 'PUT',
				url: 'http://localhost:8080/fanZoneAdmin/userAds/' + userAd.id,
				data: userAd
			})
		}
	}
});