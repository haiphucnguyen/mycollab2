<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroPage.ftl" as lib>
<@lib.head title="Unsubscribe email"/>
<body>
    <#include "pageLogo.ftl">
    <div id="spacing"></div>
    <div id="mainBody">
        <div id="title">
            <h1>MyCollab email preferences</h1>
        </div>
        <hr size="1">
        <form>
            <table border="0" style="width: 100%" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td style="width: 50%">Unsubscribed by accident? Just close this page</td>
                        <td><label for="email">Email*:</label></td>
                        <td><input id="email" maxlength="400" name="email" value=${email} type="text"/></td>
                    </tr>
                </tbody>
            </table>
            <div style="padding-top: 15px; text-align: right;">
                <button class="v-button v-button-orangebtn" type="button" onclick="return unsubscribe();">Unsubscribe</button>
            </div>
        </form>
        <#include "pageFooter.ftl">
    </div>
    <input type="hidden" id="unSubscribeUrl" value="${unSubscribeUrl}">
</body>
<script src="${defaultUrls.cdn_url}js/jquery-2.1.4.min.js"></script>
<script>
    function unsubscribe(){
        if ($('#email').val() == ""){
            alert("Email is required");
            return;
        }

        var url = encodeURI($('#unSubscribeUrl').val());
        $.ajax({
            type: 'POST',
            url: url,
            data : {
                email : $('#email').val().trim()
                },
                success: function(data) {
                    if (data!=null) {
                        if (data.length > 0) {
                            alert(data);
                        } else {
                            alert("Unsubscribe email successfully. It is safe to close this page");
                        }
                    }
                }
        });
    }
</script>
</html>