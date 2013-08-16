<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New bug created</title>
</head>
<body>
	<table width="650" cellpadding="0" cellspacing="0" border="0" style="margin: 0px auto;">
		<tr>
			<td style="background: url('${defaultUrls.cdn_url}border_large_center_new.png') repeat-y 0 0 transparent; color: #4e4e4e; font: 13px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 10px 30px 0px;">
				<div style="display: block; padding: 8px; background-color: rgb(247, 228, 221);">Please tell me some reasons why you don't accept .</div>
				<table width="588" cellpadding="0" cellspacing="0" border="0" style="margin: 0 auto 25px;">
					<tr>
						<td style="color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; padding: 3px 10px;">
							<table cellpadding="0" cellspacing="5" border="0" style="font-size: 10px; width: 100%;">
								<tr>
									<td style="display: block; padding: 8px;">Email to :</td>
									<td style="display: block; padding: 8px;">$!toUserEmail</td>
								</tr>
								<tr></tr>
								<tr>
									<td style="display: block; padding: 8px;">Message:</td>
									<td><textarea id="message" rows="5" cols="100"></textarea></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div style="align:center;">
					<button type="button" onclick="return sendToEmail();">Send</button>
					<button type="button" onclick="return skip();">Skip</button>
				</div>
			</td>
		</tr>
	</table>
	<input type="hidden" id="url" value="$!redirectURL">
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	var homePageURL = "";
	function sendToEmail(){
		 $.ajax({
		      type: 'POST',
		      url: $(#redirectURL).value(),
		      complete: function(data){
		         window.location.href = homePageURL;
		      }
		    });
	}
	fuction skip(){
		window.close();
	}
</script>				
</html>