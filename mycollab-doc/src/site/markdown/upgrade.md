## Upgrade MyCollab
The upgrade process is pretty similar than installation procedure. f you need any help during upgrade process, please send us a request via our form [https://www.mycollab.com/support/] (https://www.mycollab.com/support/), we will try response to you as soon as possible. If there is any missing in our guidelines, we will update this page per your feedback to help our instruction precise and easy for all cases.

### System Requirements
* **Java Runtime Environment 7+**: If you do not have installed java, you can get it here [http://java.com/en/download/index.jsp] (http://java.com/en/download/index.jsp), you should use the latest java version.
* **MySQL database, version 5.5+**: If you do not have installed MySQL instance, you can get it here [http://www.mysql.com/](http://www.mysql.com/), MyCollab is tested against MySQL 5.5, 5.6 and any higher version of MySQL in market.
* **RAM**: you must have at least 2 GB RAM available

### Steps to installation

#### Backup MyCollab database
You should backup database. Although MyCollab development must test the migration process automatically in their daily jobs. However, it is similar than any OS/Commercial products to require you should backup database before doing any upgrade procedure.

#### Unzip binary files
Go to MyCollab SourceForge site [https://sourceforge.net/projects/mycollab/files/?source=navbar](https://sourceforge.net/projects/mycollab/files/?source=navbar) and select the latest version of MyCollab distribution of binary file. Download MyCollab binary to your computer and unzip it under your favorite folder. Here is screenshot we download MyCollab binary and unzip in a Window folder

!["MyCollab Installation Folder"](http://mycollab_assets.s3.amazonaws.com/wiki/installation/mycollab_binary_folder.png "MyCollab Installation Folder")

#### Configure MyCollab settings

You should re-configure MyCollab settings. The easiest approach is copying the old MyCollab configuration file mycollab.properties from ``%MYCOLLAB_HOME%/conf`` to the new MyCollab installation folder ``%MYCOLLAB_NEW_FOLDER%/conf``.

#### Run MyCollab

You start MyCollab script in the new installation folder, then MyCollab should run well while our upgrade procedure will do all migration jobs silently.
