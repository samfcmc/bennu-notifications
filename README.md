# bennu-notifications
[![Build Status](https://travis-ci.org/samfcmc/bennu-notifications.svg?branch=master)](https://travis-ci.org/samfcmc/bennu-notifications)

Notifications service for the
[Bennu Framework](https://github.com/FenixEdu/bennu).

This library allows applications developed using the Bennu Framework to have real-time notifications (e-mail, push notifications, etc)

## Architecture
![Architecture](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/architecture.png)

## Requirements:
* Java JDK 8
* Maven

## Development
* Clone this repo
* Go to project's directory
* Install all modules and run unit tests
```
mvn install
```
* Run the embedded server
```
mvn tomcat7:run
```
* Open a web browser in [http://localhost:8080](http://localhost:8080)
* Enjoy your notifications service ;)
