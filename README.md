Sample Spring Cloud Task Application
============

## About the project

This project is a sample Spring boot application that showcases the following technologies.
- Spring Cloud Tasks.
- Parameterized Spring Boot Batch implementation to process csv files.
- MySQL database integration with Spring schema initialization.
- Project leverages `spring-boot-docker-compose`.

### Launching Task Application Jobs

Execute `./gradlew bootRun --args "filePath=classpath:user-data.csv"`

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.2/gradle-plugin/reference/html/#build-image)
* [Task](https://docs.spring.io/spring-cloud-task/docs/current/reference/html/)
* [JDBC API](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.sql)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#features.docker-compose)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#howto.batch)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* mysql: [`mysql:latest`](https://hub.docker.com/_/mysql)

Please review the tags of the used images and set them to the same as you're running in production.

