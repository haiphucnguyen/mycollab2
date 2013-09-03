$(document).ready(function(){
	$('.free-trial-cnt-2').hide();
	//$('.items-1').addClass('item-active');
	
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

$(window).load(function() {
	$('#slider').nivoSlider({
        pauseOnHover: false,
        pauseTime: 3000,
        onImageLoad: function(img) {
        },
        onImageChange: function(img) {
        }
      });
});

//Fixed scroll
$(window).scroll(function(){
	var $free_trial = $('.free-trial');
	var $menu_left = $('.tour-mid-left');
	// Fixed scroll free trial
	if ($(window).scrollTop() > 80) {
		$('.free-trial-cnt-1').hide();
		$('.free-trial-cnt-2').show();
	} else {
		$('.free-trial-cnt-2').hide();
		$('.free-trial-cnt-1').show();
	}
	// Fixed scroll menu left
	if ($(window).scrollTop() > 220) {
		$menu_left.css({'position': 'fixed', 'top': '0px'}); 
	} else {
		$menu_left.css({'position': 'relative', 'top': 'auto'});
	}
});