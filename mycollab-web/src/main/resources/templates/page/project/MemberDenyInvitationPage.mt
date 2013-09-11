<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style media="screen" type="text/css">
.container {
}

.footer-home {
    width: 100%;
    height: 100px;
    background: url('${defaultUrls.cdn_url}footer_home_bg.png') repeat-x scroll 0% 0% transparent;
    clear: both;
    position: relative;
    z-index: 10;
}

.footer-home .footer-copyright {
    color: #606060;
}
.footer-copyright {
    color: #FFFFFF;
    font-size: 12px;
}
.footer-home .footer-copyright h1 {
    color: #FFFFFF;
    font-family: 'Monda';
}
.footer-home .wrapper {
    width: 1100px;
    margin: 0px auto;
}
.v-button-bluebtn{
    background: url('${defaultUrls.cdn_url}grad-dark-bottom2.png') repeat-x left bottom
        #2599c8;
    border: 1px solid #093768;
    color: #FFFFFF;
    text-shadow: 1px 1px 0px #1570cd;
    border-radius: 3px;
    padding: 3px 13px;
}
.v-button-bluebtn:hover {
    background: url('${defaultUrls.cdn_url}grad-dark-bottom2.png') repeat-x left bottom
        #1377b3;
    border: 1px solid #093768;
}

</style>
<title>Member deny invitation feedback page</title>
</head>
<body style="height:100%;">
<div style="height: 100%; padding-top:100px; padding-left: 150px;">
    <table height="100%" width="1000" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto; padding-top: 50px;">
        <tr>
            <td style="height: 100%; width: 250px; display: inline-block; vertical-align: top; margin-top: 45px; border-right:1px dotted black;">
                <div>
                    <a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}logo_mycollab.png" height="42" width="225"></a>
                    <p><span style="font-style:italic; font-size:14px;">&copy;2010-2013 MyCollab, LLC. All rights reserved.</span></p>
                </div>
            </td>
            <td style="width: 600px; display: inline-block; vertical-align: top;">
                <div>
                    <div style="display: block; padding: 40px 8px 8px 20px;">
                        <a href="javascript:void(0);"><img src="${defaultUrls.cdn_url}ticket_icon.png" height="25" width="25"></a>
                        Oops! We are sorry because you do not want to join the ${projectName} project. Could you please drop some lines to tell reason to the inviter?
                    </div>
                    <div style="display: block; padding: 8px 8px 8px 20px;">
                        <textarea id="message" rows="8" cols="80">
                        </textarea>
                    </div>
                    <div style="display: block; padding: 8px 8px 8px 20px;">
                        <button class="v-button-bluebtn" type="button" onclick="return sendEmailFeedBack();">Send</button>
                        <button class="v-button-bluebtn" type="button" onclick="return skip();">Skip</button>
                    </div>
                    <div id="requireMsg" style="display: none; padding: 12px 8px 8px 20px;">
                        <p><span style="color:red; font-style:italic">
                            (*) Plz, give a reason to inviter
                        </span></p>
                    </div>
                </div>  
            </td>
        </tr>
    </table>
    <div class="container"></div>
    <input type="hidden" id="inviterEmail" value="$!inviterEmail">
    <input type="hidden" id="url" value="$!redirectURL">
    <input type="hidden" id="toEmail" value="$!toEmail">
    <input type="hidden" id="toName" value="$!toName">
    <input type="hidden" id="inviterName" value="$!inviterName">
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $('#message').val("");
        $('#message').focus();
    });
    function sendEmailFeedBack(){
        $('#requireMsg').hide();
        if($('#message').val().trim() == ""){
            $('#requireMsg').show();
            return;
        }   
        var url = encodeURI($('#url').val().trim());
             $.ajax({
                  type: 'POST',
                  url: url,
                  data : {inviterEmail : $('#inviterEmail').val().trim() ,toEmail : $('#toEmail').val().trim(), message : $('#message').val().trim(),
                    toName : $('#toName').val().trim() , inviterName: $('#inviterName').val().trim()},
                  complete: function(data){
                     alert('Send email successfully');
                     window.location.assign("http://www.mycollab.com/");
                  }
                });
    }
    function skip(){
        window.location.assign("http://www.mycollab.com/");
    }
</script>               
</html>