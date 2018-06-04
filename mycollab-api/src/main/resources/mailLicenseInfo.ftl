<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body style="width: 600px">
<table width="600" cellpadding="0" cellspacing="0" class="wrapContent">
    <#include "mailLogo.ftl">
    <tr>
        <td style="padding: 10px 30px">
            <p>
                <h1>Thank you for your payment</h1><br/>
                Dear ${name}, <br/>
                Congratulations! MyCollab license is ready for download and use in your team. You can get the license
                file in this email's attachment.
            </p>
            <p>
                To activate MyCollab, please follow the instruction <a
                    href="http://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/enter-the-license-for-mycollab-ultimate-edition/">Enter the license for MyCollab Ultimate Edition</a>
            </p>
            <p>We hope you enjoy MyCollab! If you have any issue, please contact us at <a href="http://support.mycollab.com">Support forum</a></p>
            <p>Check your online invoice <@lib.hyperLink displayName="Online Invoice" webLink="https://sites.fastspring.com/mycollab/order/invoice/${orderId}"/>. We also enclosed the PDF invoice in this email as
            well.
            </p>
        </td>
    </tr>
</table>
<#include "mailUnsubscribe.ftl">
</body>