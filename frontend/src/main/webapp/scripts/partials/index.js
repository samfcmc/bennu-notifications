'use strict';

(function(module) {

  module.exports = function(React, ReactRouter) {

    var Navbar = require('./navbar.jsx')(React, ReactRouter);

    return {
      Navbar: Navbar
    };
  };

}(module));
