<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body style="width: 600px">
  <table width="600" cellpadding="0" cellspacing="0" class="wrapContent">
    <#include "mailLogo.ftl">
    <tr>
      <td>
        <div style="padding: 0px 0px 10px 25px">
          <h1>Thank you for your payment</h1><br/>
          Dear ${customerName}, <br/>
          Payment for MyCollab workspace charged successfully<br/>
          The next payment date: ${nextPaymentDate}
        </div>
      </td>
    </tr>
  </table>
  <#include "mailUnsubscribe.ftl">
</body>