<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body>
    <table width="600" cellpadding="0" cellspacing="0" border="0" class="wrapContent">
        <#include "mailLogo.ftl">
        <tr>
            <td style="color: #4e4e4e; padding: 10px 30px;">
                <p>${actionHeading}</p>
                <p><@lib.hyperLink displayName=projectHyperLink.displayName() webLink=projectHyperLink.webLink()/></p>
                <p><b><@lib.hyperLink displayName=summary webLink=summaryLink/></b></p>
                <@lib.block content=comment.changecomment!/>
            </td>
        </tr>
        <#if lastComments?has_content>
            <tr>
                <td style="padding: 10px 30px;">
                    <h3 style="font-size:14px">Latest comments (${lastComments?size})</h3>
                </td>
            </tr>
            <#list lastComments as commentItem>
                <@lib.commentBlock avatar=commentItem.ownerAvatarId displayName=commentItem.ownerFullName comment=commentItem.comment/>
            </#list>
        </#if>
        <#include "mailFooter.ftl">
    </table>
</body>
</html>