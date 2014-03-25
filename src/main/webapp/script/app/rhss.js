var RHSS = angular.module('RHSS', [ ]);

RHSS.run(function($rootScope, $http) {
    $http.get('/containers').success(function(response) {
        $rootScope.containers = response;
        $rootScope.selectedContainer = $rootScope.containers[0].name;
    });
});
