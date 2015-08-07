'use strict';

(function(module) {

  module.exports = function(React, ReactRouter, NotificationsClient, ReactBootstrap, moment) {
    var Partials = require('./partials')(React, ReactRouter);
    var Main = require('./main.jsx')(React, Partials.Navbar, ReactRouter);
    var Welcome = require('./welcome.jsx')(React);
    var Admin = require('./admin')(React);
    var Test = require('./test')(React, NotificationsClient, ReactBootstrap, moment);
    return {
      Partials: Partials,
      Main: Main,
      Welcome: Welcome,
      Admin: Admin,
      Test: Test
    };
  }

}(module));
