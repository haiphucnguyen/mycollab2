## How to customize MyCollab

We do very hard to make MyCollab easy to develop and maintain. However, because MyCollab aim to be an enterprise application with a lot of features, many of advanced programming techniques and we also spend uncountable hours to do our best to MyCollab code base so it is not easy for you to understand the whole architecture in the moment. This page and its sub pages will help you capture basic knowledge of MyCollab architecture, basic core components then you can customize community edition meet to your needs. But the best approach to optimize your development cost and time is hiring MyCollab development team, we are author of MyCollab and we know all tricks, some of them are hard to be explained in community pages

### Core libraries in MyCollab
* [Spring framework](http://spring.io/)

We mainly use spring for manage dependency injection, database transaction and project configurations some how. We combine spring configuration in both xml format and annotation. We heavily use spring in our service code, so if you really want to customize MyCollab, you must have Spring knowledge to know how we configure our system by Spring.

* [MyBatis](http://mybatis.github.io/mybatis-3/)

We use MyBatis to play as persistence layer separate our service classes and database layer. MyBatis is used heavily in MyCollab with custom query, we also add several extensions to MyBatis make it more useful for our business, MyBatis is a must knowledge if you want to customize our query in persistence layer.

* [Vaadin](https://vaadin.com)

MyCollab UI is developed by using Vaadin. We enhance power of Vaadin by using our own MVP framework. You should learn Vaadin if you plan to customize MyCollab UI.

* [Apache Jackrabbit](http://jackrabbit.apache.org/)

MyCollab uses Jackrabbit as an abstract layer to keep content meta data. We say 'content' means it may be a file, a document or some kind of information in MyCollab. The real storage to keep content may be on database, file system or Amazon S3. If you want to change our implement of content system, you must learn how to use Jackrabbit API before diving to our code and make customization.

* [Infinispan](http://infinispan.org/)

MyCollab uses Infinispan as the cache layer to keep in memory all frequent data in used. We tune the 2nd level cache layer fit to MyCollab to ensure it captures the right data in every account. Infinispan is also used to cluster MyCollab in many nodes - This feature is only existed in the Commercial Distribution.

* [Apache Solr](http://lucene.apache.org/solr/)

Solr is used as the search engine in MyCollab, it indexes all MyCollab documents - the generic term of all MyCollab entities includes assets, or project item, account item etc. Solr helps MyCollab give the flexible search with multiple criteria to its user. Several advanced features are only existed in the Commercial Distribution.

* [Apache Camel](http://camel.apache.org/)

MyCollab targets to be a modular system, with services are loose coupling with other services, and lost one service may not cause any corruption in other services. For instance, we have CRM and Project Management modules. In fact, these two modules can play together but also we can deliver only CRM module to our customer will not cause problem because the lack of Project Management module. The magic behind of our solution is Apache Camel.

Apache Camel is used as the backbone framework to combine services together, solve their dependencies if any and help they communicate together. You should have knowledge of Enterprise Integration Patterns and Apache Camel in case you want to maintain our extension system

* [JUnit](https://github.com/junit-team/junit)

Testing is one of the most important tasks in our daily development time. All crucial features must be passed our automation test scripts. We also enhance unit test base on our needs og writing efficient test cases. The community export our part of testing framework, and several test cases only (more than 100) and you must learn to write functional test before starting to write test cases for MyCollab.

### Main MyCollab projects

We love to write good code. We develop MyCollab in modular approach follow Object Oriented principle. We seriously follow package principle [http://en.wikipedia.org/wiki/Package_principles](http://en.wikipedia.org/wiki/Package_principles) and we may move our source codes from one project to another project. But there are projects they never change their names and responsibilities, you should know them before getting any extension of MyCollab.

#### MyCollab Core
Project contains core classes used across MyCollab module, classes are played as base classes and not specific for any business. There are some responsibilities of classes in core package:
* Caching
* Utility classes
* Utility classes for Spring, Camel or so, which use in Spring loader or control life cycle of integration patterns etc
* Validator
* Template context (base on Velocity engine)

#### MyCollab Data Access Layer
Project contains generic class of DAO and Service parent classes, also related classes of handling generic query with MyBatis.

#### MyCollab Scheduler
Quartz is used as our main scheduler to run all types of scheduling. We mostly use Cron task supported by Quartz, and integrate with Spring Quartz component.

#### MyCollab Services
Contains all services class of MyCollab include CRM, Project, Document Management and more. In case you need to extend MyCollab service business, this module is one you must stay on.

#### MyCollab Web
Contains all MyCollab UI classes display views. MyCollab UI is developed base on Vaadin 7 framework and our custom MVP pattern. You may see how we develop MyCollab UI in subsequent chapters.
