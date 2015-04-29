# bennu-notifications
[![Build Status](https://travis-ci.org/samfcmc/bennu-notifications.svg?branch=master)](https://travis-ci.org/samfcmc/bennu-notifications)

Notifications service for the
[Bennu Framework](https://github.com/FenixEdu/bennu).

This library allows applications developed using the Bennu Framework to have real-time notifications (e-mail, push notifications, etc)

Give a brief introduction about the architecture
* NMaster
* NClient
* ...

## Requirements:
* Java JDK 8
* Maven

## Installation
In the project's root run:

```
mvn install
```

It will install and run all unit tests.

## Add this library as a dependency in your application:
TODO:

## Run in a embedded web server
You can use maven to run the REST API in an embedded tomcat container.

Go to web-app directory

```
cd bennu-notifications-webapp
```

And launch the embedded tomcat container

```
mvn tomcat7:run
```

Open your web browser in
http://localhost:8000 and go through the entire bootstrap process.

Now you can invoke the REST API endpoints.

TODO: Describe all endpoints here:
