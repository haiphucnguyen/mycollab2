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
          Congratulations! MyCollab is ready for download and use in your team. Get it in the link below
          <div style="padding-top: 10px">
            <a style="text-decoration:none;" href="${downloadLink}"/>
              <div style="background-color: #1a8955; text-align: center; padding: 3px 0px; width: 330px; margin: 0px auto;">
                <div style="width: 100%; padding: 10px 0px; border-color: #fff;border-width: 1px 0px; border-style: solid; margin: 0px auto;">
                  <span style="width: 100%; font-size: 22px; color: #fff;text-align: center">Download</span>
                </div>
              </div>
            </a>
          </div>
        </div>
        <div style="padding: 0px 25px;">
          If the above link doesn't work, get the MyCollab at <a href="${downloadLink}"/>here</a>
        </div>
        <div style="padding: 0px 25px;">
        As you get started with MyCollab, we recommend that you:
          <ul>
            <li style="padding: 5px 0px">Install MyCollab on your server <a href="http://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/installing-mycollab/">Installation</a>
            <li style="padding: 5px 0px">If you are using Windows, you can install MyCollab as a Window service by following steps
            at
            <a href="http://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/startup-as-windows-service/">Startup as Windows Service</a></li>
            <li>If you are using Unix, you can configure MyCollab as a Unit startup script by following steps at <a
                    href="http://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/startup-as-unix-service/">Startup as Unix Service</a></li>
            <li style="padding: 5px 0px">Common questions that MyCollab users asked us, such as how to change the server port, are
              described at our FAQ page <a href="http://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/frequently-questions-and-answers/">FAQ</a></li>
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