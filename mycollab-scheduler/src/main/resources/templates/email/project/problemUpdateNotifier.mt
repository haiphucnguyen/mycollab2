<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Problem updated</title>
</head>
<body>
    <table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
       <tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top_new.png') no-repeat 0 0 transparent; font-size: 11px; line-height: 11px;" height="11">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_orange.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">Problem Updated</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
		</tr>
        <tr>
            <td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<div style="font-weight: bold; display: block; border-bottom: 1px solid rgb(212, 212, 212); padding-bottom: 5px; margin-bottom: 10px;">Hi $!userName,</div>
				<div style="display: block; padding: 8px; background-color: rgb(247, 228, 221);">Just wanna let you know that the problem <a href="" style="color: rgb(216, 121, 55);">$problem.issuename</a> in project <a href="$hyperLinks.projectUrl" style="color: rgb(216, 121, 55); text-decoration: underline;">$hyperLinks.projectName</a> has been updated. Here're details about it:</div>
                <table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
                	<tr>
                        <td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
                            <table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
                                <tr>
									<td style="width: 60px; max-width: 90px; vertical-align: top; text-align: right;">Issue name:&nbsp;</td>
									<td style="font-weight: bold; font-size: 11px;" colspan="3"><a href="" style="color: rgb(216, 121, 55); text-decoration: none;">$!problem.issuename</a></td>
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Raised by:&nbsp;</td>
									<td><a href="${hyperLinks.raiseUserUrl}" style="color: rgb(216, 121, 55); text-decoration: none;">$!problem.raisedByUserFullName</a></td>
									
									<td style="text-align: right; vertical-align: top;">Assigned to:&nbsp;</td>
									<td>
										<a href="${hyperLinks.assignUserUrl}" style="color: rgb(216, 121, 55); text-decoration: none;">$!problem.assignedUserFullName</a>
									</td>	
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Due date:&nbsp;</td>
									<td style="vertical-align: top;">$!date.format('short_date', $!problem.datedue)</td>
									
									<td style="text-align: right; vertical-align: top;">Status:&nbsp;</td>
									<td>$!problem.status</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Impact:&nbsp;</td>
									<td>$!problem.impact</td>
									
									<td style="text-align: right; min-width: 90px;">Priority:&nbsp;</td>
									<td>$!problem.priority</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Description:&nbsp;</td>
									<td colspan="3" style="word-wrap: break-word; white-space: normal; word-break: break-all;">$!problem.description</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Resolution:&nbsp;</td>
									<td colspan="3" style="word-wrap: break-word; white-space: normal; word-break: break-all;">$!problem.resolution</td>		
								</tr>
                                <tr>
                                	<td colspan="4">
                                		<p>Changes (by $!historyLog.postedUserFullName):</p>
                                		<table border="1" style="width:100%; border-collapse: collapse; border-color: rgb(169, 169, 169);">
                                			<tr>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169);">Fields</td>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169);">Old Value</td>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169);">New Value</td>
                                			</tr>
                                			#foreach ($item in $historyLog.changeItems)
                                				#if ($mapper.hasField($item.field))
                                				<tr>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);">
                                						$mapper.getFieldLabel($item.field)
                                					</td>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);">
                                						$item.oldvalue
                                					</td>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);">
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