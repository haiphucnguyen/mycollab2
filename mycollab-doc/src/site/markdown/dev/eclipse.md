## Set up Eclipse workspace
We do very hard to make sure you spend as little time as possible when you plan to set up development environment and extend MyCollab. With proper tools below, you can set up all MyCollab projects in your Eclipse workspace within 5 minutes (exclude time to download necessary library from Maven repository)

**Note**: Eclipse IDE is not a must IDE to extend MyCollab functionalities, you can use your favorite IDE such as NetBeans or IntelliJ, you can use appropriate maven plugin to generate project files associate with your IDE. Below instruction is only for Eclipse which is one we mainly use in developing MyCollab and well test while we wrote this instruction.

### Requirements
* Java - [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html): MyCollab support Java 7+. But we do test with any Java versions presented in market.
* Eclipse - [http://www.eclipse.org/](http://www.eclipse.org/): you should use Eclipse 3.7+
* Maven - [http://maven.apache.org/](http://maven.apache.org/): you should use Maven 3+
* MySQL - [http://dev.mysql.com/downloads/](http://dev.mysql.com/downloads/): MyCollab is well tested with version 5.5+. The higher version of MySQL is recommended.

### Steps of set up
#### Get the source codes
You go to MyCollab Github site [https://github.com/esofthead/mycollab](https://github.com/esofthead/mycollab) and clone this project to your local favorite folder

`git clone https://github.com/esofthead/mycollab.git`

#### Generate eclipse project files

Open your command line and go to folder you have checkout MyCollab sources. The number of projects may be changed over our distribution versions, but generally it looks like this:

!["Project Structure"](https://mycollab_assets.s3.amazonaws.com/wiki/development/project_structure.png "Project Structure")

Open your terminal, go to MyCollab source folder, and go to folder mycollab-deployer-community, and type

`mvn clean install -Dmaven.test.skip=true`

to install all MyCollab libraries to your local maven repository. Then you type

`mvn eclipse:eclipse`

(Make sure *mvn* command is existed in your path)

Maven will generate eclipse project files for all MyCollab community projects. Depends on your internet connection speed, this process is time consuming to get all necessary libraries for MyCollab projects.

!["Generate Eclipse projects"](http://s3.amazonaws.com/mycollab_assets/wiki/development/generate_eclipse_project.png "Generate Eclipse Projects")

#### Import projects to Eclipse workspace

Run your eclipse program, select menu item *File > Import*, then select option *"Existing Projects into Workspace"*, then browse root folder to MyCollab sources folder

!["Import Eclipse Projects"](http://s3.amazonaws.com/mycollab_assets/wiki/development/import_eclipse_projects.png "Import Eclipse Projects")

Eclipse will scan folder and ask you MyCollab projects you want to import, select all then press Ok, now you are ready to preview MyCollab source code with Eclipse IDE

!["Eclipse workspace"](http://s3.amazonaws.com/mycollab_assets/wiki/development/eclipse_workspace.png "Eclipse workspace")

#### Create database schema
MyCollab needs a database to run, you must create a MySQL schema by following command:

`CREATE SCHEMA `mycollab` DEFAULT CHARACTER SET utf8mb4 ;`

*Note*: We use character set uft8mb4 instead of utf8, you can review the difference of them at [http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html](http://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html)

#### Run MyCollab program

Now you are ready to run MyCollab, you open Eclipse project `mycollab-app-community`, and select Java file CommunityServerRunner, it is the main java program

Run CommunityServerRunner as a main java file. Because MyCollab is an enterprise server and it takes a lot of memory consuming, you must extend MyCollab heapsize. Here is the argument we use in our development workspace:

!["Eclipse settings"](http://s3.amazonaws.com/mycollab_assets/wiki/development/eclipse_program_setting.png "Eclipse setting")

You run this main application, MyCollab will need you input at the first time you run it. You open the web browser then type http://<server_address>:8080 to setup MyCollab

!["Setup Wizard"](http://s3.amazonaws.com/mycollab_assets/wiki/installation/mycollab_setup_wizard.png "Setup Wizard")

**Enter site name**: `localhost`

**Enter server address**: `localhost`

**Enter database server address**: `localhost`

**Note**: We assume you install MySQL dev database in your machine. Otherwise, please type the right address of MySQL you can access to
**Enter database name (Database must be created before)**: `mycollab`

**Enter database user name**: `<Your value>`

**Enter database user password**: `<Your value>`

We will request you enter smtp email configuration in order MyCollab can send notifications to you if there is any changes in tasks, bugs, etc but it is too soon for you to know all settings. You should skip this step by selecting 'no' for the first time to run MyCollab. For advanced developers, you can read more MyCollab configuration at [here](../admin/configuration.html)

After MyCollab run successfully, the web browser will automatically lead you to MyCollab site. You use the default account [admin@mycollab.com](mailto:admin@mycollab.com) and password ***admin123*** to access MyCollab. Remember to change your email and password for the next login
