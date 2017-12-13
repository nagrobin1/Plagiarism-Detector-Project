/**
 * Created by abhishek on 11/30/17.
 */
'use strict';

/**
 * @ngdoc function
 * @name uiV2App.controller:NavigationController
 * @description
 * # NavigationController
 * Controller of the uiV2App
 */
angular.module('uiV2App').controller('NavigationController', ['$scope', '$http', 'AppConfig', 'toastr', '$location', function ($scope, $http, AppConfig, toastr, $location) {

  /**
   * Change route
   * @param route
   */
  $scope.go = function (route) {
    $location.path(route);
  };

  /**
   * Remove user token from localstorage and send user to login page
   */
  $scope.logout = function () {
    localStorage.removeItem("username");
    $location.path('/login');
  };

  /**
   * Check if user is logged in
   * @returns {boolean}
   */
  $scope.isLoggedIn = function () {
    return !!localStorage.getItem("username");
  }

}]);
