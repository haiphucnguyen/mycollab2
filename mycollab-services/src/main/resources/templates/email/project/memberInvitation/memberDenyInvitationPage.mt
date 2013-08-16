<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style media="screen" type="text/css">
#container {
}

#footer-home {
    width: 100%;
    height: 175px;
    background: url("$!urlfooterPng") repeat-x scroll 0% 0% transparent;
    clear: both;
}

#footer-home .footer-copyright {
    color: #606060;
}
.footer-copyright {
    color: #FFFFFF;
    font-size: 12px;
}
#footer-home .footer-copyright h1 {
    color: #FFFFFF;
    font-family: 'Monda';
}
#footer-home .wrapper {
    width: 1100px;
    margin: 0px auto;
}

</style>
<title>New bug created</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td>
				fsfdsa
				<div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">New Bug Comment</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
			<td>
				<div style="display: block; padding: 8px;">Please feedback, why do you deny invatation ?</div>
				<div style="display: block; padding: 8px;">
					<textarea id="message" rows="5" cols="100">
					</textarea>
				</div>
				<div style="align:center;">
					<button class="v-button-bluebtn" type="button" onclick="return sendEmailFeedBack();">Send</button>
					<button class="v-button-bluebtn" type="button" onclick="return skip();">Skip</button>
				</div>
			</td>
		</tr>
	</table>
	<div class="container"></div>
	<div class="footer-home">
		<div class="wrapper">
			<div class="footer-copyright"><h1>MYCOLLAB</h1>
                         ©2010-2013 MyCollab, LLC. All rights reserved.</div>
        </div>
	</div>
	
	<input type="hidden" id="inviterEmail" value="$!inviterEmail">
	<input type="hidden" id="url" value="$!redirectURL">
	<input type="hidden" id="memberEmail" value="$!memberEmail">
	<input type="hidden" id="memberName" value="$!memberName">
	<input type="hidden" id="inviterName" value="$!inviterName">
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	function sendEmailFeedBack(){
	var url = encodeURI($('#url').val() + $('#inviterEmail').val() + "/" + $('#memberEmail').val()+"/"+ $('#message').val().trim() + "/" + $('#memberName').val() + "/" + $('#inviterName').val());
		 $.ajax({
		      type: 'POST',
		      url: url,
		      complete: function(data){
		         alert('Send Email successfully');
		      }
		    });
	}
	function skip(){
		window.close();
	}
</script>				
</html>