'use strict';

/*
 * Notifications JS Consumer
 */

(function(require, jQuery) {

  var Notifications = require('./notifications')(jQuery);

  window.Notifications = Notifications;

}(require, window.jQuery));
