bennu-notifications
===================

[![Build Status](https://travis-ci.org/samfcmc/bennu-notifications.svg?branch=master)](https://travis-ci.org/samfcmc/bennu-notifications)

Notifications service for the [Bennu Framework](https://github.com/FenixEdu/bennu).

This library allows applications developed using the Bennu Framework to have real-time notifications (e-mail, push notifications, etc)

## Architecture

![Architecture](https://raw.githubusercontent.com/samfcmc/bennu-notifications/master/architecture.png)

## Requirements:

-	Java JDK 8
-	Maven

## Development
* Go to the project's root and use maven to compile it

```
$ mvn install
```

## Backend

The Backend is where the notifications are stored. This backend runs somewhere and allows you to register authorized applications and offers an API, for the Clients, to create notifications. The "consumers" subscribe to this backend and start to receive notifications.

### Domain in the backend

The following diagram shows the domain that is stored in the backend

![Domain](http://yuml.me/ec57a5a8)

###	Run in an embedded server
* Compile the project running the following in the project's root:
```shell
mvn install
```

* Go to the webapp dir:
```shell
cd webapp
```

* Run the webapp in an embedded tomcat container using maven
```shell
mvn tomcat7
```

-	Open a web browser in [http://localhost:8080](http://localhost:8080)
-	The first time you navigate to it you have to follow the wizzard in order to complete the bootstrap process
- You notifications service is up and running ;)

### Endpoints
All requests receive a `token` query parameter.
For now, it is just the username who is attempting to invoke the endpoint.

This must be replaced by a really security protocol.

#### POST `/api/notifications?token={token}`
Creates a new notification
* Data:
```javascript
{
      /*
       * usernames(Required): Array with usernames of who will
       * receive the notification.
       * You have to provide at least one username
       */
    "usernames": [
      "username1", "username2", ..., "usernameN"
    ],
    /*
     * payload(Required): A JSON object with the notification's payload
     * This object contains all the meta data that consumer
     * applications need to handle notifications
     */
    "payload": {
       /*
        * type(Required): A string referring to the notification's type (The consumer applications will handle this string)
      */
      "type": "TYPE",
      /*
       * image(Optional): Url of an image associated to this notification
       */
      "image": "image url",
      /*
       * link(Optional): URL associated to this new notification. Consumer
       */
      "link": "url",
      /*
       * descriptions(Required): A JSON Object with descriptions * in multiple languages.
       * Each key is a string referring to the language's name.
       * You have to provide at least one language
       */
       "descriptions": {
         "EN": "description in english",
         ...
       }  
    }
}
```

* Response: The notification that was just created
```javascript
{
    /*
     * id: The notification's identification
     */
    "id": "ID",
    /*
     * read: Boolean indicating if the notification was
     * already read
     */
    "read": true|false,
    "payload": {
      "type": "TYPE",
      "image": "image url",
      "link": "url",
       "descriptions": {
         "EN": "description in english",
         ...
       }  
    }
}
```

#### GET `/after/{id}?token={token}`
Get all the notifications that were created after the notification with the id {id}
* Parameters:
  * {id}: A notification's id
* Response:A JSON array with all the notifications that were created after the notification with the id {id}
```javascript
[
    {
        /*
         * id: The notification's identification
         */
        "id": "ID",
        /*
         * read: Boolean indicating if the notification was
         * already read
         */
        "read": true|false,
        "payload": {
          "type": "TYPE",
          "image": "image url",
          "link": "url",
           "descriptions": {
             "EN": "description in english",
             ...
           }  
        }
    },
    ...
]
```
#### GET `/before/{id}?token={token}`
Gett all notifications that were created before the notification with the id {id}
* Parameters:
  * {id}: A notification's id
* Response: A JSON array with all the notifications that were created before the notification with the id {id}
```javascript
[
    {
        /*
         * id: The notification's identification
         */
        "id": "ID",
        /*
         * read: Boolean indicating if the notification was
         * already read
         */
        "read": true|false,
        "payload": {
          "type": "TYPE",
          "image": "image url",
          "link": "url",
           "descriptions": {
             "EN": "description in english",
             ...
           }  
        }
    },
    ...
]
```

#### GET `/last/{n}?token={token}`
Get all {n} most recent notifications
* Parameters:
  * {n}: An integer > 0
* Response: A JSON array with the {n} most recent notifications
```javascript
[
    {
        /*
         * id: The notification's identification
         */
        "id": "ID",
        /*
         * read: Boolean indicating if the notification was
         * already read
         */
        "read": true|false,
        "payload": {
          "type": "TYPE",
          "image": "image url",
          "link": "url",
           "descriptions": {
             "EN": "description in english",
             ...
           }  
        }
    },
    ...
]
```

#### POST `/read/{id}?token={token}`
Read a given notification
* Parameters:
  * {id}: A notification's identification
* Response: The notification that was just read
```javascript
[
    {
        /*
         * id: The notification's identification
         */
        "id": "ID",
        /*
         * read: Boolean indicating if the notification was
         * already read
         */
        "read": true,
        "payload": {
          "type": "TYPE",
          "image": "image url",
          "link": "url",
           "descriptions": {
             "EN": "description in english",
             ...
           }  
        }
    },
    ...
]
```

#### GET `/unread?token={token}`
Get all user's unread notification
* Response: A JSON array with all unread notifications
```javascript
[
    {
        /*
         * id: The notification's identification
         */
        "id": "ID",
        /*
         * read: Boolean indicating if the notification was
         * already read
         */
        "read": true|false,
        "payload": {
          "type": "TYPE",
          "image": "image url",
          "link": "url",
           "descriptions": {
             "EN": "description in english",
             ...
           }  
        }
    },
    ...
]
```

## Client (JAVA)

There is a [client](https://github.com/samfcmc/notifications-client-java), for this service, written in JAVA.
