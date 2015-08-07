'use strict';

(function(module, require) {

  module.exports = function(React, NotificationsClient, ReactBootstrap, moment) {
    var Generate = require('./generate.jsx')(React, NotificationsClient, ReactBootstrap);
    var New = require('./new.jsx')(React);
    var Notification = require('./notification.jsx')(React, ReactBootstrap, NotificationsClient, moment);
    var List = require('./list.jsx')(React, Notification);
    var Notifications = require('./notifications.jsx')(React, New, List, NotificationsClient);
    var Test = require('./test.jsx')(React, Generate, Notifications);

    return Test;

  };

}(module, require));
