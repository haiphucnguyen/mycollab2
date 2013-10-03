<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact updated</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top_new.png') no-repeat 0 0 transparent; font-size: 11px; line-height: 11px;" height="11">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_orange.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">Contact Updated</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<div style="font-weight: bold; display: block; border-bottom: 1px solid rgb(212, 212, 212); padding-bottom: 5px; margin-bottom: 10px;">Hi $!userName,</div>
				<div style="display: block; padding: 8px; background-color: rgb(247, 228, 221);">Just wanna let you know that contact has been updated. Here're details about it:</div>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
								<tr>
									<td style="text-align: right;">First Name:&nbsp;</td>
									<td>$!simpleContact.firstname</a></td>
									<td style="text-align: right; min-width: 90px;">Office Phone:&nbsp;</td>
									<td>$!simpleContact.officephone</a></td>
								</tr>
								<tr>
									<td style="text-align: right;">Last Name:&nbsp;</td>
									<td>$!simpleContact.lastname</td>
									<td style="text-align: right; min-width: 90px;">Mobile:&nbsp;</td>
									<td>$!simpleContact.mobile</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Account:&nbsp;</td>
									<td></td>
									<td style="text-align: right;">Home Phone:&nbsp;</td>
									<td>$!simpleContact.homephone</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Title:&nbsp;</td>
									<td>$!simpleContact.title</td>
									<td style="text-align: right;">Other Phone:&nbsp;</td>
									<td>$!simpleContact.otherphone</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Department:&nbsp;</td>
									<td>$!simpleContact.department</td>
									<td style="text-align: right;">Fax:&nbsp;</td>
									<td>$!simpleContact.fax</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Email:&nbsp;</td>
									<td>$!simpleContact.email</td>
									<td style="text-align: right;">Birthday:&nbsp;</td>
									<td>$!simpleContact.birthday</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Assistant:&nbsp;</td>
									<td>$!simpleContact.assistant</td>
									<td style="text-align: right;">Callable:&nbsp;</td>
									<td> #if($!simpleContact.iscallable) Yes #else No #end</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Assistant Phone:&nbsp;</td>
									<td>$!simpleContact.assistantphone</td>
									<td style="text-align: right;">Assignee:&nbsp;</td>
									<td>$!simpleContact.assignuser</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Leader Source:&nbsp;</td>
									<td>$!simpleContact.leadsource</td>
								</tr>
								<tr>
									<td style="text-align: right;">Address:&nbsp;</td>
									<td>$!simpleContact.primaddress</td>
									<td style="text-align: right;">Other Address:&nbsp;</td>
									<td>$!simpleContact.otheraddress</td>		
								</tr>
								<tr>
									<td style="text-align: right;">City:&nbsp;</td>
									<td>$!simpleContact.primcity</td>
									<td style="text-align: right;">Other City:&nbsp;</td>
									<td>$!simpleContact.othercity</td>		
								</tr>
								<tr>
									<td style="text-align: right;">State:&nbsp;</td>
									<td>$!simpleContact.primstate</td>
									<td style="text-align: right;">Other State:&nbsp;</td>
									<td>$!simpleContact.otherstate</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Postal Code:&nbsp;</td>
									<td>$!simpleContact.primpostalcode</td>
									<td style="text-align: right;">Other Postal Code:&nbsp;</td>
									<td>$!simpleContact.otherpostalcode</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Country:&nbsp;</td>
									<td>$!simpleContact.primcountry</td>
									<td style="text-align: right;">Other Country:&nbsp;</td>
									<td>$!simpleContact.othercountry</td>		
								</tr>
								<tr>
									<td style="text-align: right; min-width: 90px; vertical-align: top;">Description:&nbsp;</td>
									<td colspan="3" style="word-wrap: break-word; white-space: normal; word-break: break-all;">$!simpleContact.description</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_footer.png') repeat-y 0 0 transparent; border-top: 1px solid rgb(212, 212, 212);">
				<div style="margin-top: 10px; padding-left: 30px; color: #4e4e4e; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; display: inline-block; width: 295px;">Copyright by <a href="http://www.esofthead.com" style="color: rgb(216, 121, 55); text-decoration: none;">eSoftHead</a><br>&copy; 2013 MyCollab, LLC. All rights reserved.</div>
				<div style="text-align: right; font-size: 10px; display: inline-block; width: 295px;">
					<span style="display: inline-block; vertical-align: top; margin-top: 10px;">Connect with us:&nbsp;</span>
					<a href="${defaultUrls.facebook_url}"><img src="${defaultUrls.cdn_url}fb_social_icon.png" height="25" width="25"></a>
					<a href="${defaultUrls.google_url}"><img src="${defaultUrls.cdn_url}google_social_icon.png" height="25" width="25"></a>
					<a href="${defaultUrls.linkedin_url}"><img src="${defaultUrls.cdn_url}linkedin_social_icon.png" height="25" width="25"></a>
					<a href="${defaultUrls.twitter_url}"><img src="${defaultUrls.cdn_url}twitter_social_icon.png" height="25" width="25"></a>
				</div>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_bottom_new.png') no-repeat 0 0 transparent; line-height: 7px; font-size: 7px;" height="7">&nbsp;</td>
		</tr>
	</table>
</body>
</html>