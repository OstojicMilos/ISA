angular.module("isaProject")

.controller("FanZoneAdminController", [function(){
    
    self = this;

}])

.controller("FanZoneAdminNewPropController", ["FanZoneAdmin", function(FanZoneAdmin){

    self = this;

    self.establishments = {};
    FanZoneAdmin.getEstablishments().then(function(data){
        self.establishments = data.data;
    })

    self.createNewProp = function(newProp){
        var prop = newProp;
        prop.available = true;
        FanZoneAdmin.newProp(prop).then(function(){});
    }
}])

.controller("FanZoneAdminOfficialPropsController", [function(){

    self = this;
}])

.controller("FanZoneAdminUsedPropsController", [function(){

    self = this;
}])

.controller("FanZoneAdminUpdateDataController", ["FanZoneAdmin", function(FanZoneAdmin){

    self = this;

    self.update = function(admin){
        FanZoneAdmin.update(admin).then(function(){});
    }
}]);