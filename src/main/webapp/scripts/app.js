'use strict';

angular.module('NakamaApp', [
    'ngAria',
    'ngCookies',
    'ngResource',
    'ui.router',
    'ui.bootstrap',
    'pascalprecht.translate',
    'angular-loading-bar'
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
                templateUrl: "/scripts/components/dashboard/dashboard.html"
            })
            .state('state1', {
                url: "/state1",
                templateUrl: "partials/state1.html"
            })
            .state('state1.list', {
                url: "/list",
                templateUrl: "partials/state1.list.html",
                controller: function ($scope) {
                    $scope.items = ["A", "List", "Of", "Items"];
                }
            })
            .state('state2', {
                url: "/state2",
                templateUrl: "partials/state2.html"
            })
            .state('state2.list', {
                url: "/list",
                templateUrl: "partials/state2.list.html",
                controller: function ($scope) {
                    $scope.things = ["A", "Set", "Of", "Things"];
                }
            });
    });