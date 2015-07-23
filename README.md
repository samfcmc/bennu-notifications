# bennu-notifications
[![Build Status](https://travis-ci.org/samfcmc/bennu-notifications.svg?branch=master)](https://travis-ci.org/samfcmc/bennu-notifications)

Notifications service for the
[Bennu Framework](https://github.com/FenixEdu/bennu).

This library allows applications developed using the Bennu Framework to have real-time notifications (e-mail, push notifications, etc)

## Architecture
![Architecture](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/architecture.png)

### Domain in the backend
The following diagram shows the domain that is stored in the backend

![Domain](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/domain-backend.png)

## Requirements:
* Java JDK 8
* Maven
* MySQL

## Development
* Clone this repo
* Go to project's directory
* Start mysql server
* Create a database
* Go to the webapp folder and create a ```fenix-framework.properties``` file
```
$ cp webapp/src/main/resources/fenix-framework.properties-template webapp/src/main/resources/fenix-framework.properties
```
* Edit that file according to your mysql server
* Install all modules and run unit tests
```
$ mvn install
```

## Backend
The Backend is where the notifications are stored.
This backend runs somewhere and allows you to register authorized applications and offers an API, for the Clients, to create notifications. The "consumers" subscribe to this backend and start to receive notifications.

* Run in an embedded server
```
$ mvn tomcat7:run
```
* Open a web browser in [http://localhost:8080](http://localhost:8080)
* Enjoy your notifications service ;)

NOTE: When you change server-side code while the embedded tomcat is running you just need to run ```mvn install``` in the module you have changed and the embedded server will automatically reload

## Client (JAVA)
The client is the component that actually generates and sends notifications to the Notifications service.
There is only a Java Client.
### Usage
* First, if you are using maven in your project you have to install
the client module:
```
$ cd client && mvn install
```
* Then, you have to add it to your dependencies in the ```pom.xml``` file of your project:
```xml
  <dependency>
    <groupId>org.fenixedu</groupId>
    <artifactId>bennu-notifications-client</artifactId>
    <version>1.0</version>
  </dependency>
```

* You will need to create a ```src/main/resources/notifications.properties``` in your file with the following content:
```
url=<url of your notifications service>
appId=<App ID got from the notifications service for your application>
appSecret=<App Secret key got from the notifications service for your application>
```

* In your code you need to get a Client instance:
```java
NotificationsClient client = NotificationsClientFactory.getClient();
```

TODO: Explain how to post notifications
