angular.module("isaProject")

.factory("EstablishmentService", ["$http", "$rootScope", function($http, $rootScope){
    
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

        getEstablishmentById: function(id) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + id
            })
        },
        
        getEstablishmentRating: function(id) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + id + '/rating'
            })
        },

        getEstablishmentIncome: function(id,data) {
            return $http({
                method: 'POST',
                url: 'http://localhost:8080/establishments/' + id + '/income',
                data: data
            })
        },

        updateEstablishment: function(id, data) {
            return $http({
                method: 'PUT',
                url: 'http://localhost:8080/establishments/' + id,
                data: data
            })
        },

        getAllCinemasEvents: function(id) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/cinemas/' + id + '/events'
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
        },

        getEvent: function(establishmentId, eventId) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events/' + eventId
            })
        },

        createEvent: function(establishmentId, data) {
            return $http({
                method: 'POST',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events',
                data: data
            })
        },

        updateEvent: function(establishmentId, eventId, data) {
            return $http({
                method: 'PUT',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events/' + eventId,
                data: data
            })
        },

        deleteEvent: function(establishmentId, eventId) {
            return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/events/' + eventId
            })
        },

        getHalls: function(establishmentId) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId +'/halls'
            })
        },

        addEventDetails: function(eventId, data) {
            return $http({
                method: 'POST',
                url: 'http://localhost:8080/events/' + eventId + '/details',
                data: data
            })
        },

        updateEventDetails: function(eventId, detailsId, data) {
            return $http({
                method: 'PUT',
                url: 'http://localhost:8080/events/' + eventId + '/details/' + detailsId,
                data: data
            })
        },

        deleteEventDetails: function(eventId, detailsId) {
            return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/events/' + eventId + '/details/' + detailsId
            })
        },

        getDiscountedTickets: function(establishmentId) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/establishments/' + establishmentId + '/discountedtickets'
            })
        },

        createDiscountedTicket: function(data) {
            return $http({
                method: 'POST',
                url: 'http://localhost:8080/discountedtickets',
                data: data
            })
        },

        deleteDiscountedTicket: function(id) {
            return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/discountedtickets/' + id,
            })
        },

        reserveDiscountedTicket: function(ticketId, data) {
            return $http({
                method: 'PUT',
                url: 'http://localhost:8080/discountedtickets/' + ticketId,
                data: data
            })
        },
        
        getSeatReservations: function(establishmentId, eventId, scheduleId) {
        	return $http({
        		method : 'GET',
        		url : 'http://localhost:8080/seatReservation/' + establishmentId + '/' + eventId + '/'+ scheduleId
        	})

        },
        
        confirmReservation: function(scheduleId, reservation) {
        	return $http({
        		method : 'POST',
        		url : 'http://localhost:8080/confirmReservation/'+ scheduleId , 
        		data : reservation 
        	})

        }
        
    }
}]);
