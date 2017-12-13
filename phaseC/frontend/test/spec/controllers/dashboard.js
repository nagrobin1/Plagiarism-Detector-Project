/**
 * Created by abhishek on 12/2/17.
 */
/**
 * Created by abhishek on 12/2/17.
 */

'use strict';
// karma start test/karma.conf.js --browsers Chrome
/**
 * Tests for Dashboard component
 */
describe('Controller: DashboardController', function () {

  // load the controller's module
  beforeEach(module('uiV2App'));

  var LoginController, scope, $httpBackend, appConfig;

  /* Initialize the controller and a mock scope*/
  beforeEach(inject(function ($controller, $rootScope, _$httpBackend_, AppConfig) {
    scope = $rootScope.$new();
    appConfig = AppConfig;
    $httpBackend = _$httpBackend_;
    LoginController = $controller('DashboardController', {
      $scope: scope
    });
  }));

  it('should redirect to /login if user not logged in', function () {
    localStorage.clear();
    expect(scope.checkAccess()).toBe(false);
  });

  it('should validate file', function () {
    scope.file = {type: 'application/zip', name: 'test_file'};
    expect(scope.validateFile()).toBe(true);
  });

  it('should validate bad file', function () {
    scope.file = {type: 'image/png', name: 'test_file'};
    expect(scope.validateFile()).toBe(false);
  });

  it('should reset file', function () {
    scope.file = {type: 'image/png', name: 'test_file'};
    scope.cancel();
    expect(scope.file).toBe(null);
  });

  it('should sort array by score', function () {
    var arr = [{finalScore: 0.98}, {finalScore: 0.96}, {finalScore: 0.97}, {finalScore: 0.91}];
    var expected = [{finalScore: 0.98}, {finalScore: 0.97}, {finalScore: 0.96}, {finalScore: 0.91}];
    var actual = scope.sortByKey(arr, 'finalScore');
    expect(JSON.stringify(actual)).toBe(JSON.stringify(expected));
  });

  it('should call COMPARE API', function () {
    var detectionSuccessResponse = {"username": "Tester", "success": true, "message": "Successfully logged in", "status": "OK"};

    $httpBackend.when('POST', appConfig.COMPARE_API).respond(detectionSuccessResponse);
    $httpBackend.when('GET', 'views/login.html').respond({});

    scope.callCompareAPI().then(function (data) {
      expect(data.data.status).toBe('OK');
      expect(data.data.success).toBe(true);
    });

    $httpBackend.flush();
  });

});
