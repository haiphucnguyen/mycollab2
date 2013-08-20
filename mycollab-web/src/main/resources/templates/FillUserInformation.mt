<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style media="screen" type="text/css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
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
			<td style="height: 200px; width: 250px; display: inline-block; vertical-align: top; margin-top: 45px; border-right:1px dotted black;">
				<div>
					<a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}logo_mycollab.png" height="42" width="225"></a>
				</div>
			</td>
			<td style="width: 600px; display: inline-block; vertical-align: top;">
				<div id="divHolder"></div>
			</td>
		</tr>
	</table>
	<div class="container"></div>
	<footer style="padding-top: 100px;">
		<div class="footer-home">
		<div class="wrapper">
			<div class="footer-copyright"><h1>MYCOLLAB</h1>
                          	&copy;2010-2013 MyCollab, LLC. All rights reserved.</div>
        </div>
	</div>
	</footer>
	<div id="welcomeBody" style="display:none">
		<div style="display: block; padding: 8px 8px 8px 20px;">
			<a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}welcome.png" height="25" width="50"></a> $!username . <br>
			Thank you for accepted the invitation !
			Come to MyCollab, you will feel new things in manage your business, experience new ways, 
			faster and smarter, the maximum help you in managing your business. 
			My Collab suitable for all small and large business management model. 
			We always listen from you, and develop to reach the best customer satisfaction. 
			What are you waiting? Now we take a tour . 
		</div>
		<div style="display: block; padding: 8px 8px 8px 20px;">
			At frist you must update your informations!
			<button class="v-button-bluebtn" type="button" onclick="return updateInfo();">Update your informations</button>
		</div>
		<div style="display: block; padding: 8px 8px 8px 20px;">
			Or use default password: 123456
			<a href=""><span style="color:blue; font-style:italic">Go to login-page</span></a>
		</div>
	</div>	
	<div id="createNewAccountForm" style="display:none;">
		<div style="display:block ; padding: 20px 8px 8px 20px;">
		<form>
		<table border="0">
		<tbody>
		<tr>
			<td><label>First name: </label> </td>
			<td><input id="firstname" maxlength="30" name="firstname" type="text" /> </td>
			
			<td style="padding-left: 15px;"><label>Last name: </label></td>
			<td><input id="lastname" maxlength="30" name="lastname" type="text" /> </td>
		</tr>
		<tr>
			<td><label>User Name:</label> </td>
			<td><input disabled="disabled" maxlength="45" value="$!username" type="text" /> </td>
			
			<td style="padding-left: 15px;"><label>Birthdate:</label> </td>
			<td><input id="birthdate" maxlength="45" name="birthdate" type="text" /></td>
		</tr>
		<tr>
			<td><label>Email Address:</label> </td>
			<td><input disabled="disabled" maxlength="45" value="$!email" type="text" /></td>
			
			<td style="padding-left: 15px;"><label>Website:</label> </td>
			<td><input id="website" maxlength="45" name="website" type="text" /></td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><input id="password" maxlength="45" name="password" type="password" /></td>
			
			<td style="padding-left: 15px;"><label for="password">Retype-Password:</label></td>
			<td><input id="repassword" maxlength="45" name="password" type="password" /></td>
		</tr>
		</tbody></table>
		</form>
		
		<div style="display: block; padding: 10px 8px 8px 20px;">
			<p><span style="color:blue; font-style:bold"> More informations
			</span></p>
		</div>
		
		<table border="0">
		<tbody>
		<tr>
			<td><label>Country: </label> </td>
			<td><input id="country" maxlength="30" name="country" type="text" /> </td>
			
			<td style="padding-left: 15px;"><label>Company: </label> </td>
			<td><input id="company" maxlength="30" name="company" type="text" /> </td>
		</tr>
		<tr>
			<td><label>Home phone: </label> </td>
			<td><input id="homePhone" maxlength="30" name="homePhone" type="text" /> </td>
			
			<td style="padding-left: 15px;"><label>Work phone: </label></td>
			<td><input id="workphone" maxlength="30" name="workphone" type="text" /> </td>
		</tr>
		<tr>
			<td><label>Skype: </label> </td>
			<td><input id="skype" maxlength="30" name="skype" type="text" /> </td>
		</tr>
		</table>
		
		<div align="center" style="padding-top: 30px;">
				<button class="v-button-bluebtn" type="button" onclick="return createAccount();">Update</button>
				<button class="v-button-bluebtn" type="button" onclick="return cancel();">Cancel</button>
		</div>
		<div style="display: block; padding: 12px 8px 8px 20px;">
			<p><span id="requireMsg" style="color:red; font-style:italic">
			</span></p>
		</div>
	</div>
	</div>
	
	<input type="hidden" id="username" value="$!username">
	<input type="hidden" id="accountId" value="$!accountId">
	<input type="hidden" id="redirectURL" value="$!redirectURL">
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#divHolder').html($('#welcomeBody').html());
		$('#birthdate').datepicker();
	});
	function updateInfo(){
		$('#divHolder').html($('#createNewAccountForm').html())
	}
	function updateInfoAction(){
		$('#requireMsg').html("").hide();
		if ($('#password').val() == ""){
			$('#requireMsg').html("Please enter user name");
			return;
		}
		if ($('#firstname').val().trim() == "" && $('#lastname').val().trim() == ""){
			$('#requireMsg').html("Please enter last name");
			return;
		}
		var url = encodeURI($('#redirectURL').val());
		 $.ajax({
		      type: 'POST',
		      url: url,
		      data : {firstname: $('#firstname').val().trim(), lastname: $('#lastname').val().trim(),
		      			username : $('#username').val().trim(), 
		      			password : $('#password').val().trim(), accountId : $('#accountId').val() ,
		      			birthdate : $('#birthdate').val().trim(), website : $('website').val().trim(),
		      			country : $('#country').val().trim() , company : $('#company').val().trim(),
		      			homePhone : $('#homePhone').val().trim(), workphone : $('#workphone').val().trim(),
		      			skype : $('#skype').val().trim()},
		      success: function(data){
		      	 if(data!=null){
		      	 	if(data.length > 0){
		      	 		$('#requireMsg').html(data).show();
		      	 	}else{
		      	 		alert("Your account has been created.");
		      	 		window.location.assign("$!loginURL");
		      	 	}
		      	 }
		      }
		});
	}
	function cancel(){
		$('#divHolder').html($('#welcomeBody').html());
	}
</script>				
</html>