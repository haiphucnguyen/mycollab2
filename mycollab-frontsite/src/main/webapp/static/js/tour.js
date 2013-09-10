$(document).ready(function(){
	$('.free-trial-cnt-2').hide();
	//$('.items-1').addClass('item-active');
	
	// Redirect to pricing page
	$('.free-trial').click(function(){
		window.location = "/pricing";	
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