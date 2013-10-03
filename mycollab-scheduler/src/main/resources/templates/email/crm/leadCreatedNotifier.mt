<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Lead created</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top_new.png') no-repeat 0 0 transparent; font-size: 11px; line-height: 11px;" height="11">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_orange.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">New Lead</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<div style="font-weight: bold; display: block; border-bottom: 1px solid rgb(212, 212, 212); padding-bottom: 5px; margin-bottom: 10px;">Hi $!userName,</div>
				<div style="display: block; padding: 8px; background-color: rgb(247, 228, 221);">Just wanna let you know that a new lead <a href="$!hyperLinks.leadURL" style="color: rgb(216, 121, 55); text-decoration: underline;">$!simpleLead.leadName</a> has been created. Here're details about it:</div>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
								<tr>
									<td style="text-align: right;">First Name:&nbsp;</td>
									<td>$!simpleLead.firstname</td>
									<td style="text-align: right; min-width: 90px;">Email:&nbsp;</td>
									<td><a href="mailto:$!simpleLead.email" style="color: rgb(216, 121, 55); text-decoration: underline;">$!simpleLead.email</a></td>		
								</tr>
								<tr>
									<td style="text-align: right;">Last Name:&nbsp;</td>
									<td>$!simpleLead.lastname</td>
									<td style="text-align: right; min-width: 90px;">Office Phone:&nbsp;</td>
									<td>$!simpleLead.officephone</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Title:&nbsp;</td>
									<td>$!simpleLead.title</td>
									<td style="text-align: right;">Mobile:&nbsp;</td>
									<td>$!simpleLead.mobile</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Department:&nbsp;</td>
									<td>$!simpleLead.department</td>
									<td style="text-align: right;">Other Phone:&nbsp;</td>
									<td>$!simpleLead.otherphone</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Account Name:&nbsp;</td>
									<td>$!simpleLead.accountName</td>
									<td style="text-align: right;">Fax:&nbsp;</td>
									<td>$!simpleLead.fax</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Lead Source:&nbsp;</td>
									<td>$!simpleLead.leadsource</td>
									<td style="text-align: right;">Web Site:&nbsp;</td>
									<td><a href="$!simpleLead.website" style="color: rgb(216, 121, 55); text-decoration: underline;">$!simpleLead.website</a></td>		
								</tr>
								<tr>
									<td style="text-align: right;">Industry:&nbsp;</td>
									<td>$!simpleLead.industry</td>
									<td style="text-align: right;">Status&nbsp;</td>
									<td>$!simpleLead.status</td>		
								</tr>
								<tr>
									<td style="text-align: right;">No of Employees:&nbsp;</td>
									<td>$!simpleLead.noemployees</td>
									<td style="text-align: right;">Assignee:&nbsp;</td>
									<td>$!simpleLead.assignuser</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Address:&nbsp;</td>
									<td>$!simpleLead.primaddress</td>
									<td style="text-align: right;">Other Address:&nbsp;</td>
									<td>$!simpleLead.otheraddress</td>		
								</tr>
								<tr>
									<td style="text-align: right;">City:&nbsp;</td>
									<td>$!simpleLead.primcity</td>
									<td style="text-align: right;">Other City:&nbsp;</td>
									<td>$!simpleLead.othercity</td>		
								</tr>
								<tr>
									<td style="text-align: right;">State:&nbsp;</td>
									<td>$!simpleLead.state</td>
									<td style="text-align: right;">Other State:&nbsp;</td>
									<td>$!simpleLead.otherstate</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Postal Code:&nbsp;</td>
									<td>$!simpleLead.primpostalcode</td>
									<td style="text-align: right;">Other Postal Code:&nbsp;</td>
									<td>$!simpleLead.otherpostalcode</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Country:&nbsp;</td>
									<td>$!simpleLead.primcountry</td>
									<td style="text-align: right;">Other Country:&nbsp;</td>
									<td>$!simpleLead.othercountry</td>		
								</tr>
								<tr>
									<td style="text-align: right; min-width: 90px; vertical-align: top;">Description:&nbsp;</td>
									<td colspan="3" style="word-wrap: break-word; white-space: normal; word-break: break-all;">$!simpleLead.description</td>
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