describe('filter tests', function() {
    beforeEach(module('todoApp'));
    var text = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678";
    it('should truncate the input to 20 characters',
        inject(function(truncateFilter) {
            expect(truncateFilter(text, 20).length).toBe(23);
        })
    );
});