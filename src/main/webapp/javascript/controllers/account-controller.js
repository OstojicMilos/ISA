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

.controller("FriendshipController", ['User', 'Friendship','$rootScope', function(User, Friendship, $rootScope){

    self = this;
    
    self.searchResult = {};
    self.pendingRequests = {};
    self.friends = {};
    
    
    Friendship.findPendingRequestsFor($rootScope.user).then(function(response){
    	if(response != undefined){
    		self.pendingRequests = response.data;
    	}
    });
    
    Friendship.findFriendsFor($rootScope.user).then(function(response){
    	if(response != undefined){
    		self.friends = response.data;
    	}
    });
    
    self.confirmFriendship = function(friend){
    	var friendship = {
    			first : $rootScope.user,
    			second : friend
    	};
    	Friendship.confirmFriendship(friendship).then(function(response){
    		
    	})
    };
    
    self.deleteFriendship = function(friend){
    	var friendship = {
    			first : $rootScope.user,
    			second : friend
    	};
    	Friendship.deleteFriendship(friendship).then(function(response){
    		
    	})
    }
    
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
    
    self.addFriendship = function(friend){
    	var friendship = {
    			first : $rootScope.user,
    			second : friend
    	};
    	Friendship.addFriendship(friendship).then(function(response){
    		
    	})
    }
}])

.controller('ReservationViewController', ["$rootScope", "User", function($rootScope, User){
	
	var self = this;
	
	self.reservationsAsOwner = {};
	self.reservationsAsGuest = {};
	
	if($rootScope.loggedIn){
		User.getReservationsAsOwner($rootScope.user.id).then(function(response){
			self.reservationsAsOwner = response.data;
			console.log(self.reservationsAsOwner);
		});
		
		User.getReservationsAsGuest($rootScope.user.id).then(function(response){
			self.reservationsAsGuest = response.data;
			console.log(self.reservationsAsGuest);
		});
	}
	
	
}])
