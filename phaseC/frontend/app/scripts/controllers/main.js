'use strict';

/**
 * @ngdoc function
 * @name uiV2App.controller:MainController
 * @description
 * # MainController
 * Controller of the uiV2App
 */
angular.module('uiV2App')
  .controller('MainController', ['$scope', '$http', 'AppConfig', 'toastr', '$location', function ($scope, $http, AppConfig, toastr, $location) {

    /**
     * Take the user to login page
     * @returns {boolean}
     */
    $scope.init = function () {
      $location.path('/login');
      return true;
    }

  }]);
