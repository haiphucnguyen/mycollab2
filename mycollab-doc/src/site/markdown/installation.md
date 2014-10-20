## Installation Guideline

We do very hard to make the installation ease for you. If you need any help during installation process, please send us a request via our form [https://www.mycollab.com/contact] (https://www.mycollab.com/contact), we will try response to you as soon as possible. If there is any missing in our guidelines, we will update this page per your feedback to help our instruction precise and easy for all cases.

### System Requirements
* **Java Runtime Environment 7+**: If you do not have installed java, you can get it here [http://java.com/en/download/index.jsp] (http://java.com/en/download/index.jsp), you should use the latest java version.
* **MySQL database, version 5.5+**: If you do not have installed MySQL instance, you can get it here [http://www.mysql.com/](http://www.mysql.com/), MyCollab is tested against MySQL 5.5, 5.6 and any higher version of MySQL in market.
* **RAM**: you must have at least 2 GB RAM available

### Steps to installation

#### Download the installer file for your operating system
You go to MyCollab SourceForge site [https://sourceforge.net/projects/mycollab/files/?source=navbar]( https://sourceforge.net/projects/mycollab/files/?source=navbar) and select the latest version of MyCollab distribution of binary file. To help you easy to install MyCollab, we have bundled MyCollab into many different formats associates to every popular platform: Windows, MacOS, Linux

!["MyCollab Binary folder"](http://mycollab_assets.s3.amazonaws.com/wiki/installation/mycollab_binary_folder.png "MyCollab Binary Folder")

#### Create database schema
MyCollab needs a database to run, you must create a MySQL schema by following command:

*CREATE SCHEMA "mycollab_aa" DEFAULT CHARACTER SET utf8mb4 ;*

**Note**: We use character set uft8mb4 instead of utf8, you can review the difference of them at [http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html](http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html)

#### Run MyCollab
Open your terminal, go to MyCollab folder and click **startup.bat** (On Windows) or **startup.sh** (on Unix, MacOS), MyCollab will initate service, and you open the browser then type [http://serveraddress:8080](http://serveraddress:8080) to setup MyCollab

MyCollab will show the installation page ask you several inputs to help you install MyCollab easily

!["Setup Wizard"](http://s3.amazonaws.com/mycollab_assets/wiki/installation/mycollab_setup_wizard.png "Setup Wizard")

**Enter site name**:```localhost```

**Enter server address**: `localhost`

**Enter database server address**: `localhost`

**Note**: We assume you install MySQL dev database is installed in the same of MyCollab. Otherwise, please type the right address of MySQL you can access to.

**Enter database name (Database must be created before)**:`mycollab`

**Enter database user name**: ``<Your value>``

**Enter database user password**:``<Your value>``

Then we will ask you give us a SMTP configuration in order MyCollab can send system notifications to you and your team.

**Note**: you can skip SMTP setting if you do not need receive notification from your MyCollab installation, but we encourage you must set up SMTP to utilize power of MyCollab in sending email notifications to notify you and your team whether an assignment is created and assigned or so on.

**Outgoing server address**: ``<Your smtp server>`` (Example: *smtp.gmail.com*)

**Mail server port**: ``<Your smtp server port>`` (Example: *465*)

**Mail user name**: ``<Your mail user name>`` (Example:  *[mycollab@gmail.com](mycollab@gmail.com)*)

**Mail password**: ``<Your mail password>`` (Example: *Your gmail password*)

**Enable TLS (y/n)**: ``<Enable TLS or not>`` (Example: *yes for Gmail service provider*)

**Note**: By default, MyCollab will run on the port `8080`. You can change this port value to any value you want by edit and change variable MYCOLLAB_PORT in script **mycollab.bat** (on Windows) or **mycollab.sh** (on Linux, Unix or MacOS) and restart server.

After MyCollab run successfully, you can open any qualified browser (IE, Firefox, Chrome, Safari) then type address [http://localhost:8080](http://localhost:8080), it will lead you to MyCollab site. The default account on initial setup is `admin@mycollab.com/admin123`
