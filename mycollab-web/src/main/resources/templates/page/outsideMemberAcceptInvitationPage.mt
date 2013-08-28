<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style media="screen" type="text/css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
.container {
}

.footer-home {
    width: 100%;
    height: 100px;
    background: url('${defaultUrls.cdn_url}footer_home_bg.png') repeat-x scroll 0% 0% transparent;
    clear: both;
	position: relative;
	z-index: 10;
}

.footer-home .footer-copyright {
    color: #606060;
}
.footer-copyright {
    color: #FFFFFF;
    font-size: 12px;
}
.footer-home .footer-copyright h1 {
    color: #FFFFFF;
    font-family: 'Monda';
}
.footer-home .wrapper {
    width: 1100px;
    margin: 0px auto;
}
.v-button-bluebtn{
	background: url('${defaultUrls.cdn_url}grad-dark-bottom2.png') repeat-x left bottom
		#2599c8;
	border: 1px solid #093768;
	color: #FFFFFF;
	text-shadow: 1px 1px 0px #1570cd;
	border-radius: 3px;
	padding: 3px 13px;
}
.v-button-bluebtn:hover {
	background: url('${defaultUrls.cdn_url}grad-dark-bottom2.png') repeat-x left bottom
		#1377b3;
	border: 1px solid #093768;
}

</style>
<title>Member accept the invitation page</title>
</head>
<body>
	<table height="400" width="1000" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto; padding-top: 50px;">
		<tr>
			<td style="height: 100%; width: 250px; display: inline-block; vertical-align: top; margin-top: 45px; border-right:1px dotted black;">
				<div>
					<a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}logo_mycollab.png" height="42" width="225"></a>
					&copy;2010-2013 MyCollab, LLC. All rights reserved.
				</div>
			</td>
			<td style="width: 600px; display: inline-block; vertical-align: top;">
				<div style="display: block; padding: 8px 8px 8px 20px;">
					<a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}welcome.png" height="25" width="50"></a> $name , $email . <br>
					Congratulations to join MyCollab!
				</div>
				<div style="display: block; padding: 8px 8px 8px 20px;">
					Password: 
					<input id="password" width="300px" maxlength="45" name="password" type="password" />
				</div>
				<div align="center" style="padding-top: 30px;">
						<button class="v-button-bluebtn" type="button" onclick="return createAccount();">Create</button>
						<button class="v-button-bluebtn" type="button" onclick="return cancel();">Cancel</button>
				</div>
			</td>
		</tr>
	</table>
	<div class="container"></div>
	<input type="hidden" id="handelCreateAccountURL" value="$!handelCreateAccountURL">
	<input type="hidden" id="projectId" value="$!projectId">
	<input type="hidden" id="sAccountId" value="$!sAccountId">
	<input type="hidden" id="email" value="$!email">
	<input type="hidden" id="roleId" value="$!roleId">
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(function(){
	});
	function createAccount(){
		if ($('#username').val() == ""){
			$('#requireMsg').html("Please enter user name");
			return;
		}
		var url = encodeURI($('#handelCreateAccountURL').val());
		 $.ajax({
		      type: 'POST',
		      url: url,
		      data : { email : $('#email').val().trim(),
		      			password : $('#password').val().trim(), 
		      			roleId : $('#roleId').val().trim(), 
		      			projectId : $('#projectId').val().trim(),
		      			sAccountId : $('#sAccountId').val()},
		      success: function(data){
		      	 if(data!=null){
		      	 	if(data.length > 0){
		      	 		$('#requireMsg').html(data).show();
		      	 	}else{
		      	 		alert("Your account has been created.");
		      	 		window.location.assign("$!projectLinkURL");
		      	 	}
		      	 }
		      }
		});
	}
	function cancel(){
		window.location.assign("$!projectLinkURL");
	}
</script>				
</html>
