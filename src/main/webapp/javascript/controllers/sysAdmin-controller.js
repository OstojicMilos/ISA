angular.module("isaProject")

.controller("SysAdminController", [function(){

    self = this;

}])

.controller("NewTheatreController", ["$location", "SysAdmin", function($location, SysAdmin){

    self = this;

    self.admins = {};
    SysAdmin.getEstablishmentAdmins().then(function(data){
        self.admins = data.data;
    })

    self.register = function(theatre){
        var newTheatre = theatre;
        newTheatre.establishmentType = "THEATRE";
        SysAdmin.newTheatre(newTheatre).then(function(){
            $location.path("/adminSistema");
        });
    }
}])

.controller("NewCinemaController", ["$location", "SysAdmin", function($location, SysAdmin){

    self = this;

    self.admins = {};
    SysAdmin.getEstablishmentAdmins().then(function(data){
        self.admins = data.data;
    })

    self.register = function(cinema){
        var newCinema = cinema;
        newCinema.establishmentType = "CINEMA";
        SysAdmin.newCinema(newCinema).then(function(){
            $location.path("/adminSistema");
        });
    }
}])

.controller("NewAdminController", ["$location", "SysAdmin", function($location, SysAdmin){

    self = this;

    self.register = function(admin){
        if(admin.role == "Administrator sistema"){
            admin.role = "SYS_ADMIN";
        }else if(admin.role == "Administrator fan zone"){
            admin.role = "FAN_ZONE_ADMIN";
        }else{
            admin.role = "ESTABLISHMENT_ADMIN";
        }
        SysAdmin.newAdmin(admin).then(function(){
            $location.path("/adminSistema");
        });
    }

}])

.controller("PointScaleController", ["SysAdmin", function(SysAdmin){

    self = this;

    self.showNewCategoryForm = false;
    self.categories = {};
    SysAdmin.getCategories().then(function(data){
        self.categories = data.data;
    });

    self.newCategory = function(newCategory){
        SysAdmin.newCategory(newCategory).then(function(){
            SysAdmin.getCategories().then(function(data){
                self.categories = data.data;
                self.showNewCategoryForm = false;
                self.category.name = "";
                self.category.pointRequired = "";
            });
        });
    }
}]);