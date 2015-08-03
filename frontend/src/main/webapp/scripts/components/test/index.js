'use strict';

(function(module, require) {

  module.exports = function(React, NotificationsClient) {
    var Generate = require('./generate.jsx')(React, NotificationsClient);
    var New = require('./new.jsx')(React);
    var Notification = require('./notification.jsx')(React);
    var List = require('./list.jsx')(React, Notification);
    var Notifications = require('./notifications.jsx')(React, New, List);
    var Test = require('./test.jsx')(React, Generate, Notifications);

    return Test;

  };

}(module, require));
