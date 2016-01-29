<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyCollab Invitation</title>
<style>
a {
  color: $styles.link_color;
}
</style>
</head>
<body style="background-color: ${styles.background}; font: ${styles.font}; color: #4e4e4e; padding: 0px 0px;">
  <table width="800" cellpadding="0" cellspacing="0" border="0" style="margin: 20px auto;">
    <tr>
      <td>
        <div style="padding: 0px 25px;">
          <img src="${defaultUrls.cdn_url}icons/logo.png" alt="The power productivity tool for your organization" width="130" height="30" style="margin: 0px; padding: 0px;">
        </div>
      </td>
    </tr>
    <tr>
      <td style="padding: 10px 25px;">
        <div><img src="${defaultUrls.cdn_url}icons/default_user_avatar_16.png" width="16" height="16" style="display: inline-block; vertical-align: top;"/>
              $formatter.formatMemberLink($siteUrl, $newMember) has joined to the project $formatter.formatProjectLink($siteUrl, $newMember) as
              ${newMember.qualifiedRole}.
        </div>
      </td>
    </tr>
    #parse("templates/email/footer_en_US.mt")
  </table>
</body>