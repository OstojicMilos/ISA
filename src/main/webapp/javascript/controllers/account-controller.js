angular.module("isaProject")
.controller('AccountController', ['User', '$scope', '$rootScope', function(User, $scope, $rootScope){
	
	$scope.logOut = function() {
		$rootScope.loggedIn = false;
		$rootScope.user = {};
		$location.path("/");
	}
	
	
}])