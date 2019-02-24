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
          Dear ${lastname}, <br/>
          Thank you for trialing MyCollab!
        <div style="padding: 0px 25px;">
        As you get started with MyCollab, we recommend that you:
          <ul>
            <li style="padding: 5px 0px">Install MyCollab on your server <a href="https://docs.mycollab.com/getting-started/installation/">Installation</a>
            <li style="padding: 5px 0px">If you are using Windows, you can install MyCollab as a Window service by following steps
            at
            <a href="https://docs.mycollab.com/getting-started/startup-as-windows-service/">Startup as Windows Service</a></li>
            <li>If you are using Unix, you can configure MyCollab as a Unit startup script by following steps at <a
                    href="https://docs.mycollab.com/getting-started/startup-as-unix-service/">Startup as Unix Service</a></li>
            <li style="padding: 5px 0px">Common questions that MyCollab users asked us, such as how to change the server port, are
              described at our FAQ page <a href="https://docs.mycollab.com/frequently-questions-and-answers/">FAQ</a></li>
            <li style="padding: 5px 0px">If you still have any question, suggestion etc to make MyCollab better, you are welcome to post a message into our support page <a href="http://support.mycollab.com">http://support.mycollab.com</a></li>
          </ul>
        </div>
        <div style="padding: 0px 25px;">
        We hope you enjoy MyCollab!
        </div>
      </td>
    </tr>
  </table>
  <#include "mailUnsubscribe.ftl">
</body>