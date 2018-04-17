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
    self.success = "";
    self.error = "";

    self.flags = {
        "edit": false,
        "preview": false,
        "add": false
    };

    self.toggleEditView = function() {
        self.flags.edit = true;
        self.flags.preview = false;
        self.flags.add = false;
    };

    self.togglePreviewView = function() {
        self.flags.edit = false;
        self.flags.preview = true;
        self.flags.add = false;
    };

    self.toggleAddView = function() {
        self.flags.edit = false;
        self.flags.preview = false;
        self.flags.add = true;
    };

    (function() {
        EstablishmentService.getEvent($routeParams.establishmentId, $routeParams.eventId)
            .then(function(response) {
                self.event = response.data;
            })
    })();
    (function() {
        EstablishmentService.getHalls($routeParams.establishmentId)
            .then(function(response) {
                self.halls = response.data;
                console.log(self.halls);
            });
    })()

    self.updateEvent = function() {
        EstablishmentService.updateEvent($routeParams.establishmentId, $routeParams.eventId, self.event).then(function(response) {
            self.alertMessage = "Ažuriranje uspešno";
        })
    };

    self.projection = {};
    self.addProjection = function() {
        var projectionDTO = {};
        projectionDTO.price = self.projection.price;
        projectionDTO.halls = [parseInt(self.projection.selectedHall)];

        var day = self.projection.date.getDate();
        var month = self.projection.date.getMonth() + 1;
        var year = self.projection.date.getFullYear();
        var date = year + '-' + month + '-' + day;

        var hours = self.projection.time.getHours();
        var minutes = self.projection.time.getMinutes();
        var time = hours + ':' + minutes + ':00';
        
        var dateAndTime = date + 'T' + time;
        projectionDTO.date = dateAndTime;
        
        EstablishmentService.addEventDetails($routeParams.eventId, projectionDTO)
            .then(function(response) {
                self.success = "Uspešno dodata projekcija";
                self.error = "";
            }, 
            function(error) {
                self.success = "";
                self.error = "Sala je zauzeta u traženom terimnu";
            })
    }

    self.cancel = function() {
        $location.path('/repertoar/'+ $routeParams.establishmentId);
    };
}])