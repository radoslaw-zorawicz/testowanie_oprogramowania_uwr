'use strict';

describe('my app', function() {
    
	beforeEach(function() {
		localStorage.clear();
		browser().navigateTo('http://localhost:8000/app/notes.html');
    });

	
	
	it("entering note and performing click", function () {

		input('note.noteTitle').enter('test data');
		input('note.noteText').enter('test data');
		select('note.notePriority').option('Important');
				
		element('button').query(function($el, done) {
			$el.click();
			done();
		});
		
		expect(repeater(".repeater").count()).toBe(1);
		
	});

	it("entering 4 new note and performing click", function () {

		for(var i=0; i<5; i++){
			input('note.noteTitle').enter('test data' + i);
			input('note.noteText').enter('test data' + i);
			select('note.notePriority').option('Important');

			element('button').query(function($el, done) {
				$el.click();
				done();
			});
		}
        element('#notes > div:nth-child(5) > div.col-md-9 > div > button').query(function($el, done) {
            $el.click();
            done();
        });
        


		expect(repeater(".repeater").count()).toBe(4);

	});
		
});

