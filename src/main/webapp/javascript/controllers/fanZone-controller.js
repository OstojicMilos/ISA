angular.module("isaProject")

.controller("FanZoneController", ["FanZone", function(FanZone){
	
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
		
	}

	self.openActiveUserAds = function(){
		FanZone.getActiveUserAds().then(function(data){
			self.userAds = data.data;
			self.showOfficialProps = false;
			console.log(self.userAds);
		})
	}

}])

.controller("NewUserAdController", ["FanZone", function(FanZone){
	
	var self = this;

	self.createNewAd = function(newUserAd){
		var userAd = newUserAd;
		userAd.sold = false;
		userAd.adStatus = "PENDING";
		userAd.owner = {};
		//userAd.owner.id = sessionStorage.userId;
		userAd.owner.id = 1;
		FanZone.createNewUserAd(userAd).then(function(){
		});
	}
}]);