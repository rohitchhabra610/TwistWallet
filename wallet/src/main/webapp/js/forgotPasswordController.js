
App.controller('forgotPasswordController', function($scope,$http,$location) {
	$scope.email = $("#email").val();
	
	$scope.cancelBtn=function(){
		$location.path("/login");
	}
	
	$scope.sendMail = function(){

	$scope.url = p.URL+"/forgotPassword";
	$scope.request = {
			"user": {
				"emailAddress": $("#email").val()//$scope.email,
			}
		}
	$http({ 
			method: 'POST',
		    url: $scope.url,
		    data: $scope.request
		    }).then(function successCallback(response) {
				console.log(response);
				if(response.data.resultCode==1){
					alert("password is reset and send over mail")
					$location.path("/login")
				}else if(response.data.resultCode==102){
					alert(response.data.resultDesc)
					//$location.path("/login")
				}else {
					alert("failure")
				}
			}, function errorCallback(response) {
				alert("error")
			});
		
	}
	});
