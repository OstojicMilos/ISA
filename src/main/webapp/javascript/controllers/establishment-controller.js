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

.controller("RepertoireController", ["repertoirePromise","$route", function(repertoirePromise, $route) {
    
    var self = this;
    var today = new Date();
    var today_plus1 = new Date(); today_plus1.setDate(today.getDate() + 1);
    var today_plus2 = new Date(); today_plus2.setDate(today.getDate() + 2);
    var today_plus3 = new Date(); today_plus3.setDate(today.getDate() + 3);
    var today_plus4 = new Date(); today_plus4.setDate(today.getDate() + 4);
    
    self.days = [today,
    			today_plus1,
    			today_plus2,
    			today_plus3,
    			today_plus4];
 
    self.cinemaId = $route.current.params.id;
   
    
    self.projections = repertoirePromise;
    console.log(self.projections);
    
}]);