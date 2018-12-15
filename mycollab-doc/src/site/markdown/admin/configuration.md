## Configuration

MyCollab is a highly configurable system when we try not to use any hardcode value in our code also we do not want to keep lengthly configuration options that make people confuse. You will read up-to-date MyCollab configuration options here (for Community edition). With premium users, you may access to our private places and do more configurations for advanced features.

MyCollab configuration are listed in form of key, value format. All of them are persisted in file **%MYCOLLAB_HOME%/conf/mycollab.properties** with **%MYCOLLAB_HOME%** is folder path value which you install MyCollab.

**Note**: if you customize MyCollab community, we do not deliver **mycollab.properties** but a template file **mycollab.properties.templates**. You should clone a copy and rename to the file **mycollab.properties** and follow below instructions as usual.


>\#====================================================

>\# You can visit link http://community.mycollab.com/admin/configuration.html

>\# to get all configuration parameters and their meanings

>\#====================================================

>\#====================================================

>\#    SITE CONFIGURATION

>\#====================================================

**site.name**:

Name your MyCollab instance site. For instance: if you name 'Your Company', then in MyCollab dialogs we will show site name branding with your name value.

**running.mode**:

Its value must be `standalone`. You can not change this value for community and premium edition.

**server.address**:

Server address that you and your team will access MyCollab via browser. For instance:

* If you installed MyCollab on your PC, and use for testing by only you, you can set its value is localhost. Then you can access MyCollab with address http://localhost/
* If you installed MyCollab in one server in your LAN network and it is used not only you but your team members, they you give server address by the IP (or machine name) in your LAN, and any of you can access MyCollab with address http://<your LAN IP>/ for example.

>\#====================================================

>\#    DATABASE CONFIGURATION

>\#====================================================

**db.driverClassName**: Value must be *com.mysql.cj.jdbc.Driver*

**db.url**: The format string must be *jdbc:mysql://{database server}/{database schema}?useUnicode=true&characterEncoding=utf-8&autoReconnect=true*. Where:

* **database server**: address of MySQL database server. For instance, if you install MySQL at the same machine which install MyCollab, then you can set value is localhost, otherwise you can set database server has value is machine name, or IP address in your LAN network.

* **database schema**: schema name of MyCollab database you has created. See how to create MyCollab schema at [here](../installation.html#Create_database_schema)

**db.username**: Database user name

**db.password**: Database password

>\#=====================================================

>\#    MAIL CONFIGURATION

>\# SMTP Mail setting to use in

>\#=====================================================

**mail.smtphost**: Host of your SMTP server

**mail.port**: Port of your SMTP server

**mail.username**: SMTP username

**mail.password**: SMTP password

**mail.isTLS**: true/false value indicate whether SMTP use TLS or not

>\#=====================================================

>\#    ERROR REPORTING

>\# This email is used to receive any error causes during

>\# MyCollab running. It just collect java stack trace not

>\# any end user sensitive data. In case you are serious not

>\# want to send report automatically to our team, you can

>\# leave this field to empty

>\#=====================================================

**error.sendTo**:support@mycollab.com


>\#=====================================================

>\#    LINK CONFIGURATION

>\# You should not change these values.

>\#=====================================================

**cdn.url**:Value must be http://%s:%d/assets/images/email/

**app.url**:Value must be http://%s:%d/

### Example
Below is the full example of mycollab.properties we use in our development workspace

>\#**SITE CONFIGURATION**

>site.name=localhost

>running.mode=development

>server.address=localhost

>server.port=8080

>\#**DATABASE CONFIGURATION**

>db.driverClassName=com.mysql.cj.jdbc.Driver

>db.url=jdbc:mysql://localhost/mycollab_live?useUnicode=true&characterEncoding=utf-8&autoReconnect=true

>db.username=root

>db.password=***

>mail.smtphost=smtp.gmail.com

>mail.port=587

>mail.username=mail@esofthead.com

>mail.password=****

>mail.isTLS=true

>\#**ERROR REPORTING**

>error.sendTo=support@mycollab.com

>\#**LINKS CONFIGURATION**

>cdn.url=http://%s:%s/assets/images/email/

>app.url=http://%s:%s/
