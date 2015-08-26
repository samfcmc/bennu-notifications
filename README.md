bennu-notifications
===================

[![Build Status](https://travis-ci.org/samfcmc/bennu-notifications.svg?branch=master)](https://travis-ci.org/samfcmc/bennu-notifications)

Notifications service for the[Bennu Framework](https://github.com/FenixEdu/bennu).

This library allows applications developed using the Bennu Framework to have real-time notifications (e-mail, push notifications, etc)

Architecture
------------

![Architecture](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/architecture.png)

### Domain in the backend

The following diagram shows the domain that is stored in the backend

![Domain](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/domain-backend.png)

Requirements:
-------------

-	Java JDK 8
-	Maven
-	MySQL

Development
-----------

-	Clone this repo
-	Go to project's directory
-	Start mysql server
-	Create a database
-	Go to the webapp folder and create a `fenix-framework.properties` file

```
$ cp webapp/src/main/resources/fenix-framework.properties-template webapp/src/main/resources/fenix-framework.properties
```

-	Edit that file according to your mysql server
-	Install all modules and run unit tests

```
$ mvn install
```

Backend
-------

The Backend is where the notifications are stored. This backend runs somewhere and allows you to register authorized applications and offers an API, for the Clients, to create notifications. The "consumers" subscribe to this backend and start to receive notifications.

-	Run in an embedded server

```
$ mvn tomcat7:run
```

-	Open a web browser in [http://localhost:8080](http://localhost:8080)
-	Enjoy your notifications service ;)

Client (JAVA)
-------------

There is a [client](https://github.com/samfcmc/notifications-client-java), for this service, written in JAVA.
