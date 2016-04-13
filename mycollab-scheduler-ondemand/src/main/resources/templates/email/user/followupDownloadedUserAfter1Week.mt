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
<table width="600" cellpadding="0" cellspacing="0" border="0" style="margin: 20px auto;">
  #parse("templates/email/logo.mt")
  <tr>
    <td>
      <div style="padding: 25px 25px 10px 25px">
        Dear ${lead},
        <p>Thank you very much for trying out MyCollab.</p>
        <p>My name is Hai Nguyen and I am writing to learn about your first experiences with MyCollab.
        We are thrilled to see you using our products and we'd be happy to
        help you make your project management more effective, successful and
        fun! Do you have questions regarding your use of MyCollab? Would you
        like to suggest an improvement or feature? Are you planning on using the paid MyCollab after your
        evaluation of the free edition?</p>

        <p>Please don't hesitate to contact me directly via phone or email. I look forward to your feedback.</p>
        <p>Kind regards,</p>
        <p>Hai Nguyen</p>
        <p>P.S.: Should you like MyCollab already, we'd be more than happy if you could post a quick update on Facebook or Twitter about us. Thanks!</p>
      </div>
    </td>
  </tr>
  #parse("templates/email/footer_en_US.mt")
</table>
</body>