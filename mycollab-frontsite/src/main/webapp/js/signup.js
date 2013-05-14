$(document).ready(function(){
	$('.timezone_value').text($('#timezone_dropdown_hidden > .single_timezone:first-child > span').text());
	
	$('.timezone_input').click(function() {
		$('#timezone_dropdown_hidden').slideToggle("fast");
	});
	
	$('#timezone_dropdown_hidden > .single_timezone').click(function() {
		$('.timezone_value').text($(this).children('span').text());
		$('#timezonefield').attr('value', $(this).attr('data-tag'));
		$('#timezone_dropdown_hidden').slideToggle("fast");
	});
});