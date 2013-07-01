$(document).ready(function(){
	$('.tour-items').hide();
	$('.content-1').show(0);
	$('.items-1').addClass('item-active');
	
	// Redirect to pricing page
	$('.free-trial').click(function(){
		window.location = "/pricing";	
	})
	
	// Event click on menu left
	$('.tour-menu').click(function(){
		$('.tour-menu').removeClass('item-active');
		$(this).addClass('item-active');
		//TourSlide(this);
		var className = $(this).attr('class');
		var classArray = className.split(" ");
		if(classArray != null) {
			items = classArray[0].split("-");
			TourSlide(items[1]);
		}
	})
});

//Fixed scroll
$(window).scroll(function(){
	var $free_trial = $('.free-trial');
	var $menu_left = $('.tour-mid-left');
	// Fixed scroll free trial
	if ($(window).scrollTop() > 80) {
		$free_trial.css({'position': 'fixed', 'top': '0px'}); 
	} else {
		$free_trial.css({'position': 'absolute', 'top': 'auto'});
	}
	// Fixed scroll menu left
	if ($(window).scrollTop() > 220) {
		$menu_left.css({'position': 'fixed', 'top': '0px'}); 
	} else {
		$menu_left.css({'position': 'relative', 'top': 'auto'});
	}
});

// Slide content
function TourSlide($obj){
	$('.tour-items').hide("slide", { direction: "left" }, 700);
	setTimeout(function(){
		$('.content-' + $obj).show("slide", { direction: "right" }, 700);
	}, 500);	
	
}