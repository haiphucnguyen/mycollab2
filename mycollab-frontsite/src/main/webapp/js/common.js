var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-39177678-1']);
_gaq.push(['_trackPageview']);

(function() {
	var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();

$(document).ready(function(){
	// Footer position
	var window_height = $(window).height();
	var content_height = $('body').height();
	console.log('window_height: '+window_height);
	console.log('content_height: '+content_height);
	if(content_height <= window_height) {
		$('#footer').css({'position': 'absolute', 'bottom': 0});
	} 
});

// Event resize
$(window).resize(function() {
	var window_height = $(window).height();
	var content_height = $('body').height();
	
	if(content_height < window_height) {
		$('#footer').css({'position': 'absolute', 'bottom': 0});
	}
});