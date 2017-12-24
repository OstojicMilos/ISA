angular.module("isaProject")

.factory("SysAdmin", function($http){
	return{
		newAdmin: function(admin){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newAdmin',
				data: admin
			})
		},

		newCinema: function(cinema){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newCinema',
				data: cinema
			})
			
		},

		newTheatre: function(theatre){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newTheatre',
				data: theatre
			})
		},

		getEstablishmentAdmins: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/sysAdmin/users/establishmentAdmins'
			})
		},

		newCategory: function(newCategory){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/privilegedUserCategories',
				data: newCategory
			})
		},

		getCategories: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/sysAdmin/privilegedUserCategories'
			})
		}
	}
});