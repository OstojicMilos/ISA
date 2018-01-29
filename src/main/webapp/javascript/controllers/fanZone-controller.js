angular.module("isaProject")

.controller("FanZoneController", ["$location", "$rootScope", "FanZone", function($location, $rootScope, FanZone){
	
	var self = this;
	
	self.officialProps = {};
	self.userAds = {};
	self.showOfficialProps = true;
	FanZone.getOfficialProps().then(function(data){
		self.officialProps = data.data;
	})

	self.openOfficialProps = function(){
		FanZone.getOfficialProps().then(function(data){
			self.officialProps = data.data;
			self.showOfficialProps = true;
		})
	}

	self.reserveProp = function(prop){
		FanZone.reserveNewProp(prop.id, $rootScope.user.email).then(function(){
			
		})
	}

	self.openActiveUserAds = function(){
		FanZone.getActiveUserAds().then(function(data){
			self.userAds = data.data;
			self.showOfficialProps = false;
			console.log(self.userAds);
		})
	}

	self.userAdDetails = function(userAd){
		if(userAd.owner.id == $rootScope.user.id)
			$location.path("/account/fanZona/mojOglas/"+userAd.id);
		else
			$location.path("/account/fanZona/tudjiOglas/"+userAd.id);
	}

}])

.controller("MyUserAdController", ["$location", "$routeParams", "FanZone", function($location, $routeParams, FanZone){
	
	var self = this;

	FanZone.getUserAd($routeParams.userAdId).then(function(data){
		self.userAd = data.data;
		console.log(self.userAd);
	})

	self.acceptOffer = function(userAdId, offerId){
		FanZone.acceptOffer(userAdId, offerId).then(function(){
			$location.path("/account/fanZona");
		})
	}
}])

.controller("OthersUserAdController", ["$rootScope", "$routeParams", "FanZone", function($rootScope, $routeParams, FanZone){

	var self = this;

	self.myOffer = {};
	self.myOffer.placed = false;
	self.myOffer.id = -1;
	self.userAd = {};

	FanZone.getUserAd($routeParams.userAdId).then(function(data){
		self.userAd = data.data;
		for(var i=0; i<self.userAd.offers.length; i++){
			if(self.userAd.offers[i].user.id == $rootScope.user.id)
				self.myOffer = self.userAd.offers[i];
				self.myOffer.placed = true;
		}
		console.log(self.myOffer);
	})

	self.deleteOffer = function(){
		FanZone.deleteOffer(self.userAd.id, self.myOffer.id).then(function(){
			self.myOffer = {};
			self.myOffer.placed = false;
		})
	}

	self.placeOffer = function(myOffer){
		FanZone.placeOffer(self.userAd.id, self.myOffer.id, self.myOffer.offeredSum, $rootScope.user.id).then(function(){
			self.myOffer.placed = true;
		})
	}
}])

.controller("NotificationController", ["$rootScope", "FanZone", function($rootScope, FanZone){

	var self = this;

	self.notifications = {};
	FanZone.getNotifications($rootScope.user.id).then(function(data){
		self.notifications = data.data;
	})
}])

.controller("NewUserAdController", ["$rootScope", "$location", "FanZone", function($rootScope, $location, FanZone){
	
	var self = this;

	self.createNewAd = function(newUserAd){
		var userAd = newUserAd;
		userAd.sold = false;
		userAd.adStatus = "PENDING";
		userAd.owner = {};
		userAd.owner.id = $rootScope.user.id;
		FanZone.createNewUserAd(userAd).then(function(){
			$location.path("/account/fanZona");
		});
	}
}]);