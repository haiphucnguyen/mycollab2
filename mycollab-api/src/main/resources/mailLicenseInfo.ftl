<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body>
<table width="600" cellpadding="0" cellspacing="0" border="0" style="margin: 20px 0px;">
    <#include "mailLogo.ftl">
    <tr>
        <td>
            <div style="padding: 25px 25px 10px 25px">
                Dear ${name}, <br/>
                Congratulations! MyCollab license is ready for download and use in your team. You can get the license
                file in this email's attachment.
            </div>
            <div style="padding: 0px 25px;">
                To activate MyCollab, please follow the instruction <a
                    href="https://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/enter-the-license-for-mycollab-ultimate-edition/">Enter the license for MyCollab Ultimate Edition</a>

            </div>
            <div style="padding: 0px 25px;">
                We hope you enjoy MyCollab! If you have any issue, please contact us at <a
                    href="http://support.mycollab.com">Support forum</a>
            </div>
        </td>
    </tr>
    <#include "mailFooter.ftl">
</table>
</body>