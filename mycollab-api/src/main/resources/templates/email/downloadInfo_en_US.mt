<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download information</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<style>
a {
  color: $styles.link_color;
}

div{
  line-height: 20px;
}
</style>
</head>
<body style="background-color: ${styles.background}; font: ${styles.font}; padding: 0px;">
  #macro( hyperLink $displayName $webLink )
        <a href="$webLink" style="color: ${styles.link_color}; white-space: normal;">$displayName</a>
  #end
  <table width="600" cellpadding="0" cellspacing="0" border="0" style="margin: 20px auto;">
    #parse("templates/email/logo.mt")
    <tr>
      <td>
        <div style="padding: 25px 25px;">
          Dear ${lastname}, <br/>
          Congratulations! MyCollab is ready for download and use in your team. Get it in the link below
          <a style="text-decoration:none;" href="$!downloadLink"/>
            <div style="background-color: rgb(32, 36, 35); text-align: center; padding: 3px 0px; width: 330px; margin: 0px auto;">
              <div style="width: 100%; padding: 10px 0px; border-color: rgb(99, 102, 101);border-width: 1px 0px; border-style: solid; margin: 0px auto;">
                <span style="width: 100%; font-size: 22px; color: rgb(255, 255, 255);text-align: center">Download</span>
              </div>
            </div>
          </a>
        </div>
        <div style="padding: 0px 25px;">
        As you get started with MyCollab, we recommend that you:
          <ul>
            <li style="padding: 5px 0px">Install MyCollab on your server <a href="https://community.mycollab.com/installing-mycollab/">https://community.mycollab.com/installing-mycollab/</a>
            <li style="padding: 5px 0px">If you are using Windows, you can install MyCollab as a Window service by following steps at <a href="https://community.mycollab.com/administration/startup-via-windows-service/">https://community.mycollab.com/administration/startup-via-windows-service/</a></li>
            <li style="padding: 5px 0px">Common questions that MyCollab users asked us, such as how to change the server port, are described at our FAQ page <a href="https://community.mycollab.com/faq/">https://community.mycollab.com/faq/</a></li>
            <li style="padding: 5px 0px">If you still have any question, suggestion etc to make MyCollab better, you are welcome to post a message into our support page <a href="http://support.mycollab.com">http://support.mycollab.com</a></li>
          </ul>
        </div>
        <div style="padding: 0px 25px;">
        We hope you enjoy MyCollab!
        </div>
      </td>
    </tr>
    #parse("templates/email/footer_en_US.mt")
  </table>
</body>