<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
</head>
<body style="width:600px">
    <table width="600" cellpadding="0" cellspacing="0" class="wrapContent">
        <#include "mailLogo.ftl">
        <tr>
            <td style="padding: 0px 30px; text-align: left;">
                <div id="contentBody">
                    <p>Hi <b>${userName}</b>,</p>

                    <p>Your free trial of MyCollab has just ended. If you wish to continue using MyCollab to manage
                    projects and tasks, you may enter a payment method in your account under Billing Info.</p>
                    
                    <p>To access your account, visit  <@lib.hyperLink displayName="Your account" webLink=link/></p>

                    <p>We will keep your account and data safe for 30 days so that you can export it or upgrade to a paid plan.</p>

                    <p>If you have any questions, we are always happy to help, please contact us through our Help Center
                     at <@lib.hyperLink displayName="http://support.mycollab.com" webLink="http://support.mycollab.com"/>
                     or our <@lib.hyperLink displayName="Contact Form" webLink="https://www.mycollab.com/contact"/></p>
                </div>
                <div id="contentFooter">
                    Best regards, <br>
                    John Adams <br>
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