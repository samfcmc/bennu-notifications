'use strict';

(function(module, require) {

  module.exports = function(React) {

    var Admin = require('./admin.jsx')(React);

    return Admin;

  }

}(module, require));
