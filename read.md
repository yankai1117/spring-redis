# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)

有时候遇见dispatchServelert已经初始化了，但是请求过来的时候拦截不到出现404，
这个原因可能是因为@SpringBootApplication注解默认的扫描路径是从其所在的目录的
子文件夹开始的，可以将controller放在Application所在的包的子包下，也可以加
扫描路径@SpringBootApplication(scanBasePackages = "com.example.controller")