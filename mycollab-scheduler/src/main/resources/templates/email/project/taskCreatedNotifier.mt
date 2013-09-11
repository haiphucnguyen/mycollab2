<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task updated</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top_new.png') no-repeat 0 0 transparent; font-size: 11px; line-height: 11px;" height="11">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_orange.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">New Task Created</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<div style="font-weight: bold; display: block; border-bottom: 1px solid rgb(212, 212, 212); padding-bottom: 5px; margin-bottom: 10px;">Hi $!userName,</div>
				<div style="display: block; padding: 8px; background-color: rgb(247, 228, 221);">Just wanna let you know that a new task <a href="$hyperLinks.taskUrl" style="color: rgb(216, 121, 55); text-decoration: underline;">$!task.taskname</a> has been created in project <a href="$!hyperLinks.projectUrl" style="color: rgb(216, 121, 55); text-decoration: underline;">$!task.projectName</a>. Here're details about it:</div>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
								<tr>
									<td style="width: 60px; max-width: 90px; vertical-align: top; text-align: right;">Task Name:&nbsp;</td>
									<td style="font-weight: bold; font-size: 11px; word-wrap: break-word;" colspan="3"><a href="$!hyperLinks.taskUrl" style="color: rgb(216, 121, 55); text-decoration: none;">$!task.taskname</a></td>
								</tr>
								<tr>
									<td style="text-align: right;">Start Date:&nbsp;</td>
									<td>$!date.format('short_date', $!task.Startdate)</td>
									<td style="text-align: right; min-width: 90px;">Actual Start Date:&nbsp;</td>
									<td>$!date.format('short_date', $!task.Actualstartdate)</td>		
								</tr>
								<tr>
									<td style="text-align: right;">End Date:&nbsp;</td>
									<td>$!date.format('short_date', $!task.Enddate)</td>
									<td style="text-align: right;">Actual End Date:&nbsp;</td>
									<td>$!date.format('short_date', $!task.Actualenddate)</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Deadline:&nbsp;</td>
									<td>$!date.format('short_date', $!task.Deadline)</td>
									<td style="text-align: right;">Priority:&nbsp;</td>
									<td>$!task.priority</td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Assign:&nbsp;</td>
									<td style="vertical-align: top;"><a href="${!hyperLinks.assignUserUrl}" style="color: rgb(216, 121, 55); text-decoration: none;">$!task.assignUserFullName</a>
									</td>
									<td style="text-align: right; vertical-align: top;"> Task list:&nbsp;</td>
									<td style="vertical-align: top;"><a href="${!hyperLinks.taskListUrl}" style="color: rgb(216, 121, 55); text-decoration: none;">$!task.taskListName</a></td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Notes:&nbsp;</td>
									<td style=" vertical-align: top;" colspan="3">$!task.Notes</td>	
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
