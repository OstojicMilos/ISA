angular.module("isaProject")
.controller('AccountController', ['User', '$scope', '$rootScope', function(User, $scope, $rootScope){
	
	$scope.logOut = function() {
		$rootScope.loggedIn = false;
		$rootScope.user = {};
		$location.path("/");
	}
	
	
}])

.controller("UserUpdateDataController", ['User','$rootScope', function(User, $rootScope){

    self = this;
    console.log(self.user);

    self.update = function(){
        User.update(self.user).then(function(){});
    }
}])

.controller("FriendshipController", ['User','$rootScope', function(User, $rootScope){

    self = this;
    
    self.searchResult = {};
    
    self.search= function(){
    	if(self.criteria != ""){
    		
    		User.searchForUser(self.criteria).then(function(response){
        		self.searchResult = response.data;
        	});
    		self.criteria= "";
    	}
    	else{
    		self.searchResult = {};
    	}
    }
}])