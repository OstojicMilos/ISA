angular.module("isaProject")

.controller("FanZoneController", ["FanZone", function(FanZone){
	
	var self = this;
	
	self.officialProps = {};
	FanZone.getOfficialProps().then(function(data){
		self.officialProps = data.data;
	})

	self.reserveProp = function(prop){
		
	}

}])

.controller("NewPropController", ["FanZone", function(FanZone){
	
	var self = this;

	self.createNewAd = function(name, description, price, image){

	}
}]);