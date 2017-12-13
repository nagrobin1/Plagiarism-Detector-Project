'use strict';

/**
 * Tests for Navigation component
 */
describe('Controller: NavigationController', function () {

  // load the controller's module
  beforeEach(module('uiV2App'));

  var NavigationController, scope;

  /* Initialize the controller and a mock scope*/
  beforeEach(inject(function ($controller, $rootScope, $location) {
    scope = $rootScope.$new();
    NavigationController = $controller('NavigationController', {
      $scope: scope
    });
  }));

  it('should return true if user is logged in', function () {
    localStorage.setItem('username', 'abhishek');
    expect(scope.isLoggedIn()).toBe(true);
  });

  it('should return false if user is not logged in', function () {
    localStorage.clear();
    expect(scope.isLoggedIn()).toBe(false);
  });

  it('should remove user token from localstorage after logout', function () {
    scope.logout();
    expect(scope.isLoggedIn()).toBe(false);
  });

});
