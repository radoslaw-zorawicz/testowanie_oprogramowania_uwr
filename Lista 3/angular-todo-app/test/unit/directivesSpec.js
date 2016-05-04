'use strict';

describe('directive tests', function() {
    beforeEach(module('todoApp'));
    it('should set background to rgb(128, 128, 128)',
        inject(function($compile,$rootScope) {
            var scope = $rootScope.$new();

            var elem = angular.element("<span custom-color=\"rgb(128, 128, 128)\"></span>");

            scope = $rootScope.$new();

            $compile(elem)(scope);

            expect(elem.css("background-color")).toEqual('rgb(128, 128, 128)');
        })
    );
});