angular.module("isaProject")
.config(function($routeProvider){
	$routeProvider
	
	.when("/", {
		
	})
	
	.when("/adminSistema", {
		templateUrl: "pages/sysAdmin/index.html",
		controller: "SysAdminController",
		controllerAs: "SysAdminCtrl"
	})
	
	.when("/novoPozoriste", {
		templateUrl: "pages/sysAdmin/newTheatre.html",
		controller: "NewTheatreController",
		controllerAs: "NewTheatreCtrl"
	})

	.when("/noviBioskop", {
		templateUrl: "pages/sysAdmin/newCinema.html",
		controller: "NewCinemaController",
		controllerAs: "NewCinemaCtrl"
	})

	.when("/noviAdmin", {
		templateUrl: "pages/sysAdmin/newAdmin.html",
		controller: "NewAdminController",
		controllerAs: "NewAdminCtrl"
	})

	.when("/registracija", {
		templateUrl: "pages/register.html",
		controller: "RegistrationController",
		controllerAs: "RegistrationCtrl"
	})
	
	.when("/logovanje", {
		templateUrl: "pages/login.html",
		controller: "LoginController",
		controllerAs: "LoginCtrl"
	})
	
	.when("/fanZona", {
		templateUrl: "pages/fanZone/index.html",
		controller: "FanZoneController",
		controllerAs: "FanZoneCtrl"
	})
	
	.when("/fanZona/noviOglas", {
		templateUrl: "pages/fanZone/newProp.html",
		controller: "NewPropController",
		controllerAs: "NewPropCtrl"
	})
});