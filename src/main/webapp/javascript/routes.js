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
		templateUrl: "pages/fanZone.html",
		controller: "FanZoneController",
		controllerAs: "FanZoneCtrl"
	})
});