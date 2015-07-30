'use strict';

(function(module) {

  module.exports = function(React, ReactRouter) {
    var Navbar = require('./navbar')(React, ReactRouter);
    var Main = require('./main')(React, Navbar, ReactRouter);
    var Welcome = require('./welcome')(React);
    var Admin = require('./admin')(React);
    var Test = require('./test')(React);
    return {
      Navbar: Navbar,
      Main: Main,
      Welcome: Welcome,
      Admin: Admin,
      Test: Test
    };
  }

}(module))
