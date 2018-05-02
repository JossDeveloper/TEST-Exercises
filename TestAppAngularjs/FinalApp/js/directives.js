angular.module("FinalApp")
	.directive("postInfo", function()
		{
			return { restrict: 'E', scope: { info: '=' }, templateUrl: 'templates/postInfo.html' };
		});