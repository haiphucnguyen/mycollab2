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
    height: 70px;
    width: 100%;
    text-align: left;
    vertical-align: middle;
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
    height: 300px;
    width: 800px;
}

#mainContent{
     background-color: #F6F6F6;
   -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    display: block; 
    padding: 10px 10px 8px 10px;
    border: 1px solid rgb(169, 169, 169);
    height : 200px;
}

#bottom{
    padding-left: 160px; 
    padding-right: 100px; 
    color : blue;
    font-style: bold;
    background-color: rgb(239, 239, 239);
    padding-bottom: 100px;
    text-align: center;
    height : 100%;
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
<title>User recovery password page</title>
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
			<div id="spacing" style="height:60px; background-color: rgb(239, 239, 239);"></div>
			<div style="padding-left: 300px; padding-right: 250px; background-color: rgb(239, 239, 239); height: 100%;">
				<div id="mainBody">
					<div id="title">
						<p><span style="color: #196893; font: 22px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;">Choose your new password
						</span></p>
					</div>
					<hr>
					<div>
						<table>
						 	<tr>
						 		<td style="width: 350px; vertical-align:top; padding-left: 10px; padding-top:12px;font-size: 18px; color: #616161;">Please enter a new password!</td>
						 		<td style="width: 400px; display: inline-block; vertical-align: top;">
									<div id="mainContent">
						  				 <div>
											<form>
											<table border="0">
											<tbody>
											<tr>
												<td><label for="password"><span style="font-size:14px;">New password:</span></label></td>
											</tr>
											<tr>
												<td><input id="password" maxlength="45" name="password" type="password" style="width:350px;"/></td>
											</tr>
											<tr>
   												<td style="height:10px;"></td>
											</tr>
											<tr>
												<td><label for="password"><span style="font-size:14px;">Confirm new password:</span></label></td>
											</tr>
											<tr>
												<td><input id="repassword" maxlength="45" name="password" type="password" style="width:350px;"/></td>
											</tr>
											</tbody></table>
											</form>
										</div>
										<div style="padding-top:10px; padding-left:8px;">
											<button class="v-button-bluebtn" type="button" onclick="return updateInfoAction();">Reset</button>
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
			    	<a javascrip="void(0);" href="https://www.mycollab.com/terms" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Terms of Service</span></a> &nbsp&nbsp&nbsp
					<span style="color: #000000">|</span>
					&nbsp&nbsp&nbsp<a javascrip="void(0);" href="https://www.mycollab.com/privacy" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Privacy Policy</span></a>&nbsp&nbsp&nbsp
					<span style="color: #000000">|</span>
					&nbsp&nbsp&nbsp<a javascrip="void(0);" href="https://www.mycollab.com" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Copyright 2013 MyCollab. All rights reserved.</span></a>
				</p>
			</div>
		</div>
	</div>
	<input type="hidden" id="username" value="$!username">
	<input type="hidden" id="loginURL" value="$!loginURL">
	<input type="hidden" id="redirectURL" value="$!redirectURL">
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#bottom').css("padding-bottom","200px");
	});
	function updateInfoAction(){
		if ($('#password').val() == ""){
			alert("Please enter password");
			return;
		}
		if($('#repassword').val()==""){
			alert("Please retype password");
			return;
		}
		if($('#password').val() != $('#repassword').val()){
			alert("You enter password mismatch with retype password, please re-enter");
			return;
		}
		var url = encodeURI($('#redirectURL').val());
		 $.ajax({
		      type: 'POST',
		      url: url,
		      data : {
		      			username : $('#username').val().trim(), 
		      			password : $('#password').val().trim()
		      		},
		      success: function(data){
		      	 if(data!=null){
		      	 	if(data.length > 0){
		      	 		alert(data);
		      	 	}else{
		      	 		alert("Your account has been updated.");
		      	 		window.location.assign("$!loginURL");
		      	 	}
		      	 }
		      }
		});
	}
</script>				
</html>