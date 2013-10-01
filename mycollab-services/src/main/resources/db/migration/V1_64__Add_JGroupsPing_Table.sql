 CREATE TABLE JGROUPSPING ( 
        own_addr varchar(200) NOT NULL, 
        cluster_name varchar(200) NOT NULL,
        ping_data varbinary(5000) DEFAULT NULL,
        PRIMARY KEY (own_addr, cluster_name) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;