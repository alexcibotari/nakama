'use strict';

angular.module('NakamaApp', [
    'ngAria',
    'ngCookies',
    'ngResource',
    'ui.router',
    'ui.bootstrap',
    'pascalprecht.translate',
    'angular-loading-bar',
    'NakamaApp.controllers'
])
    .run(
    ['$rootScope', '$state', '$stateParams',
        function ($rootScope, $state, $stateParams) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ]
)
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/dashboard");
        $stateProvider
            .state('dashboard', {
                url: "/dashboard",
                templateUrl: "/scripts/components/dashboard/dashboard.html",
                controller: "DashboardCtrl"
            });
    });


angular.module('NakamaApp.controllers', [])
    .controller('DashboardCtrl', ['$scope',
        function ($scope) {
            $scope.abc="abc123"
        }]);