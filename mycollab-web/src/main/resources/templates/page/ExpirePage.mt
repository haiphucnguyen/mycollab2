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
<title>Expire to registry</title>
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
						<p><span>Expire to registry</span></p>
					</div>
					<hr>
					<div>
						<p><span>Sorry! You can only accept this invitation within 7 days. Please ask your Project Admin to resend the invitation.
						</span></p>
						<div style="padding-top: 10px;">
							<button class="v-button-bluebtn" type="button" onclick="return login();">Login</button>
		     			</div>
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
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	function login(){
		window.location.assign("$!loginURL");
	}
</script>				
</html>
