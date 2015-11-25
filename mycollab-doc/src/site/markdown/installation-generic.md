## Install MyCollab on Unix/Linux

### System Requirements
Check the system requirements at [here](installation.html#System_Requirements)

### Steps to installation

#### Download and execute the generic java installer
You go to MyCollab SourceForge site [https://sourceforge.net/projects/mycollab/files/?source=navbar]( https://sourceforge.net/projects/mycollab/files/?source=navbar), select the latest version of MyCollab java installer. Its format would be MyCollab-Generic-xxx.jar (xxx is the MyCollab version number). After downloading MyCollab, open the terminal then type

*java -jar MyCollab-Generic-xxx.jar*

MyCollab will display the install wizard to help you select the right folder to install MyCollab binary files

!["Accept the license"](https://farm6.staticflickr.com/5610/14977099473_47c9c352dd_b.jpg)

**Note**: If you want to install MyCollab in the headless mode OS, i.e without the graphic user interface, you can ask MyCollab installer run in the console mode

*java -jar MyCollab-Generic-xxx.jar -console*

#### Create database schema
MyCollab needs a database to run, you must create a MySQL schema by following command:

*CREATE SCHEMA mycollab_live DEFAULT CHARACTER SET utf8mb4 ;*

**Note**: We use character set uft8mb4 instead of utf8, you can review the difference of them at [http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html](http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html)

#### Run MyCollab
Go to the MyCollab installation folder, and navigate to bin folder. You click to the startup.sh script,
MyCollab will start the service

You open the browser then type [http://serveraddress:8080](http://serveraddress:8080) to setup MyCollab

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
