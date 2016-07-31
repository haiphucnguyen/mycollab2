<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body style="width:600px">
    <table width="600" cellpadding="0" cellspacing="0" class="wrapContent">
        <#include "mailLogo.ftl">
        <tr>
            <td style="padding: 0px 20px; text-align: left;">
                <div id="contentBody">
                    <p>Hi <b>${userName}</b>,</p>
                    <p>
                    We want to thank you again for trying MyCollab. Your trial is set to expires on
                    <b>${expireDay}</b> and we wanted to remind you that in order to keep using MyCollab for project
                    management, please visit your account and enter a valid form of payment under the Billing Info section. That way, there will be no interruption of service for you or your users. <br><br>
                    
                    To access your account, visit <@lib.hyperLink displayName=link webLink=link/>
                    </p>

                    <p>If you have any questions, we are always happy to help, please contact us through our Help Center
                     at <@lib.hyperLink displayName="http://support.mycollab.com" webLink="http://support.mycollab
                     .com"/> or our <@lib.hyperLink displayName="Contact Form" webLink="https://www.mycollab.com/contact"/></p>
                </div>
                <div id="contentFooter">
                    Best regards, <br>
                    <span style="font-weight: bold;">MyCollab's Customer Support Team </span><br>
                    (+84) 862-924-513 <br>
                    <@lib.hyperLink displayName="support@mycollab.com" webLink="support@mycollab.com"/>
                </div>
            </td>
        </tr>
    </table>
    <#include "mailUnsubscribe.ftl">
</body>
</html>    
