angular.module("isaProject")

.factory("SysAdmin", function($http, $rootScope){
	return{
		newAdmin: function(admin){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newAdmin',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: admin
			})
		},

		newCinema: function(cinema){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newCinema',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: cinema
			})
			
		},

		newTheatre: function(theatre){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/newTheatre',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: theatre
			})
		},

		getEstablishmentAdmins: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/sysAdmin/users/establishmentAdmins',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		},

		newCategory: function(newCategory){
			return $http({
				method: 'POST',
				url: 'http://localhost:8080/sysAdmin/privilegedUserCategories',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
				data: newCategory
			})
		},

		getCategories: function(){
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/sysAdmin/privilegedUserCategories',
				headers: {
					'Authorization': $rootScope.user.email+":"+$rootScope.user.password
				},
			})
		}
	}
});