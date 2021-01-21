Project of Social Network for workers
=====================================

Portal will be used by:

*   People and group of people: builders, repairing workers
*   People needed building, repairing and similar type of work

Also there'll be a possibility to order, estimate workers and find job

Usage 
-----
See project ```WIKI```

Technical configs
-----
Read `run/IDE/README.txt` and apply its recommendations to IDE 

<!-- TODO: should be written well (write wiki)-->
Temp notes:
- Start mongodb locally: 
`$> sudo mongod --config /etc/mongodb.conf`
- When init project first with docker elastic:
`$> sudo ./run/elastic/elastic.sh`
- Generate site documentation for project:
`$> mvn clean site --pl mainside,mq-publish,mq-consume,intro-service,cache-service && mvn clean site --pl util`
- Before start app locally run kafka 3 brokers:
 https://www.bogotobogo.com/Hadoop/BigData_hadoop_Zookeeper_Kafka_single_node_Multiple_broker_cluster.php:
 ```
 each server_(x).properties should contains:)
 broker.id=2
 port=9094
 log.dir=/tmp/kafka-logs-2
 ```
 
 - $> sudo /usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server_1.properties
 - $> sudo /usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server_2.properties
 - $> sudo /usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server_3.properties

Mysql migration:
1) go to mainside
2) m clean compile flyway:clean  flyway:migrate --pl mainside

Make release (based on util module):
1) `$>m clean install source:jar javadoc:jar -Prelease`
2) `$>gpg --verify target/*.pom.asc`
3) `$>m deploy -Prelease`
4) go to `https://oss.sonatype.org/#nexus-search;quick~makeitfine`

Kafka web viewer (locally):
1) Download jar release of `kafdrop`:
https://github.com/obsidiandynamics/kafdrop/releases
2) $>java -jar ~/dev/software/kafdrop-3.27.0.jar --kafka.brokerConnect=<host:port,host:port> (e.g localhost:9093,..)
 * It's necessary for brokerConnect contains all brokers from _consumer_offset to success run.
 Otherwise reboot PC or add localhost:9092
   
Mysql database help:
SET GLOBAL validate_password_policy=LOW;

CREATE USER 'makeitfinemysql'@'localhost' IDENTIFIED BY 'makeitfine121';

SHOW VARIABLES LIKE 'validate_password%';

SET GLOBAL validate_password.policy = 0;

GRANT ALL PRIVILEGES ON makeitfinemysql.* TO 'makeitfinemysql'@'localhost';
