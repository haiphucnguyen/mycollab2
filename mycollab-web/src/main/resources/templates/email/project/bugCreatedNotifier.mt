<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New bug created</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		#include ("notifierHeader.mt")
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top.png') no-repeat 0 0 transparent;" height="39">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_blue.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><span style="font: bold 18px Tahoma, Geneva, sans-serif;text-transform: uppercase; color: white;">New bug created</span>
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_white.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 14px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 32px 32px 0px;">
				<p style="font-weight: bold;">Hi there,</p>
				<p>Just wanna let you know that a new bug has been created i project <a href="$hyperLinks.projectUrl" style="color: #4283c4; text-decoration: underline;">$bug.projectname</a>. Here're details about it:</p>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_top.png') no-repeat 0 0 transparent; line-height: 7px;" height="7">&nbsp;</td>
					</tr>
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_center.png') repeat-y 0 0 transparent; color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px;">
								<tr>
									<td style="text-align: right; min-width: 90px; vertical-align: top;">Bug Summary:&nbsp;</td>
									<td style="font-weight: bold; text-transform: uppercase;" colspan="3"><a href="$hyperLinks.taskUrl" style="color: #4283c4; text-decoration: none;">$bug.summary</a></td>
								</tr>
								<tr>
									<td style="text-align: right; min-width: 90px; vertical-align: top;">Description:&nbsp;</td>
									<td colspan="3">$!bug.description</td>
								</tr>
								<tr>
									<td style="text-align: right;">Status:&nbsp;</td>
									<td>$!bug.status</td>
									<td style="text-align: right; min-width: 90px;">Priority:&nbsp;</td>
									<td>$!bug.priority</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Severity:&nbsp;</td>
									<td>$!bug.severity</td>
									<td style="text-align: right;">Resolution:&nbsp;</td>
									<td>$!bug.resolution</td>		
								</tr>
								<tr>
									<td style="text-align: right;">Due date:&nbsp;</td>
									<td>$!date.format('short_date', $bug.Duedate)</td>
									<td style="text-align: right;">Milestone:&nbsp;</td>
									<td><a href="$hyperLinks.milestoneUrl" style="color: #4283c4; text-decoration: none;">$!bug.milestoneName</td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Logged by:&nbsp;</td>
									<td><a href="${hyperLinks.loggedUserUrl}" style="color: #4283c4; text-decoration: none;">$bug.logby</a></td>
									<td style="text-align: right; vertical-align: top;">Assigned to:&nbsp;</td>
									<td><a href="${hyperLinks.assignUserUrl}" style="color: #4283c4; text-decoration: none;">$!bug.assignuser</a></td>		
								</tr>
								<tr>
									<td style="text-align: right; vertical-align: top;">Components:&nbsp;</td>
									<td style=" vertical-align: top;">
										#if( $bug.components.size() > 1 )
											<ul>
											#foreach( $component in $bug.components )
												<li>$component.Componentname</li>
											#end
											</ul>
										#elseif( $bug.components.size() > 0 )
											$bug.components.get(0).Componentname
										#else
											N/A
										#end
									</td>
									<td style="text-align: right; vertical-align: top;">Affected Versions:&nbsp;</td>
									<td style=" vertical-align: top;">
										#if( $bug.affectedVersions.size() > 1 )
											<ul>
											#foreach( $affectedVersion in $bug.affectedVersions )
												<li>$component.Componentname</li>
											#end
											</ul>
										#elseif( $bug.affectedVersions.size() > 0 )
											$bug.affectedVersions.get(0).Versionname
										#else
											N/A
										#end
									</td>	
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="background: url('${defaultUrls.cdn_url}border_small_bottom.png') no-repeat 0 0 transparent; line-height: 7px;" height="7">&nbsp;</td>
					</tr>
				</table>
				#include ("notifierSocialLinks.mt")
			</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_bottom.png') no-repeat 0 0 transparent;" height="16">&nbsp;</td>
		</tr>
		#include ("notifierFooter.mt")
	</table>
</body>
</html>
