'use strict';

(function(module, require) {

  module.exports = function(context, name) {
    var Generate = require('./generate.jsx')(context, name);
    var New = require('./new.jsx')(context, name);
    var Notification = require('./notification.jsx')(context, name);
    var List = require('./list.jsx')(context, name);
    var Notifications = require('./notifications.jsx')(context, name);
    var Test = require('./main.jsx')(context, name);

  };

}(module, require));
