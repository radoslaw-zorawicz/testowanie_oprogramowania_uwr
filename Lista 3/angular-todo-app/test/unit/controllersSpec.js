'use strict';

describe('TodoController Test', function() {
    
	beforeEach(module('todoApp'));
	
	var mockService = function(rootScope) {
		return {
			notes: {
				"todo1" : {
					noteTitle: "some todo"
				},
				"todo2" : {
					noteTitle: "some other todo"
				}
			},
			get: function (){
				var notesArr = [];
				for(var key in this.notes) {
					notesArr.push(this.notes[key]);
				}
				return notesArr;
			},
			put: function(content){
				this.notes["todo" + (this.get().length + 1)] = content;
				rootScope.$broadcast("noteFactoryEvent");
			},

			remove: function (key) {
				delete this.notes[key];
				rootScope.$broadcast("noteFactoryEvent")
			}
		}
	};
	
    it('should return notes array with 2 elements initially',
		inject(function($rootScope, $controller) {
			
			var scope = $rootScope.$new();
			var ctrl = $controller("TodoController", {$scope: scope, notesFactory: mockService($rootScope) });
			
			expect(scope.notes.length).toBe(2);
			
			scope.note = {
				noteTitle: "My note"
			};
			scope.createNote();
			
			expect(scope.notes.length).toBe(3);
			
		})
	);

	it('should return notes array with 1 element after delete',
		inject(function($rootScope, $controller) {

			var scope = $rootScope.$new();
			var ctrl = $controller("TodoController", {$scope: scope, notesFactory: mockService($rootScope) });

			expect(scope.notes.length).toBe(2);

			scope.removeNote("todo2");

			expect(scope.notes.length).toBe(1);

		})
	);
	
});