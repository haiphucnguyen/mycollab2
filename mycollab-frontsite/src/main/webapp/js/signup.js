$(document).ready(function(){
	$('.timezone_value').text($('#timezone_dropdown_hidden > .single_timezone:eq(0) > span').text());
	
	$('.timezone_input').click(function() {
		$('#timezone_dropdown_hidden').slideToggle("fast");
	});
	
	$('#timezone_dropdown_hidden > .single_timezone').click(function() {
		$('.timezone_value').text($(this).children('span').text());
		$('#timezonefield').attr('value', $(this).attr('data-tag'));
		$('#timezone_dropdown_hidden').slideToggle("fast");
	});
	
	$('div[class^="formfield"]').each(function(){
		var placeHolderContainer = $(this).find('p');
		var inputObject = $(this).find('input');
		inputObject.attr('placeholder', placeHolderContainer.text());
		placeHolderContainer.hide();
	});
	
	$.placeholder.shim();
});