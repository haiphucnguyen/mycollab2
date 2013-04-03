<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Updated</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td>
				<img src="${defaultUrls.cdn_url}logo_full.png" alt="esofthead-logo" width="650" height="100" style="margin: 0px; padding: 0px;">
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top.png') no-repeat 0 0 transparent;" height="39">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_blue.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><span style="font: bold 18px Tahoma, Geneva, sans-serif;text-transform: uppercase; color: white;">Task Updated</span>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_white.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 14px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 32px 32px 0px;">
				<p style="font-weight: bold;">Hi there,</p>
				<p style="line-height: 28px;">Just wanna let you know that the task <a href="$hyperLinks.taskUrl" style="color: #4283c4; text-decoration: none;">$task.taskname</a> in project <a href="$hyperLinks.projectUrl" style="color: #4283c4; text-decoration: underline;">$task.projectName</a> has been updated. Here're details about it:</p>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_top.png') no-repeat 0 0 transparent; line-height: 7px;" height="7">&nbsp;</td>
					</tr>
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_center.png') repeat-y 0 0 transparent; color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
								<tr>
									<td style="text-align: right; min-width: 90px; vertical-align: top;">Task Name:&nbsp;</td>
									<td style="font-weight: bold; font-size: 11px; word-wrap: break-word;" colspan="3"><a href="$hyperLinks.taskUrl" style="color: #4283c4; text-decoration: none;">$task.taskname</a></td>
								</tr>
								<tr>
									<td style="text-align: right;">Start Date:&nbsp;</td>
									<td>$!date.format('short_date', $task.Startdate)</td>
									<td style="text-align: right; min-width: 90px;">Actual Start Date:&nbsp;</td>
									<td>$!date.format('short_date', $task.Actualstartdate)</td>		
								</tr>
								<tr>
									<td style="text-align: right;">End Date:&nbsp;</td>
									<td>$!date.format('short_date', $task.Enddate)</td>
									<td style="text-align: right;">Actual End Date:&nbsp;</td>
									<td>$!date.format('short_date', $task.Actualenddate)</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Deadline:&nbsp;</td>
									<td>$!date.format('short_date', $task.Deadline)</td>
									<td style="text-align: right;">Priority:&nbsp;</td>
									<td>$!task.priority</td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Assign:&nbsp;</td>
									<td><a href="${hyperLinks.assignUserUrl}" style="color: #4283c4; text-decoration: none;">$!task.assignUserFullName</a>
									</td>
									<td style="text-align: right; vertical-align: top;"> Task list:&nbsp;</td>
									<td style="vertical-align: top;"><a href="${hyperLinks.taskListUrl}" style="color: #4283c4; text-decoration: none;">$!task.taskListName</a></td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Notes:&nbsp;</td>
									<td style=" vertical-align: top;" colspan="3">$!task.Notes</td>	
								</tr>
								<tr>
                                	<td colspan="4">
                                		<p>Changes (by $historyLog.postedUserFullName):</p>
                                		<table border="1" style="width:100%;">
                                			<tr>
                                				<td>Fields</td>
                                				<td>Old Value</td>
                                				<td>New Value</td>
                                			</tr>
                                			#foreach ($item in $historyLog.changeItems)
                                				#if ($mapper.hasField($item.field))
                                				<tr>
                                					<td valign="top">
                                						$mapper.getFieldLabel($item.field)
                                					</td>
                                					<td valign="top">
                                						$item.oldvalue
                                					</td>
                                					<td valign="top">
                                						$item.newvalue
                                					</td>
                                				</tr>
                                				#end
                                			#end
                                		</table>
                                	</td>
                                </tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_bottom.png') no-repeat 0 0 transparent; line-height: 7px;" height="7">&nbsp;</td>
					</tr>
				</table>
				<div style="text-align: right; font-size: 10px; width: 100%;">
                <span style="display: inline-block; vertical-align: top; margin-top: 10px;">Connect with us:&nbsp;</span>
                <a href="${defaultUrls.facebook_url}"><img src="${defaultUrls.cdn_url}fb_social_icon.png" height="25" width="25"></a>
                <a href="${defaultUrls.google_url}"><img src="${defaultUrls.cdn_url}google_social_icon.png" height="25" width="25"></a>
                <a href="${defaultUrls.linkedin_url}"><img src="${defaultUrls.cdn_url}linkedin_social_icon.png" height="25" width="25"></a>
                <a href="${defaultUrls.twitter_url}"><img src="${defaultUrls.cdn_url}twitter_social_icon.png" height="25" width="25"></a>
                </div>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_bottom.png') no-repeat 0 0 transparent;" height="16">&nbsp;</td>
		</tr>
		<tr>
            <td>
                <p style="margin-top: 5px; color: #4e4e4e; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;">Copyright by eSoftHead<br>&copy; 2013 MyCollab, LLC. All rights reserved.</p>
            </td>
        </tr>
	</table>
</body>
</html>
