'use strict';

(function(module) {

  module.exports = function(context) {
    var Partials = require('./partials')(context, 'Partials');
    var Main = require('./main.jsx')(context, 'Main');
    var Welcome = require('./welcome.jsx')(context, 'Main');
    var Admin = require('./admin')(context, 'Admin');
    var Test = require('./test')(context, 'Test');
  }

}(module));
