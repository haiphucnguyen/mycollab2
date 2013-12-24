$(document).ready(function(){
	$('.free-trial-cnt-2').hide();
	//$('.items-1').addClass('item-active');
	
	// Redirect to pricing page
	$('.free-trial').click(function(){
		window.location = "/pricing";	
	});	
	
	/*$('#tour-navigator-mobile').click(function(){
		if ($('.tour-mid-left').hasClass('active'))
			$('.tour-mid-left').removeClass('active');
		else
			$('.tour-mid-left').addClass('active');
	});*/
	
});

$(document).mouseup(function (e)
		{
		    var container = $('.tour-mid-left');

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length === 0) // ... nor a descendant of the container
		    {
		        container.removeClass('active');
		    }
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
	$(".rslides").responsiveSlides({
		  auto: true,             // Boolean: Animate automatically, true or false
		  speed: 500,            // Integer: Speed of the transition, in milliseconds
		  timeout: 4000,          // Integer: Time between slide transitions, in milliseconds
		  pager: true,           // Boolean: Show pager, true or false
		  nav: true,             // Boolean: Show navigation, true or false
		  random: false,          // Boolean: Randomize the order of the slides, true or false
		  pause: false,           // Boolean: Pause on hover, true or false
		  pauseControls: true,    // Boolean: Pause when hovering controls, true or false
		  prevText: "Previous",   // String: Text for the "previous" button
		  nextText: "Next",       // String: Text for the "next" button
		  maxwidth: 300,           // Integer: Max-width of the slideshow, in pixels
		  navContainer: "",       // Selector: Where controls should be appended to, default is after the 'ul'
		  manualControls: "",     // Selector: Declare custom pager navigation
		  namespace: "centered-btns",   // String: Change the default namespace used
		  before: function(){},   // Function: Before callback
		  after: function(){}     // Function: After callback
		});
});

//Fixed scroll
$(window).scroll(function(){
	var $free_trial = $('.free-trial');
	var $menu_left = $('.tour-mid-left');
	// Fixed scroll free trial
	if ($(window).scrollTop() > 104) {
		$('.free-trial-cnt-1').hide();
		$('.free-trial-cnt-2').show();
		
	} else {
		$('.free-trial-cnt-2').hide();
		$('.free-trial-cnt-1').show();
		
	}
	if ($(window).scrollTop() > 149)
		{
			$('.free-trial-cnt-3').css({'position': 'fixed', 'top': '0px', 'right':'0'});
		}
	else
		{
			$('.free-trial-cnt-3').css({'position': 'relative', 'top': 'auto'});
		}
	// Fixed scroll menu left
	if ($(window).scrollTop() > 260) {
		$menu_left.css({'position': 'fixed', 'top': '0px'}); 
		
	} else {
		$menu_left.css({'position': 'relative', 'top': 'auto'});
	}
});