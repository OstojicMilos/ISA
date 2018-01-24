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
	
	.when("/aktivacija/:tokenId", {
		templateUrl: "pages/activateAccount.html",
		controller: "ActivationController",
		controllerAs: "ActivationCtrl"
	})
	
	.when("/logovanje", {
		templateUrl: "pages/login.html",
		controller: "LoginController",
		controllerAs: "LoginCtrl"
	})
	
	.when("/account", {
		templateUrl: "pages/user/index.html",
		controller: "AccountController",
		controllerAs: "AccountCtrl"
	})
	
	.when("/account/fanZona", {
		templateUrl: "pages/fanZone/index.html",
		controller: "FanZoneController",
		controllerAs: "FanZoneCtrl"
	})
	
	.when("/account/fanZona/noviOglas", {
		templateUrl: "pages/fanZone/newProp.html",
		controller: "NewPropController",
		controllerAs: "NewPropCtrl"
	})
});