'use strict';

(function(module, require) {

  module.exports = function(context, name) {
    require('./config.jsx')(context, name);
    require('./webhooks.jsx')(context, name);
    require('./main.jsx')(context, name);

  }

}(module, require));
