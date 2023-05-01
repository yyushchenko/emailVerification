# emailVerification

This is an assignment for Junior QA position. 

The goal is to create a test case that is checking: 
- login to Gmail
- sending email with subject and body to the same email address
- that email with the given subject and from same sender is received

#### Frameworks and tools used: 
- Selenium
- TestNG
- Maven 

### Configuration

There are two configuration files

#### config.properties

Here we should set up driver location

```
driverLocation=path/to/chromedriver
```

#### testng.xml

This file allows to set up test parameters:

| Name       | Description                                                   | Example                             |
|------------|---------------------------------------------------------------|-------------------------------------|
| email      | Gmail account that the test will login, send and check emails | here.we.are@gmail.com               |
| password   | Gmail account password                                        | KeepItSecret123                     |
| baseUrl    | Gmail page URL                                                | https://www.google.com/gmail/about/ |
| reportFile | A path to the report file that will be generated              | /path/to/report.txt                 |

## Comments

For reporting, it is possible to use a TestNG report which can be configured in IDE and is automatically generated after
a
test run.
Inbuilt TestNG Reporter class can be used to record logs in TestNG report:

```java
Reporter.log("Login is successful");
```