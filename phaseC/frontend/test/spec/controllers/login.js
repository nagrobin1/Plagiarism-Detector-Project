/**
 * Created by abhishek on 12/2/17.
 */

'use strict';
// karma start test/karma.conf.js --browsers Chrome
/**
 * Tests for Login component
 */
describe('Controller: LoginController', function () {

  // load the controller's module
  beforeEach(module('uiV2App'));

  var LoginController, scope, $httpBackend, appConfig;

  /* Initialize the controller and a mock scope*/
  beforeEach(inject(function ($controller, $rootScope, _$httpBackend_, AppConfig) {
    scope = $rootScope.$new();
    appConfig = AppConfig;
    $httpBackend = _$httpBackend_;
    LoginController = $controller('LoginController', {
      $scope: scope
    });
  }));

  it('should redirect to /callLoginAPI if user not logged in', function () {
    localStorage.clear();
    expect(scope.init()).toBe(false);
  });

  it('should redirect to /dashboard if user is logged in', function () {
    localStorage.setItem('username', 'Abhishek');
    expect(scope.init()).toBe(true);
  });

  it('should call LOGIN API to check if user is a valid user', function () {
    var loginSuccessResponse = {"username": "Tester", "success": true, "message": "Successfully logged in", "status": "OK"};

    $httpBackend.when('POST', appConfig.LOGIN_API).respond(loginSuccessResponse);
    $httpBackend.when('GET', 'views/dashboard.html').respond({});

    var postData = ({
      'username': 'Tester',
      'password': 'Test123'
    });
    scope.callLoginAPI(appConfig.LOGIN_API, postData).then(function (data) {
      expect(data.data.status).toBe('OK');
      expect(data.data.success).toBe(true);
    });

    $httpBackend.flush();
  });

});
