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

	.when("/aktivacija/:token", {
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
		templateUrl: "pages/account/index.html",
		controller: "AccountController",
		controllerAs: "AccountCtrl"
	})
	
	.when("/account/editInfo", {
		templateUrl: "pages/account/updatePersonalData.html",
		controller: "UserUpdateDataController",
		controllerAs: "UserUpdateDataCtrl"
	})
	
	.when("/prijatelji", {
		templateUrl: "pages/account/friends.html",
		controller: "FriendshipController",
		controllerAs: "FriendshipCtrl"
	})
	
	.when("/account/fanZona", {
		templateUrl: "pages/fanZone/index.html",
		controller: "FanZoneController",
		controllerAs: "FanZoneCtrl"
	})
	
	.when("/account/fanZona/noviOglas", {
		templateUrl: "pages/fanZone/newUserAd.html",
		controller: "NewUserAdController",
		controllerAs: "NewUserAdCtrl"
	})

	.when("/account/fanZona/mojOglas/:userAdId", {
		templateUrl: "pages/fanZone/myUserAd.html",
		controller: "MyUserAdController",
		controllerAs: "MyUserAdCtrl"
	})

	.when("/account/fanZona/tudjiOglas/:userAdId", {
		templateUrl: "pages/fanZone/othersUserAd.html",
		controller: "OthersUserAdController",
		controllerAs: "OthersUserAdCtrl"
	})

	.when("/account/fanZona/obavestenja", {
		templateUrl: "pages/fanZone/notifications.html",
		controller: "NotificationController",
		controllerAs: "NotificationCtrl"
	})
	
	.when("/adminFanZone/izmenaLozinke", {
		templateUrl: "pages/fanZoneAdmin/changePassword.html",
		controller: "FanZoneAdminChangePassController",
		controllerAs: "FanZoneAdminChangePassCtrl"
	})

	.when("/bioskopi", {
		templateUrl: "pages/establishment/cinemas.html",
		controller: "CinemasController",
		controllerAs: "CinemasCtrl"
	})

	.when("/pozorista", {
		templateUrl: "pages/establishment/theatres.html",
		controller: "TheatresController",
		controllerAs: "TheatresCtrl"
	})

	.when("/ustanova/:id/izmena", {
		templateUrl: "pages/establishment/editEstablishment.html",
		controller: "EditEstablishmentController",
		controllerAs: "EditEstablishmentCtrl"
	})
	
	.when("/repertoar/:id/:day", {
		templateUrl: "pages/establishment/cinemaRepertoire.html",
		controller: "RepertoireController",
		controllerAs: "RepertoireCtrl",
		resolve : {
			repertoirePromise: function($route, EstablishmentService) {
				return EstablishmentService.getCinemaRepertoire($route.current.params.id, $route.current.params.day )
					.then(function(response) {
						return response.data;
					})
			}
		}
	})
	
	.when("/repertoar/:id", {
		templateUrl: "pages/establishment/theatreRepertoire.html",
		controller: "RepertoireController",
		controllerAs: "RepertoireCtrl",
		resolve : {
			repertoirePromise: function($route, EstablishmentService) {
				return EstablishmentService.getTheatreRepertoire($route.current.params.id, $route.current.params.day )
					.then(function(response) {
						return response.data;
					})
			}
		}
	})

	.when("/repertoar/:establishmentId/izmena/:eventId", {
		templateUrl: "pages/establishment/editRepertoire.html",
		controller: "EditRepertoireController",
		controllerAs: "EditRepertoireCtrl"
	})
	
	.when("/rezervacija/:establishmentId/:eventId/:scheduleId",{
		templateUrl: "pages/account/seatReservation.html",
		controller: "SeatReservationController",
		controllerAs: "SeatReservationCtrl"
	})
	
});