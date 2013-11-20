/* Sticky Tooltip script (v1.0)
* Created: Nov 25th, 2009. This notice must stay intact for usage 
* Author: Dynamic Drive at http://www.dynamicdrive.com/
* Visit http://www.dynamicdrive.com/ for full source code
*/


var stickytooltip={
	tooltipoffsets: [20, -30], //additional x and y offset from mouse cursor for tooltips
	fadeinspeed: 200, //duration of fade effect in milliseconds
	rightclickstick: true, //sticky tooltip when user right clicks over the triggering element (apart from pressing "s" key) ?
	stickybordercolors: ["black", "darkred"], //border color of tooltip depending on sticky state
	stickynotice1: ["Press \"s\"", "or right click", "to sticky box"], //customize tooltip status message
	stickynotice2: "Click outside this box to hide it", //customize tooltip status message

	//***** NO NEED TO EDIT BEYOND HERE

	isdocked: true,

	positiontooltip:function($, $tooltip, e){
		var x=e.pageX+this.tooltipoffsets[0], y=e.pageY+this.tooltipoffsets[1]
		var tipw=$tooltip.outerWidth(), tiph=$tooltip.outerHeight(), 
		x=(x+tipw>$(document).scrollLeft()+$(window).width())? x-tipw-(stickytooltip.tooltipoffsets[0]*2) : x
		y=(y+tiph>$(document).scrollTop()+$(window).height())? $(document).scrollTop()+$(window).height()-tiph-10 : y
		$tooltip.css({left:x, top:y})
	},
	
	showbox:function($, $tooltip, e){
		$tooltip.fadeIn(this.fadeinspeed)
		this.positiontooltip($, $tooltip, e)
	},

	hidebox:function($, $tooltip){
		if (this.isdocked){
			$tooltip.stop(false, true).hide()
			$tooltip.css({borderColor:'black'}).find('.stickystatus:eq(0)').css({background:this.stickybordercolors[0]}).html(this.stickynotice1)
		}
	},

	docktooltip:function($, $tooltip, e){
		this.isdocked=true
		$tooltip.css({borderColor:'darkred'}).find('.stickystatus:eq(0)').css({background:this.stickybordercolors[1]}).html(this.stickynotice2)
	},


	init:function(targetselector, tipid){
			var $targets=$(targetselector)
			var $tooltip=$('#'+tipid).appendTo(document.body)
			if ($targets.length==0)
				return
			var $alltips=$tooltip.find('div.atip')
			if (!stickytooltip.rightclickstick)
				stickytooltip.stickynotice1[1]=''
			stickytooltip.hidebox($, $tooltip)
			$targets.bind('mouseenter', function(e){
				$alltips.hide().filter('#'+$(this).attr('data-tooltip')).show()
				stickytooltip.showbox($, $tooltip, e)
			})
			$targets.bind('mouseleave', function(e){
				stickytooltip.hidebox($, $tooltip)
			})
			$targets.bind('mousemove', function(e){
				if (!stickytooltip.isdocked){
					stickytooltip.positiontooltip($, $tooltip, e)
				}
			})
			$tooltip.bind("mouseenter", function(){
				stickytooltip.docktooltip($, $tooltip, null)
			})
			$tooltip.bind("click", function(e){
				e.stopPropagation()
			})
			$(this).bind("click", function(e){
				if (e.button==0){
					stickytooltip.isdocked=true
					stickytooltip.hidebox($, $tooltip)
				}
			})
			$(this).bind("contextmenu", function(e){
				if (stickytooltip.rightclickstick && $(e.target).parents().andSelf().filter(targetselector).length==1){ //if oncontextmenu over a target element
					stickytooltip.docktooltip($, $tooltip, e)
					return false
				}
			})
			$(this).bind('keypress', function(e){
				var keyunicode=e.charCode || e.keyCode
				if (keyunicode==115){ //if "s" key was pressed
					stickytooltip.docktooltip($, $tooltip, e)
				}
			})
			stickytooltip.docktooltip($, $tooltip, null)
	}
}

//stickytooltip.init("targetElementSelector", "tooltipcontainer")
stickytooltip.init("*[data-tooltip]", "mystickytooltip")

function overIt(dateTimeTypeIdStr, type, typeId, url, sAccountId){
	var idDIVserverdata = "serverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "mystickyTooltip"+dateTimeTypeIdStr;
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: type, typeId: typeId , sAccountId : sAccountId},
		      success: function(data){
		      	 if(data!=null){
		      	 	if(data.length > 0){
		      	 		$("#"+idDIVserverdata).html(data);
		      	 	}else{
		      	 		$("#"+idDIVserverdata).html("Sorry this item has removed!");
		      	 	}
					stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
	}
}