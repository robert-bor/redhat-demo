RHSS.controller('ContainerList', function($rootScope, $scope, $http) {

    $scope.selectContainer= function(containerName) {
        $rootScope.selectedContainer = containerName;
    };

    $scope.reauthenticate= function() {
        $http.post('/reauthenticate').success(function(response) {
            location.reload();
        });
    };

    $scope.addContainer = function() {
        var containerName = prompt("What is your container name?");
        if (containerName != undefined) {
            $http.post('/add-container?containerName='+containerName).success(function(response) {
                location.reload();
            });
        }
    };

    $scope.removeContainer= function(containerName) {
        if (confirm("Are you sure you want to delete "+containerName +"?")) {
            $http.delete('/delete-container?containerName='+containerName).success(function(response) {
                location.reload();
            });
        }
    };

    $scope.removeObject= function(containerName, objectName) {
        if (confirm("Are you sure you want to delete "+containerName + "/" + objectName + "?")) {
            $http.delete('/delete-object?containerName='+containerName+"&objectName="+objectName).success(function(response) {
                location.reload();
            });
        }
    };

});