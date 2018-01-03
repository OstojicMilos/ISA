angular.module("isaProject")

.factory("FanZone", function($http){
	return{
		test: function(){
			return "radi service";
		}
	}
});