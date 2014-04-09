<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New bug created</title>
</head>
<body>
	#macro( hyperLink $displayName $webLink )
		<a href="$webLink" style="color: rgb(216, 121, 55);">$displayName</a>
	#end
	
    <table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
       <tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_top_new.png') no-repeat 0 0 transparent; font-size: 11px; line-height: 11px;" height="11">&nbsp;</td>
		</tr>
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_orange.png') repeat-y 0 0 transparent; text-align: center; padding-bottom: 10px;"><div style="width: 440px; display: inline-block; vertical-align: middle; text-align: left;"><span style="font: bold 18px Tahoma, Geneva, sans-serif; color: white;">Bug Updated</span></div><div style="width: 150px; display: inline-block; vertical-align: middle;"><img src="${defaultUrls.cdn_url}logo_new.png" alt="esofthead-logo" width="150" height="45" style="margin: 0px; padding: 0px;"></div>
			</td>
		</tr>
        <tr>
            <td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<p>$historyLog.postedUserFullName <b>updated</b> $itemType on:</p>
				<p>
				#foreach( $title in $titles )
					#hyperLink( $title.displayName $title.webLink )
				#end
				</p>
				<p>
				#hyperLink( $summary $summaryLink )
				</p>
                <table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
                	<tr>
                        <td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
                            <table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
                                <tr>
                                	<td colspan="4">
                                		<p>Changes:</p>
                                		<table border="1" style="width:100%; border-collapse: collapse; border-color: rgb(169, 169, 169);">
                                			<tr>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169); width:200px;">Fields</td>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169); width:220px;">Old Value</td>
                                				<td style="font-weight: bold; border-color: rgb(169, 169, 169); width:230px;">New Value</td>
                                			</tr>
                                			#foreach ($item in $historyLog.changeItems)
                                				#if ($mapper.hasField($item.field))
                                				<tr>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);font-size:10px; width:200px;">
                                						$mapper.getFieldLabel($item.field)
                                					</td>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);font-size:10px;width: 220px ;word-wrap: break-word; white-space: normal; word-break: break-all;">
                                						$item.oldvalue
                                					</td>
                                					<td valign="top" style="border-color: rgb(169, 169, 169);font-size:10px;width: 230px ;word-wrap: break-word; white-space: normal; word-break: break-all;">
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
        #parse("templates/email/footer.mt")
    </table>
</body>
</html>