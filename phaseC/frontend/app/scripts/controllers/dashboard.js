/**
 * Created by abhishek on 11/30/17.
 */
'use strict';

/**
 * @ngdoc function
 * @name uiV2App.controller:DashboardController
 * @description
 * # DashboardController
 * Controller of the uiV2App
 */
var app = angular.module('fileUpload', ['ngFileUpload']);

angular.module('uiV2App').controller('DashboardController', ['$scope', '$http', 'AppConfig', 'toastr', '$location', 'Upload', function ($scope, $http, AppConfig, toastr, $location, Upload) {

  // allow uploading only one file.
  $scope.file = null;

  $scope.status = {
    fileUploadedSuccessfully: false
  };

  /**
   *  Upload a valid zip archive to backend server
   */
  $scope.submit = function () {

    // do nothing if file is not a valid zip file.
    if (!this.validateFile())
      return;

    Upload.upload({
      url             : AppConfig.UPLOAD_API,
      method          : 'POST',
      sendFieldsAs    : 'form',
      headers         : {
        'Content-Type': 'application/json; charset=utf-8'
      },
      file            : $scope.file,
      fileFormDataName: 'file'
    }).then(function (response) {
      $scope.status.fileUploadedSuccessfully = true;
      toastr.success($scope.file.name + " uploaded successfully.", "Nice!");
    }, function (response) {
      $scope.status.fileUploadedSuccessfully = false;
      toastr.error("Something went wrong. Please try again with a valid archive file.", "Oops!");
    });

  };

  /**
   * Check if the file being uploaded is a valid zip archive file
   * @returns {boolean}
   */
  $scope.validateFile = function () {
    // if file is not a valid zip file, show error alert
    if (!$scope.file || $scope.file.$error) {
      toastr.error("Please upload a valid archive file.", "Invalid file selected!");
      return false;
    }

    // only allow zip files.
    if ($scope.file.type != AppConfig.ARCHIVE_TYPE) {
      toastr.error("Please upload a valid archive file.", "Invalid file selected!");
      this.cancel();
      return false;
    }
    return true;
  };

  /**
   * Remove selected zip file from scope
   */
  $scope.cancel = function () {
    $scope.file = null;
  }

  /**
   * Trigger change detection
   */
  $scope.runDetection = function () {
    $scope.callCompareAPI().then(function (success) {
      console.log(success);
      var details = success.data || [];
      $scope.comparisionDetails = $scope.sortByKey(details, 'finalScore') || [];
    }, function (error) {
      toastr.error("Something went wrong. Please try again.", "Oops!");
    });
  };

  /**
   * Sorts a given array by given key in descending order
   * @param array
   * @param key
   * @returns {*|Array.<T>}
   */
  $scope.sortByKey = function (array, key) {
    return array.sort(function (a, b) {
      var x = a[key];
      var y = b[key];
      return y - x;
    });
  }

  /**
   * Call DETECT API for comparing files that were uploaded
   * @returns {HttpPromise}
   */
  $scope.callCompareAPI = function () {
    var postConfig = {
      headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
    };
    return $http.post(AppConfig.COMPARE_API, postConfig);
  }

  /**
   * Check if the user is logged in, if not redirect to /login page.
   * @returns {boolean}
   */
  $scope.checkAccess = function () {
    if (!localStorage.getItem('username')) {
      $location.path('/login');
      return false;
    }
    return true;
  }
  // init
  $scope.checkAccess();
}]);
