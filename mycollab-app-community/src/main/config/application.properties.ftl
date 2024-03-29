#=====================================================
# You can visit link https://docs.mycollab.com/getting-started/configuration/
# to get all configuration fieldBuilder and their meanings
#=====================================================

#=====================================================
#    SITE CONFIGURATION
#=====================================================
app.siteName=${sitename}
app.notifyEmail=${mailNotify}

vaadin.servlet.productionMode=true

server.address=${serveraddress}

# You do not change the rest server information if you are not sure what you are doing
server.apiUrl=https://api.mycollab.com/
server.storageSystem=file
server.siteUrl=http://%s:%d/
server.resourceDownloadUrl=http://%s:%d/file/
server.cdnUrl=http://%s:%d/assets/
server.dataDir=userdir

#=====================================================
#    DATABASE CONFIGURATION
#=====================================================
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=${dbUrl}
spring.datasource.username=${dbUser}
spring.datasource.password=${dbPassword}

#=====================================================
#    MAIL CONFIGURATION
#
# SMTP Mail setting to use in 
#=====================================================
mail.smtphost=${smtpAddress}
mail.port=${smtpPort}
mail.username=${smtpUserName}
mail.password=${smtpPassword}
mail.startTls=${smtpTLSEnable}
mail.ssl=${smtpSSLEnable}

#=====================================================
#    ERROR REPORTING
# This email is used to receive any error causes during 
# MyCollab running. It just collects java stack trace not
# any end user sensitive data. In case you are serious not
# want to send report automatically to our team, you can
# leave this field to empty
#=====================================================
mail.errorTo=error@mycollab.com