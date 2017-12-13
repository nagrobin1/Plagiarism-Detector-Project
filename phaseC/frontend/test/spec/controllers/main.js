'use strict';

/**
 * Tests for MainController
 */
describe('Controller: MainController', function () {


  // load the controller's module
  beforeEach(module('uiV2App'));

  var MainCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainCtrl = $controller('MainController', {
      $scope: scope
    });
  }));

  it('should redirect to /login after calling init()', function () {
      expect(scope.init()).toBe(true);
  });
});
