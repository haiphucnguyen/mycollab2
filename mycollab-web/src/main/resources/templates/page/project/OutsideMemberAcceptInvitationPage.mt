<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<style media="screen" type="text/css">
.container {
}

#header {
    background-color: #1777AD;
    border-bottom: 6px solid #77BAE0;
    height: 60px;
    width: 100%;
}
.header-mid {
    display: block;
    margin: 0 auto;
    width: 1100px;
}
.header-mid .a {
    color: #000000;
    text-decoration: none;
}

.body-style {
    background-color: #F9F9F9;
    float: right;
    margin-bottom: 20px;
    padding-left: 30px;
    width: 840px;
}
#mainBody{
   background-color: #FFFFFF;
   -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    padding-left : 11px;
    padding-right: 11px;
    padding-bottom: 11px;
    font-size: 12px;
    text-align: left;
    padding-top : 8px;
    border: 1px solid rgb(169, 169, 169);
    border-radius : 3px;
}

#mainContent{
     background-color: #F6F6F6;
   -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    display: block; 
    padding: 10px 10px 8px 10px;
    border: 1px solid rgb(169, 169, 169);
}

#bottom{
    align:center;
    padding-left: 160px; 
    padding-right: 100px; 
    color : blue;
    font-style: bold;
    background-color: rgb(239, 239, 239);
    padding-bottom: 100px;
    text-align: center;
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
	cursor: pointer;
}

</style>
<title>Member accept the invitation page</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<div class="header-mid">
				<a href=".">
					<img src="${defaultUrls.cdn_url}logo_mycollab.png" alt="Logo Mycollab">
				</a>
			</div>
		</div>
		<div id="body" style="background-color: rgb(239, 239, 239); width: 100%;">
			<div id="spacing" style="height:30px; background-color: rgb(239, 239, 239);"></div>
			<div style="padding-left: 300px; padding-right: 250px; background-color: rgb(239, 239, 239); height: 100%;">
				<div id="mainBody">
					<div id="title">
						<p><span>Choose Your New Password</span></p>
					</div>
					<hr>
					<div>
						<table>
						 	<tr>
						 		<td style="width: 350px;vertical-align:top; padding-top:12px;">Please enter a new password!</td>
						 		<td style="width: 400px; display: inline-block; vertical-align: top;">
									<div id="mainContent">
						  				 Welcome <span style="font-style:italic; font-size:14px;">$email</span>.<br>
										 Congratulations to join MyCollab!
										<br><br>
											Password: 
										<br>
						   				<input id="password" size="30" maxlength="50" name="password" type="password" />
						    			<div style="padding-top: 10px;">
											<button class="v-button-bluebtn" type="button" onclick="return createAccount();">Create</button>
											<button class="v-button-bluebtn" type="button" onclick="return cancel();">Cancel</button>
						     			</div>    
									</div>
								</td>
						 	</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="bottom">
			    <p>
					<span style="font-size:12px;">Terms of Service</span>
					<span>|</span>
					<span style="font-size:12px;">Privacy Policy</span>
					<span>|</span>
					<span style="font-size:12px;">Copyright 2013 MyCollab. All rights reserved.</span>
				</p>
			</div>
		</div>
	</div>
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
			alert("Please enter user name");
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
		      	 		alert(data);
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