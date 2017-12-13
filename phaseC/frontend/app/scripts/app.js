'use strict';

/**
 * @ngdoc overview
 * @name uiV2App
 * @description
 * # uiV2App
 *
 * Main module of the application.
 */
angular
  .module('uiV2App', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'toastr',
    'angular-loading-bar',
    'ngFileUpload',
    'appConfig'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainController'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController'
      })
      .when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardController'
      })
      .when('/help', {
        templateUrl: 'views/help.html'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
