angular.module("isaProject")

.controller("SysAdminController", [function(){

    self = this;

}])

.controller("NewTheatreController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.admins = {};
    SysAdmin.getEstablishmentAdmins().then(function(data){
        self.admins = data.data;
    })

    self.register = function(theatre){
        var newTheatre = theatre;
        newTheatre.establishmentType = "THEATRE";
        SysAdmin.newTheatre(newTheatre).then(function(){});
    }
}])

.controller("NewCinemaController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.admins = {};
    SysAdmin.getEstablishmentAdmins().then(function(data){
        self.admins = data.data;
    })

    self.register = function(cinema){
        var newCinema = cinema;
        newCinema.establishmentType = "CINEMA";
        SysAdmin.newCinema(newCinema).then(function(){});
    }
}])

.controller("NewAdminController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.register = function(admin){
        if(admin.role == "Administrator sistema"){
            admin.role = "SYS_ADMIN";
        }else if(admin.role == "Administrator fan zone"){
            admin.role = "FAN_ZONE_ADMIN";
        }else{
            admin.role = "ESTABLISHMENT_ADMIN";
        }
        SysAdmin.newAdmin(admin).then(function(){});
    }

}]);