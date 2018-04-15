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

        getRepertoire: function(establishmentId) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events'
            })
        },

        createEvent: function(establishmentId, data) {
            return $http({
                method: 'POST',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events',
                data: data
            })
        }
    }
});
