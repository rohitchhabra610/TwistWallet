	App.controller("loginController", function($scope,$http,$location,$route) {
		$scope.google_client_id=cred.google_client_id;
		$scope.cancelBtn=function(){
			$location.path("/landingPage");
		}
		
	$scope.login = function(){
		$scope.email = $("#email").val();
		$scope.password = $("#password").val();

		$scope.request = 
		{
			"user": {
				"emailAddress": $scope.email
			},
			"login": {
				"password": $scope.password
			}
		}

	$scope.url =   p.URL+"/login";
		
		$http({
		    method: 'POST',
		    url: $scope.url,
		    data: $scope.request
		}).then(function successCallback(response) {
			console.log(response);
			if(response.data.resultCode==1){
				console.log("userId "+response.data.user.userId);
				window.localStorage.setItem("userId", response.data.user.userId);
				window.localStorage.setItem("isLogin",true);
				window.localStorage.setItem("loginId",response.data.login.loginId);
				window.localStorage.setItem("loginUserName",response.data.user.firstName);
				console.log("loginUserName "+response.data.user.firstName);
				loginUser = response.data.user;
				window.localStorage.setItem("loginUserArr", loginUser);
				window.localStorage.setItem("stringfyJson", JSON.stringify(loginUser));
				if(response.data.user.newUser == true){
					alert("new user");
					$location.path("/resetPassword");
				}else{
				$location.path("/landingPage")
				window.location.reload();
				} 
			}else{
				alert("failure")
			}
		}, function errorCallback(response) {
			alert("error")
		});
		
	}
	$scope.gotoforgotPassword = function(){
		$location.path("/forgotPassword")
	}
	
	});
var signUp = function (){
	window.location.href=(p.URL+"/#/signUp")
}


var  onSignIn = function(googleUser) {
	
	  var profile = googleUser.getBasicProfile();
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Gmail id: ' + profile.getEmail());
	  var name = profile.getName().split(" ");
	  
	  var reqJSON = {
			  "user": {
					"firstName": name[0],
					"lastName": name[1],
					"emailAddress": profile.getEmail(),
					"isAdmin": 1
				}
		    }
		    var reqURL = p.URL + "/googleLoginSignUp";
	  
	  $.ajax({
	        method: 'POST',
	        data: JSON.stringify(reqJSON),
	        dataType: 'json',
	        contentType: 'application/json; charset=utf8',
	        url: reqURL,
	        success: function(getresponce) {
	            console.log("getresponce from google :-"+getresponce)
	            
	            window.localStorage.setItem("userId", getresponce.user.userId);
				window.localStorage.setItem("isLogin",true);
				window.localStorage.setItem("loginId",getresponce.login.loginId);
				window.localStorage.setItem("loginUserName",getresponce.user.firstName);
				
				loginUser = getresponce.user;				
				window.localStorage.setItem("stringfyJson", JSON.stringify(loginUser));
				
				if(getresponce.user.newUser == true){
					alert("new user");
					window.location.href =p.URL +"/#/resetPassword";
				}else{
					window.location.href =p.URL +"/#/landingPage";
				//$route.reload();
				window.location.reload();
				} 
			  
	            
	        },
	        error: function(error) {
	            alert("error")
	            return false;
	        }
	    });
	  
	  
	  
	}
