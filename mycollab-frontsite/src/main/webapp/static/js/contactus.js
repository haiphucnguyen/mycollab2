$(document).ready(function(){
	$('html').click(function() {
		$(".dropdown-hidden").slideUp("fast");
	});
	$(".dropdown").each(function() {
		var el = $(this);
		var parent = el.parent();
		var valueList = parent.children(".dropdown-hidden");
		var hiddenInput = parent.children("input");
		if(!hiddenInput.val()) {
			el.text("- Please Select -");
		}
		else {
			el.text(hiddenInput.val());
		}
		
		valueList.width(el.innerWidth());
		
		el.click(function(event){
			event.stopPropagation();
			$(".dropdown-hidden").each(function(){
				if($(this).get(0) === valueList.get(0))
					valueList.slideDown("fast");
				else
					$(this).slideUp("fast");
			});
		});
		valueList.children(".dropdown-value").click(function(event) {
			//event.stopPropagation();
			el.text($(this).text());
			hiddenInput.val($(this).text());
			valueList.slideToggle("fast");
		});
	});
});