<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New item is created</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<style>
a {
  color: $styles.link_color;
}
</style>
</head>
<body style="background-color: ${styles.background}; font: ${styles.font}; color: #4e4e4e; padding: 0px 0px;">
    #macro( hyperLink $displayName $webLink )
        <a href="$webLink">$displayName</a>
    #end
    
    #macro( messageBlock $messageContent )
        <div style="padding: 20px 15px; background-color: rgb(237, 248, 255); position: relative; color: rgb(71, 87, 116); text-align: left; word-wrap: break-word; white-space: normal; word-break: break-all;">
            <div style="color: rgb(167, 221, 249); font-size: 35px; line-height: 10px; text-align: left;">&ldquo;</div>
            <div style="padding:0px 20px; font-size: 12px; line-height: 1.6em;">$messageContent</div>
            <div style="color: rgb(167, 221, 249); font-size: 35px; line-height: 10px; text-align: right;">&bdquo;</div>
        </div>
    #end
    
    <table width="600" cellpadding="0" cellspacing="0" border="0" style="font-size:12px; margin: 20px auto;">
        #parse("templates/email/logo.mt")
        <tr>
            <td style="padding: 10px 30px">
                <p>$actionHeading</p>
                <p>
                #foreach( $title in $titles )
                    #hyperLink( $title.displayName $title.webLink )
                #end
                </p>
                <p><b>#hyperLink( $summary $summaryLink )</b></p>
                #if( $mapper )
                <table width="100%" cellpadding="0" cellspacing="0" style="font-size: 12px; margin: 20px 0px; border-collapse: collapse;">
                    #set($currentRowElements = 0)
                    #foreach( $key in $mapper.keySet() )
                        #set($fieldFormat=$mapper.getFieldLabel($key))
                        #if ($currentRowElements == 0)
                            <tr style="border-bottom: 1px solid $styles.border_color">
                                <td style="$styles.cell('125px'); color: $styles.meta_color">$context.getMessage($fieldFormat.displayName)</td>
                            #if ($fieldFormat.IsColSpan)
                                <td style="$styles.cell('615px')" colspan="3">$fieldFormat.formatField($context)</td>
                            #elseif (!$foreach.hasNext)
                                <td style="$styles.cell('615px')" colspan="3">$fieldFormat.formatField($context)</td>
                            #else
                                <td style="$styles.cell('245px')">$fieldFormat.formatField($context)</td>
                                #set($currentRowElements = $currentRowElements + 1)
                            #end
                        #else
                            <td style="$styles.cell('125px'); color: $styles.meta_color">$context.getMessage($fieldFormat.displayName)</td>
                            <td style="$styles.cell('245px')">$fieldFormat.formatField($context)</td>
                            </tr>
                            #set($currentRowElements = 0)
                        #end
                    #end
                </table>
                #elseif( $message )
                    #messageBlock( $message )
                #end
            </td>
        </tr>
        #parse("templates/email/footer_en_US.mt")
    </table>
</body>
</html>