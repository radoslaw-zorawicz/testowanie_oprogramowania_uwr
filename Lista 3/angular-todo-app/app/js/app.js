	
	var todoApp = angular.module('todoApp',[]);

	todoApp.controller('TodoController', function($scope, notesFactory) {
		  
		//localStorage.clear();
		
		$scope.note = {
			notePriority: 1
		};
		  
		$scope.notes = notesFactory.get();

		$scope.$on("noteFactoryEvent", function(event) {
			$scope.notes = notesFactory.get();
		});

		$scope.createNote = function() {
			notesFactory.put($scope.note);
			$scope.note = {
				notePriority: 1
			};
		};
		
		$scope.removeNote = function(key) {
			notesFactory.remove(key);
		};
		
		$scope.deleteNote = function(key) {
			notesFactory.remove(key);
		}
		
	});
	 
	todoApp.directive('customColor', function () {
		return {
			restrict: 'A',
			link: function (scope, elem, attrs) {
				elem.css({"background-color":attrs.customColor});
			}
		}
	});
	 
	todoApp.filter('truncate',function(){
		return function(input, length){
			return (input.length > length ? input.substring(0,length) + "..." : input);
		};
	}); 
	 
	todoApp.factory('notesFactory', function($rootScope){
		return {
			put: function(note){
				var id = 'todo' + (Object.keys(localStorage).length + 1);
				note.id = id;
				localStorage.setItem(id, JSON.stringify(note));
				$rootScope.$broadcast("noteFactoryEvent");
			},
			get: function(){
				var notes = [];
				var keys = Object.keys(localStorage);
				for(var i = 0; i < keys.length; i++){
					notes.push(JSON.parse(localStorage.getItem(keys[i])));
				} 
				return notes;
			},
			remove: function(key) {
				localStorage.removeItem(key);
				$rootScope.$broadcast("noteFactoryEvent");
			},
			removeAll: function() {
				localStorage.clear();
				$rootScope.$broadcast("noteFactoryEvent");
			}
		}	
	});  
	
	
	
	
	
	
	
	
	