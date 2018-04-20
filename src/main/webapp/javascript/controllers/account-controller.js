angular.module("isaProject")
.controller('AccountController', ['User', '$scope', '$rootScope', 'EstablishmentService', function(User, $scope, $rootScope, EstablishmentService){
	
	/*
	$scope.logOut = function() {
		$rootScope.loggedIn = false;
		$rootScope.user = {};
		$location.path("/");
	}*/
	
	(function() {
		EstablishmentService.getAllCinemas()
			.then(function(response) {
				$scope.cinemas = response.data;
			})
		
		EstablishmentService.getAllTheatres()
			.then(function(response) {
				$scope.theatres = response.data;
			})
	})();
	$scope.types = ["Pozoriste", "Bioskop"];
	$scope.selected = {};

	$scope.getIncome = function() {
		var dto = {};
		dto.from = $scope.selected.from;
		dto.to = $scope.selected.to;
		EstablishmentService.getEstablishmentIncome($scope.selected.id, dto)
			.then(function(response) {
				$scope.selected.income = response.data;
			})
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

.controller('ReservationViewController', ["$rootScope", "User", "$route", function($rootScope, User,$route){
	
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
	
	self.newRating = {};

	self.updateRating = function(id) {
		var dto = {};
		if (self.newRating.a) {
			dto.ambient = self.newRating.a;
		}
		if (self.newRating.p) {
			dto.event = self.newRating.p
		}
		User.updateRating(id, dto)
			.then(function(response) {
				$route.reload();
			})
	}
	
}])
