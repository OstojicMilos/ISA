angular.module("isaProject")

.factory("EstablishmentService", function($http){
    
    return{
        
        getAllCinemas: function() {
			return $http({
				method: 'GET',
				url: 'http://localhost:8080/cinemas'
			})
        },

        getAllTheatres: function() {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/theatres'
            })
        },

        getCinemaRepertoire: function(establishmentId, dayOffset) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId +"/"+ dayOffset + '/events'
            })
        },
        
        getTheatreRepertoire: function(establishmentId) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events'
            })
        }
    }
});
