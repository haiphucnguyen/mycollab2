<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
</head>
<body>
	<table width="800" cellpadding="0" cellspacing="0" class="wrapContent">
       <tr>
       		<td style="text-align: center; padding: 50px 0px 0px;">
       			<img src="${defaultUrls.cdn_url}icons/logo_email.png" alt="The power productivity tool for your organization" width="130" height="30" style="margin: 0px; padding: 0px;">
       		</td>			
		</tr>
        <tr>
            <td style="padding: 20px 30px; text-align: center;">
				<p style="font-size: 22px; padding-top: 10px;"><b><i>Oh no, your trial has ended!</i></b><p>
				<hr size="1">
				<div id="contentBody" style="text-align: left;">
					<p>Hi <b>$!userName</b>,</p>
					<p>
					Your free trial of MyCollab has just ended. If you wish to continue using MyCollab to manage projects and tasks, you may enter a payment method in your account under Billing Info. <br><br>
					
					To access your account, visit  
					#confirmLink( $!link $!link )
					<br><br>
					
					If you decide not to upgrade to a paid plan, you may still use MyCollab free for personal stuff. You don't need to do anything if you want to use it personally. We'll simply convert your account for you. If you wish to cancel your account entirely, please login and go to your Account section.<br></br>
					
					<p><b>Thank you!</b></p>
					</p>
					<div style="padding-top:10px;">
						<hr size="1">
					</div>
				</div>
				<div id="contentFooter" style="padding-top:10px; text-align: left;">
					<span>Question?</span>
					#confirmLink( "mailto:support@mycollab.com" "Get help" )
				</div>
			</td>
		</tr>
		#parse("mailFooter_en-US.html")
	</table>
</body>
</html>	