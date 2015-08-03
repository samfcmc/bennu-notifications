'use strict';

(function(module) {

  module.exports = function(React) {
    var Generate = require('./generate.jsx')(React);
    var New = require('./new.jsx')(React);
    var Notification = require('./notification.jsx')(React);
    var List = require('./list.jsx')(React, Notification);
    var Notifications = require('./notifications.jsx')(React, New, List);
    var Test = require('./test.jsx')(React, Generate, Notifications);

    return Test;

  };

}(module));
