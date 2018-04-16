angular.module("isaProject")
.controller("CinemasController", ["EstablishmentService", function(EstablishmentService) {
    
    var self = this;
    (function () {
        EstablishmentService.getAllCinemas()
            .then(function(response) {
                self.cinemas = response.data;
            })
    })();
    
}])

.controller("TheatresController", ["EstablishmentService", function(EstablishmentService) {
    var self = this;
    (function () {
        EstablishmentService.getAllTheatres()
            .then(function(response) {
                self.theatres = response.data;
            })
    })();
}])

.controller("RepertoireController", ["repertoirePromise", "EstablishmentService", "$route", "$routeParams", function(repertoirePromise, EstablishmentService, $route, $routeParams) {
    
    var self = this;
    self.projections = repertoirePromise;
    self.establishmentId = $routeParams.id;

    self.newEvent = {};
    self.addEvent = function() {
        EstablishmentService.createEvent($routeParams.id, self.newEvent)
            .then(function(response) {
                self.projections.push(response.data);
                self.newEvent = {};
            });
    };
    
    self.deleteEvent = function(eventId) {
        EstablishmentService.deleteEvent($routeParams.id, eventId)
            .then(function(response) {
                $route.reload();
            });
    };

}])

.controller("EditRepertoireController", ["$location", "$routeParams", "EstablishmentService", function($location, $routeParams, EstablishmentService) {
    var self = this;
    self.alertMessage = "";

    (function() {
        EstablishmentService.getEvent($routeParams.establishmentId, $routeParams.eventId)
            .then(function(response) {
                self.event = response.data;
            })
    })();

    self.update = function() {
        EstablishmentService.updateEvent($routeParams.establishmentId, $routeParams.eventId, self.event).then(function(response) {
            self.alertMessage = "Ažuriranje uspešno";
        })
    }

    self.cancel = function() {
        $location.path('/repertoar/'+ $routeParams.establishmentId);
    }
}])