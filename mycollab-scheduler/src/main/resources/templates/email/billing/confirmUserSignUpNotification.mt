<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
</head>
<body style="background-color: rgb(218, 223, 225); color: #4e4e4e; font: 16px Georgia, serif; padding: 20px 0px;">
	#macro( confirmLink $webLink $displayText )
		<a href="$webLink" style="color: rgb(90, 151, 226); font-size: 12px; text-decoration: none;">$displayText</a>
	#end
	
	#macro( linkBlock $webLink )
		<div style="width: 100%; padding: 20px 15px; background-color: rgb(237, 248, 255);">
			<a href="$webLink" style="color: rgb(76, 131, 182); font-size: 12px; text-decoration: underline; width: 100%; display: inline-block; word-wrap: break-word; white-space: normal; word-break: break-all;">$webLink</a>
		</div>
	#end
	
	<table width="760" cellpadding="0" cellspacing="0" border="0" style="margin: 20px auto; background-color: rgb(255, 255, 255);">
       <tr>
       		<td style="text-align: center;">
       			<img src="${defaultUrls.cdn_url}logo-email-big.png" alt="esofthead-logo" width="218" height="50" style="margin: 0px; padding: 0px;">
       		</td>			
		</tr>
        <tr>
            <td style="padding: 10px 50px; text-align: center;">
						<p style="font-size: 22px;"><b><i>Thank you for choosing MyCollab!</i></b><p>
						<p>You are just one click away from completing your account registration: <p>
						<div style="background-color: rgb(32, 36, 35); tex-align: center; padding: 3px 0px; width: 330px; margin: 0px auto;">
							<a style="text-decoration:none;" href="$!linkConfirm"/><span style="font-size: 22px; text-transform: uppercase;">Confirm your e-mail</span></a>
						</div> 
						<br>
						#set ($loginUrl = "https://" + $user.Subdomain + ".esofthead.com/")
						<p>Access your account anytime from 
						#confirmLink ($loginUrl $loginUrl)
						 (maybe bookmark this page for future reference).<br>
						Login with your email address 
						#set ($mailToUrl = "mailto:" + $user.Email)
						#confirmLink ($mailToUrl $user.Email)
						 and the password you created.</p>
						<span>By clicking this link, you agree to the 
						#confirmLink ("https://www.mycollab.com/terms" "Terms of Service"
						 and the 
						#confirmLink ("https://www.mycollab.com/privacy" "Privacy Policy"
						</p>
						<p>
						If clicking on the link does not work, just copy and paste the following address into your browser:
						#webLink ($!linkConfirm)
						If you are still having problems, simply forward this e-mail 
						#confirmLink ("mailto:support@mycollab.com" "support@mycollab.com"
						, and we will be happy to help you. <br><br>
						
						<span style="font-weight: bold;">Have a productive day!</span>
						<p>P/S: Hope you enjoy using Mycollab to grow the sales in your business, and remember you can switch between plans during the trial!</p>
					</div>
					<div id="contentFooter" style="padding-top:10px;">
						Best regards, <br>
						<span style="font-weight: bold;">MyCollab's Customer Support Team </span><br>
						(+84) 862-924-513 <br>
						<a href="mailto:support@mycollab.com" style="text-decoration : none;"><span style="font-weight: bold; color:#709AC5">support@mycollab.com </span></a>
					</div>
				</div>
			</td>
		</tr>
		#parse("templates/email/footer.mt")
	</table>
</body> 
</html>	