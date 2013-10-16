<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="https://www.mycollab.com/favicon.ico" type="image/x-icon">
<style media="screen" type="text/css">
.container {
}

#header {
    background-color: #1777AD;
    border-bottom: 6px solid #77BAE0;
    height: 70px;
    width: 100%;
    text-align: left;
    vertical-align: middle;
}
.header-mid {
    display: block;
    margin: 0 auto;
    width: 1100px;
}
.header-mid .a {
    color: #000000;
    text-decoration: none;
}

.body-style {
    background-color: #F9F9F9;
    float: right;
    margin-bottom: 20px;
    padding-left: 30px;
    width: 840px;
}
#mainBody{
    -moz-box-sizing: border-box;
    background-color: #FFFFFF;
    border: 1px solid #A9A9A9;
    border-radius: 3px 3px 3px 3px;
    font-size: 12px;
    height: 350px;
    padding: 8px 11px 11px;
    text-align: left;
    width: 800px;
}

#mainContent{
     background-color: #F6F6F6;
   -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    display: block; 
    padding: 10px 10px 8px 10px;
    border: 1px solid rgb(169, 169, 169);
    height : 200px;
}

#bottom{
    padding-right: 100px; 
    color : blue;
    font-style: bold;
    background-color: rgb(239, 239, 239);
    padding-bottom: 100px;
    text-align: center;
    height : 100%;
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
	cursor: pointer;
}

</style>
<title>Member deny invitation feedback page</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<div class="header-mid">
				<a href=".">
					<img src="${defaultUrls.cdn_url}logo_mycollab.png" alt="Logo Mycollab">
				</a>
			</div>
		</div>
		<div id="body" style="background-color: rgb(239, 239, 239); width: 100%;">
			<div id="spacing" style="height:30px; background-color: rgb(239, 239, 239);"></div>
			<div style="padding-left: 250px; background-color: rgb(239, 239, 239); height: 100%;">
				<div id="mainBody">
					<div id="title">
						<p><span style="color: #196893; font: 22px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;">Please feed back to inviter</span></p>
					</div>
					<hr>
					<div>
						<p><span style="vertical-align:top; padding-left: 10px; padding-top:12px;font-size: 18px; color: #616161;">Oops! We are sorry because you do not want to join Mycollab. Could you please drop some lines to tell reason to the inviter?
						</span></p>
						<div style="display: block; padding: 8px 8px 8px 8px;">
                        <textarea id="message" rows="8" cols="92">
                        </textarea>
                    	</div>
	                    <div style="display: block; padding: 8px 8px 8px 8px;">
	                        <button class="v-button-bluebtn" type="button" onclick="return sendEmailFeedBack();">Send</button>
	                        <button class="v-button-bluebtn" type="button" onclick="return skip();">Skip</button>
	                    </div>
	                    <div id="requireMsg" style="display: none; padding: 12px 8px 8px 20px;">
	                        <p><span style="color:red; font-style:italic">
	                            (*) Reason
	                        </span></p>
	                    </div>
					</div>
				</div>
			</div>
			<div id="bottom">
			    <p>
			    	<a javascrip="void(0);" href="https://www.mycollab.com/terms" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Terms of Service</span></a> &nbsp&nbsp&nbsp
					<span style="color: #000000">|</span>
					&nbsp&nbsp&nbsp<a javascrip="void(0);" href="https://www.mycollab.com/privacy" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Privacy Policy</span></a>&nbsp&nbsp&nbsp
					<span style="color: #000000">|</span>
					&nbsp&nbsp&nbsp<a javascrip="void(0);" href="https://www.mycollab.com" style="text-decoration : none;"><span style="font-size:12px; color: #1777AD;">Copyright 2013 MyCollab. All rights reserved.</span></a>
				</p>
			</div>
		</div>
	</div>
	<input type="hidden" id="inviterEmail" value="$!inviterEmail">
    <input type="hidden" id="url" value="$!redirectURL">
    <input type="hidden" id="toEmail" value="$!toEmail">
    <input type="hidden" id="toName" value="$!toName">
    <input type="hidden" id="inviterName" value="$!inviterName">
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