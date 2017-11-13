angular.module("isaProject")

.controller("FanZoneController", ["FanZone", function(FanZone){
	
	var self = this;
	
	console.log("povezan kontroler...");
	console.log(FanZone.test());
	
}]);