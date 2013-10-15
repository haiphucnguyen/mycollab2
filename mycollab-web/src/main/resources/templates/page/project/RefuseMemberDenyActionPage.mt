<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
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
<title>Refuse deny action page</title>
</head>
<body style="height:100%;">
	<div style="height: 100%; padding-top:100px; padding-left: 150px;">
		<table cellpadding="0" cellspacing="0" border="0" style="height: 100%;margin: 0px auto; padding-top: 50px;">
			<tr>
				<td style="height: 100%; width: 250px; display: inline-block; vertical-align: top; margin-top: 45px; border-right:1px dotted black;">
					<div>
						<a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}logo_mycollab.png" height="42" width="225"></a>
						<p><span style="font-style:italic; font-size:14px;">&copy;2010-2013 MyCollab, LLC. All rights reserved.</span></p>
					</div>
				</td>
				<td style="width: 600px; display: inline-block; vertical-align: center;">
					<div style="display: block; padding: 40px 8px 8px 30px;">
		  				Sorry! You can not leave this project by yourself. Please request your project admin remove you out of project if you like so.
		    			<div style="padding-top: 10px;">
							<button class="v-button-bluebtn" type="button" onclick="return login();">Login</button>
		     			</div>    
					</div>
				</td>
			</tr>
		</table>
	</div>	
	<div class="container"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	function login(){
		window.location.assign("$!projectLinkURL");
	}
</script>				
</html>
