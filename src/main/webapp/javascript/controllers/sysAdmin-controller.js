angular.module("isaProject")

.controller("SysAdminController", [function(){

    self = this;

}])

.controller("NewTheatreController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.register = function(theatre){

    }
}])

.controller("NewCinemaController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.register = function(cinema){

    }
}])

.controller("NewAdminController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.register = function(admin){
        console.log(admin);
    }

}]);