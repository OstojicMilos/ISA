angular.module("isaProject")

.controller("FanZoneAdminController", [function(){
    
    var self = this;

}])

.controller("FanZoneAdminNewPropController", ["$location", "FanZoneAdmin", function($location, FanZoneAdmin){

    var self = this;

    self.establishments = {};
    FanZoneAdmin.getEstablishments().then(function(data){
        self.establishments = data.data;
        console.log(data.data);
    })

    self.createNewProp = function(newProp){
        var prop = newProp;
        prop.available = true;
        FanZoneAdmin.newProp(prop).then(function(){
            $location.path("/zvanicniRekviziti");
        });
    }
}])

.controller("FanZoneAdminOfficialPropsController", ["$location", "FanZoneAdmin", function($location, FanZoneAdmin){

    var self = this;

    self.officialProps = {};
    FanZoneAdmin.getOfficialProps().then(function(data){
        self.officialProps = data.data;
    })

    self.deleteProp = function(prop){
        console.log(prop);
        FanZoneAdmin.deleteOfficialProp(prop.id).then(function(){
            FanZoneAdmin.getOfficialProps().then(function(data){
                self.officialProps = data.data;
            })
        })
    }

    self.updateProp = function(prop){
        FanZoneAdmin.setPropForUpdate(prop);
        $location.path("/izmenaTematskogRekvizita");
    }
}])

.controller("FanZoneAdminUpdatePropController", ["$location", "FanZoneAdmin", function($location, FanZoneAdmin){

    var self = this;

    self.prop = FanZoneAdmin.getPropForUpdate();
    console.log(self.prop);   
    
    self.updateProp = function(prop){
        FanZoneAdmin.updateOfficialProp(prop).then(function(){
            $location.path("/zvanicniRekviziti");
        })
    }
}])

.controller("FanZoneAdminUsedPropsController", ["FanZoneAdmin", function(FanZoneAdmin){

    var self = this;

    self.pendingUserAds = {};
    FanZoneAdmin.getPendingUserAds().then(function(data){
        self.pendingUserAds = data.data;
        console.log(self.pendingUserAds);
    })

    self.updateUserAdStatus = function(userAd, status){
        var updatedUserAd = userAd;
        updatedUserAd.adStatus = status;
        FanZoneAdmin.updateUserAdStatus(updatedUserAd).then(function(){
            FanZoneAdmin.getPendingUserAds().then(function(data){
                self.pendingUserAds = data.data;
            })
        })
    }


}])

.controller("FanZoneAdminChangePassController", ["$location", "$rootScope", "FanZoneAdmin", function($location, $rootScope, FanZoneAdmin){
	
	var self = this;
	
	self.changePassword = function(oldPassword, newPassword){
		if($rootScope.user.password == oldPassword){
			FanZoneAdmin.changePassword($rootScope.user.id, newPassword).then(function(){
				$rootScope.user.password = newPassword;
				$location.path("/adminFanZone");
			})
		}
	}
	
}])

.controller("FanZoneAdminUpdateDataController", ["$location", "$rootScope", "FanZoneAdmin", function($location, $rootScope, FanZoneAdmin){

    var self = this;

    self.admin = {};
    self.admin.email = $rootScope.user.email;
	self.admin.id = $rootScope.user.id;
	self.admin.name = $rootScope.user.name;
	self.admin.surname = $rootScope.user.surname;
	self.admin.city = $rootScope.user.city;
	self.admin.phoneNumber = $rootScope.user.phoneNumber;
	self.admin.role = $rootScope.user.role;
    self.admin.password = $rootScope.user.password;

    self.update = function(admin){
        FanZoneAdmin.update(admin).then(function(){
            $rootScope.user.name = self.admin.name;
            $rootScope.user.surname = self.admin.surname;
            $rootScope.user.city = self.admin.city;
            $rootScope.user.phoneNumber = self.admin.phoneNumber;
            $rootScope.user.password = self.admin.password;
            $location.path("/adminFanZone")
        });
    }
}]);