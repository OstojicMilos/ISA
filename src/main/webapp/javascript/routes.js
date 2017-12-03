angular.module("isaProject")
.config(function($routeProvider){
	$routeProvider
	
	.when("/", {
		
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