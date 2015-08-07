'use strict';

/*
 * Notifications JS Consumer
 */

(function(require, module, jQuery) {

  var Notifications = require('./notifications')(jQuery);

  module.exports = Notifications;

}(require, module, window.jQuery));
