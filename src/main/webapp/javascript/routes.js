angular.module("isaProject")
.config(function($routeProvider){
	$routeProvider
	
	.when("/", {
	
	})
	
	.when("/bodovnaSkala", {
		templateUrl: "pages/sysAdmin/pointScale.html",
		controller: "PointScaleController",
		controllerAs: "PointScaleCtrl"
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

	.when("/adminFanZone", {
		templateUrl: "pages/fanZoneAdmin/index.html",
		controller: "FanZoneAdminController",
		controllerAs: "FanZoneAdminCtrl"
	})

	.when("/izmenaTematskogRekvizita", {
		templateUrl: "pages/fanZoneAdmin/updateProp.html",
		controller: "FanZoneAdminUpdatePropController",
		controllerAs: "FanZoneAdminUpdatePropCtrl"
	})

	.when("/noviTematskiRekvizit", {
		templateUrl: "pages/fanZoneAdmin/newProp.html",
		controller: "FanZoneAdminNewPropController",
		controllerAs: "FanZoneAdminNewPropCtrl"
	})

	.when("/zvanicniRekviziti", {
		templateUrl: "pages/fanZoneAdmin/officialProps.html",
		controller: "FanZoneAdminOfficialPropsController",
		controllerAs: "FanZoneAdminOfficialPropsCtrl"
	})

	.when("/objavePonuda", {
		templateUrl: "pages/fanZoneAdmin/usedPropsForSale.html",
		controller: "FanZoneAdminUsedPropsController",
		controllerAs: "FanZoneAdminUsedPropsCtrl"
	})

	.when("/izmenaLicnihPodataka", {
		templateUrl: "pages/fanZoneAdmin/updatePersonalData.html",
		controller: "FanZoneAdminUpdateDataController",
		controllerAs: "FanZoneAdminUpdateDataCtrl"
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