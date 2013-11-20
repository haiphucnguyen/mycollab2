$(document).ready(function(){
	if(window.location.hash.replace("#", "") == "download") {
		$("#download-tab").addClass("selected");
		$("#onsite-tab").removeClass("selected");
		$(".tab1").css("display", "none");
		$(".tab2").css("display", "block");
	}
	$("#download-tab").click(function(){
		$(this).addClass("selected");
		$("#onsite-tab").removeClass("selected");
		$(".tab1").css("display", "none");
		$(".tab2").css("display", "block");
	});
	
	$("#onsite-tab").click(function(){
		$(this).addClass("selected");
		$("#download-tab").removeClass("selected");
		$(".tab1").css("display", "block");
		$(".tab2").css("display", "none");
	});
});