/**
 * Created by abhishek on 11/30/17.
 */
'use strict';

/**
 * @ngdoc function
 * @name uiV2App.controller:LoginController
 * @description
 * # LoginController
 * Controller of the uiV2App
 */
angular.module('uiV2App').controller('LoginController', ['$scope', '$http', 'AppConfig', 'toastr', '$location', function ($scope, $http, AppConfig, toastr, $location) {

  // model for holding form data
  $scope.model = {
    username: '',
    password: ''
  };

  /**
   * If the user has already logged in then take them directly to /dashboard
   * @returns {boolean}
   */
  $scope.init = function () {
    if (!!localStorage.getItem('username')) {
      $location.path('/dashboard');
      return true;
    }
    return false;
  };

  /**
   * Submit login form and check if the user should be allowed to go to /dashboard
   */
  $scope.submit = function () {
    var postData = ({
      'username': $scope.model.username || '',
      'password': $scope.model.password || ''
    });

    $scope.callLoginAPI(appConfig.LOGIN_API, postData).then(function (success) {
      toastr.success($scope.model.username, "Welcome!");
      localStorage.setItem("username", $scope.model.username);
      $location.path('/dashboard');
    }, function (error) {
      toastr.error("Invalid credentials. Please try again.", "Oops!");
    });
  }


  /**
   * Call /login API to check if user is logged in
   * @param API
   * @param postData
   * @returns {HttpPromise}
   */
  $scope.callLoginAPI = function (API, postData) {
    var postConfig = {
      headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
    };
    return $http.post(AppConfig.LOGIN_API, JSON.stringify(postData), postConfig);
  };

  // init
  $scope.init();
}]);
