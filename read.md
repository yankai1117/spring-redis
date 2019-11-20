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

###@JoinTable
在使用此@JoinTable标记时，需要注意以下几个问题。
该标记与@Table注释类似，用于标注用于关联的表。可以标注在方法或者属性上
name属性为连接两个表的表名称。若不指定，则使用默认的表名称如下所示。
“表名1”+“_”+“表名2”。

### @joinColumns
@joinColumns属性表示，在保存关系中的表中，所保存关联关系的外键的字段。并配合@JoinColumn标记使用。
例如以下的映射配置，表示字段customer_id为外键关联到customer表中的id字段。
joinColumns={
         @JoinColumn(name="customer_id",referencedColumnName="id")
}
###@inverseJoinColumns

inverseJoinColumns属性与joinColumns属性类似，它保存的是保存关系的另一个外键字段。
例如以下的映射配置，表示字段address_id为外键关联到address表中的id字段。
@inverseJoinColumns={
         @JoinColumn(name="address_id",referencedColumnName="id")
}

提示：@JoinTable不仅能够定义一对多的关联，也可以定义多对多表的关联
在单向关系中没有mappedBy,主控方相当于拥有指向另一方的外键的一方。
*    1.一对一和多对一的@JoinColumn注解的都是在“主控方”，都是本表指向外表的外键名称。
*    2.一对多的@JoinColumn注解在“被控方”，即一的一方，指的是外表中指向本表的外键名称。
*    3.多对多中，joinColumns写的都是本表在中间表的外键名称，inverseJoinColumns写的是另一个表在中间表的外键名称。

* 写一个kafka生产者消费者的demo跑的时候报错：
java.io.IOException: Can't resolve address: VM_0_12_centos:9092
不能解析VM_0_12_centos
kafka 连接原理
首先连接 192.168.0.141:9092
再连接返回的host.name = VM_0_12_centos,
最后继续连接advertised.host.name=VM_0_12_centos
解决办法
###添加window解析
hosts 文件增加 
192.168.0.141 VM_0_12_centos
用cmd ping bogon 试试如果可以ping通即可。