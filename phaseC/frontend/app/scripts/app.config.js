/**
 * Created by abhishek on 12/1/17.
 */
/**
 * This file contains application wide config data like string constants etc.
 * This should contain only key value pairs, no logic here!
 */

'use strict';

/**
 * Check if app is running on production server or localhost
 * @returns {boolean}
 */
function isProduction() {
  return window.location.hostname.indexOf('localhost') === -1;
}

/**
 * Create module for app configuration
 * @type {angular.Module}
 */
var appConfig = angular.module('appConfig', []);

appConfig.factory('AppConfig', function () {

  // server endpoint
  var API_BASE_URL = isProduction() ? 'PRODUCTION_URL' : 'http://localhost:8080';

  var CONFIG = {
    LOGIN_API   : API_BASE_URL + '/login',
    UPLOAD_API  : API_BASE_URL + '/upload',
    COMPARE_API : API_BASE_URL + '/compare',
    ARCHIVE_TYPE: 'application/zip'
  };

  return CONFIG;

});

