<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body>
    <table width="600" cellpadding="0" cellspacing="0" border="0" style="font-size:12px; margin: 20px 0px;">
        <#include "mailLogo.ftl">
        <tr>
            <td style="padding: 10px 30px">
                <p>${actionHeading}</p>
                <p><@lib.hyperLink displayName=projectHyperLink.displayName() webLink=projectHyperLink.webLink()/></p>
                <p><b><@lib.hyperLink displayName=summary webLink=summaryLink/></b></p>
                <#if mapper?has_content>
                <table width="100%" cellpadding="0" cellspacing="0" style="font-size: 12px; margin: 20px 0px; border-collapse: collapse;">
                    <#list mapper.keySet() as key>
                        <#assign fieldFormat=mapper.getFieldLabel(key)>
                        <#if (key?index %2 = 0)>
                            <tr style="border-bottom: 1px solid ${styles.border_color}">
                                <td style="${styles.cell('125px')}; color: ${styles.meta_color}">${context.getMessage(fieldFormat.displayName)}</td>
                            <#if fieldFormat.IsColSpan>
                                <td style="${styles.cell('615px')}" colspan="3">${fieldFormat.formatField(context)}</td>
                            <#elseif key?has_next>
                                <td style="${styles.cell('615px')}" colspan="3">${fieldFormat.formatField(context)}</td>
                            <#else>
                                <td style="${styles.cell('245px')}">${fieldFormat.formatField(context)}</td>
                            </#if>
                        <#else>
                            <td style="${styles.cell('125px')}; color: {$styles.meta_color}">${context.getMessage(fieldFormat.displayName)}</td>
                            <td style="${styles.cell('245px')}">${fieldFormat.formatField(context)}</td>
                            </tr>
                        </#if>
                    </#list>
                </table>
                <#elseif message>
                    <@lib.block content=message!/>
                </#if>
            </td>
        </tr>
        <#include "mailFooter.ftl">
    </table>
</body>
</html>