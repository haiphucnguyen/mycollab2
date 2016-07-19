<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body style="width: 600px">
<table width="600" cellpadding="0" cellspacing="0" class="wrapContent">
  <#include "mailLogo.ftl">
  <tr>
    <td>
      <div style="padding: 25px 25px 10px 25px">
        Dear ${lead},
        <p>Thank you very much for trying out MyCollab.</p>
        <p>My name is John Adams and I am writing to learn about your first experiences with MyCollab.
        We are thrilled to see you using our products and we'd be happy to
        help you make your project management more effective, successful and
        fun! Do you have questions regarding your use of MyCollab? Would you
        like to suggest an improvement or feature? Are you planning on using the paid MyCollab after your
        evaluation?</p>

        <p>Please don't hesitate to contact me directly via phone or email. I look forward to your feedback.</p>
        <p>Kind regards,</p>
        <p>John Adams</p>
        <p>P.S.: Should you like MyCollab already, we'd be more than happy if you could post a quick update on Facebook or Twitter about us. Thanks!</p>
      </div>
    </td>
  </tr>
</table>
<#include "mailUnsubscribe.ftl">
</body>